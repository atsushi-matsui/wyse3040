package wyse.domain.reservation.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wyse.app.controller.model.ReservationForm
import wyse.common.mapper.ReservationMapper
import wyse.common.mapper.WyseMapper
import wyse.common.model.Reservation
import wyse.common.model.ReservationStatus
import wyse.common.model.Wyse
import wyse.common.model.WyseStatus
import wyse.domain.WyseExceptionhandller.UnavailableReservationException
import wyse.domain.WyseExceptionhandller.DomainRuntimeException

@Service
@Transactional
class ReservationService(
        private val wyseMapper: WyseMapper,
        private val reservationMapper: ReservationMapper
) {
    fun processOrder(reservationForm: ReservationForm){
       try{
           val reservationTarget = wyseMapper.find(reservationForm.wyseId).of()
           when (reservationTarget.status){
               // 即時予約
               WyseStatus.ACTIVATED -> {
                   reservationMapper.insert(reservationForm, ReservationStatus.COMPLETED)
                   wyseMapper.updateStatusAndDates(
                           wyseId = reservationForm.wyseId,
                           status = WyseStatus.USING,
                           reservationDate = reservationForm.reservationDate,
                           returnDate = reservationForm.returnDate

                   )
               }
               // 対象のシンクライアントが使用中の場合は仕掛り中のオーダとする
               WyseStatus.USING -> {
                   val reservationOrders = reservationMapper.selectByWyseIdAndStatus(reservationForm.wyseId, ReservationStatus.WAITING).map { it.of() }
                   if(! judgeReservable(reservationForm ,reservationTarget, reservationOrders))
                       throw UnavailableReservationException("ご希望の予約日は予約済みです.")
                   reservationMapper.insert(reservationForm, ReservationStatus.WAITING)
               }
               WyseStatus.DEACTIVATED -> throw UnavailableReservationException("予約希望のシンクライアントは現在使用不可です.")
           }

//           val reservatedWyseList = reservationMapper.selectByWyseIdAndStatus(reservationForm.wyseId).map { it.of() }
//           if(judgeReservable(reservatedWyseList, reservationForm)){
//               reservationMapper.insert(reservationForm)
//           } else {
//               throw AlreadyReservedException("希望の予約時間は予約済みとなっています.")
//           }
       } catch (e: Throwable){
           throw DomainRuntimeException("テーブル更新中にエラーが発生しました", e)
       }
    }

    private fun judgeReservable(
            currentOrder: ReservationForm,
            reservationTarget: Wyse,
            reservationOrders: List<Reservation>): Boolean{

        val reservationTargetReservationDate = reservationTarget.reservationDate
                ?: throw DomainRuntimeException("利用中のシンクライアントに予約日が適用されていません.", null)
        val reservationTargetReturnDate = reservationTarget.returnDate
                ?: throw DomainRuntimeException("利用中のシンクライアントに返却日が適用されていません.", null)

        // 仕掛り中のレコードの予約日と返却日が申込中のオーダと重複していないか検査する
        return if (reservationTargetReturnDate< currentOrder.reservationDate
                && reservationTargetReservationDate < currentOrder.returnDate) {
            reservationOrders.all {
                it.returnDate < currentOrder.reservationDate
                        && it.reservationDate < currentOrder.returnDate
            }
        } else { false }
    }
}