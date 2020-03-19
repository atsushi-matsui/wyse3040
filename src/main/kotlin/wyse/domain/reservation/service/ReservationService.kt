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


/**
 シンクライアント貸出、もしくは予約を行う機能
 **/
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
                   wyseMapper.updateStatusAndDate(
                           wyseId = reservationForm.wyseId,
                           status = WyseStatus.USING,
                           reservationDate = reservationForm.reservationDate,
                           returnDate = reservationForm.returnDate

                   )
               }
               // 対象のシンクライアントが使用中の場合は予約オーダを登録する
               WyseStatus.USING -> {
                   val waitingReservedOrders = reservationMapper.selectByWyseIdAndStatus(reservationForm.wyseId, ReservationStatus.WAITING).map { it.of() }
                   if(! judgeReservable(reservationForm ,reservationTarget, waitingReservedOrders))
                       throw UnavailableReservationException("ご希望の貸出期間はすでに予約済みです.")
                   reservationMapper.insert(reservationForm, ReservationStatus.WAITING)
               }
               WyseStatus.DEACTIVATED -> throw UnavailableReservationException("利用希望のシンクライアントは現在使用不可です.")
           }

       } catch (e: Throwable){
           throw DomainRuntimeException("テーブル更新中にエラーが発生しました", e)
       }
    }

    /**
    　現在の使用情報と仕掛かり中の予約オーダ情報を元に予約可能であるかを判定する
     **/
    private fun judgeReservable(
            currentOrder: ReservationForm,
            wyse: Wyse,
            waitingReservedOrders: List<Reservation>): Boolean{

        // 指定した利用期間ですでに予約が入っていないかチェック
        for (idx in waitingReservedOrders.indices) {
            val startPoint = if (idx == 0) {
                wyse.returnDate ?: throw DomainRuntimeException("利用中のシンクライアントに予約日が適用されていません.", null)
            }
            else waitingReservedOrders[idx-1].returnDate

            val endPoint = waitingReservedOrders[idx].reservationDate

            val reservableTerm = startPoint..endPoint

            if( reservableTerm.contains(currentOrder.reservationDate)
                    && reservableTerm.contains(currentOrder.returnDate)
            ) return true
        }

         waitingReservedOrders.last().let {
             if( it.returnDate < wyse.reservationDate )
                 return true
         }

        return false
    }
}