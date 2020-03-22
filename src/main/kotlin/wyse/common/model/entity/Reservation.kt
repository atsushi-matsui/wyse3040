package wyse.common.model.entity

import java.util.Date

data class Reservation (
        val wyseId: String,
        val userId: String,
        val reservationStatus: ReservationStatus,
        val reservationDate: Date,
        val returnDate: Date
){
    data class ReservationForMyBatis(
            val wyseId: String? = null,
            val userId: String? = null,
            val reservationStatus: ReservationStatus? = null,
            val reservationDate: Date? = null,
            val returnDate: Date? = null
    ){
        fun of(): Reservation {
            return Reservation(
                    wyseId = this.wyseId!!,
                    userId = this.userId!!,
                    reservationStatus = this.reservationStatus!!,
                    reservationDate = this.reservationDate!!,
                    returnDate = this.returnDate!!
            )
        }
    }
}
enum class ReservationStatus{
    /** 利用待ち **/
    WAITING,
    /** 利用完了 **/
    COMPLETED,
    /** 利用キャンセル **/
    CANCELED
}