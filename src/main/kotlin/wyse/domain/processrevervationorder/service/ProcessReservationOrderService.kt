package wyse.domain.processrevervationorder.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wyse.BatchProcess
import wyse.common.model.Reservation
import wyse.common.model.ReservationStatus
import wyse.common.model.WyseStatus
import wyse.domain.processrevervationorder.mapper.ReservedMapper
import java.util.*

/**
  シンクライアントが空き状態なら仕掛り中の予約オーダを処理する
 */
@Service
@Transactional
class ProcessReservationOrderService (
        private val reservedMapper: ReservedMapper
): BatchProcess {
    override fun process() {
        try {
            val test = reservedMapper.selectByReturnDate().map { it.of() }
                    .asSequence()
                    .groupBy { it.wyseId }
            test.let {

            }

            reservedMapper.selectByReturnDate().map { it.of() }
                    .asSequence()
                    .groupBy { it.wyseId }
                    .forEach {
                        val firstReservation = it.value
                                .asSequence()
                                .sortedBy { reservation ->  reservation.reservationDate }
                                .firstOrNull() ?: return
                        reservedMapper.updateWyse(
                                wyseId = firstReservation.wyseId,
                                status = WyseStatus.ACTIVATED,
                                reservationDate = firstReservation.reservationDate,
                                returnDate = firstReservation.returnDate
                        )
                        reservedMapper.updateReservation(
                                wyseId = firstReservation.wyseId,
                                status = ReservationStatus.COMPLETED,
                                reservationDate = firstReservation.reservationDate
                        )
                    }
        } catch (e: Throwable){
        // FIXME JDBC及びmyBatis関連のエラーはcatchしてエラーログを出力。後続の処理を進める。
        }

    }


    private fun excuteDomainProcess(target: List<Reservation>) {
        target.asSequence()
                .groupBy{ it.wyseId }
                .forEach {
                    val firstReservation = it.value.first()
                    reservedMapper.updateWyse(
                            wyseId = firstReservation.wyseId,
                            status = WyseStatus.ACTIVATED,
                            reservationDate = firstReservation.reservationDate,
                            returnDate = firstReservation.returnDate
                    )
                    reservedMapper.updateReservation(
                            wyseId = firstReservation.wyseId,
                            status = ReservationStatus.COMPLETED,
                            reservationDate = firstReservation.reservationDate
                    )

//                    replyNotification(it.value)
//                    if (it.value.size == 1) {
//                        val firstReservation = it.value.first()
//                        reservedMapper.updateWyse(
//                                wyseId = firstReservation.wyseId,
//                                status = WyseStatus.ACTIVATED,
//                                reservationDate = null,
//                                returnDate = null
//                                )
//                    } else {
//
//                    }
                }
    }
}