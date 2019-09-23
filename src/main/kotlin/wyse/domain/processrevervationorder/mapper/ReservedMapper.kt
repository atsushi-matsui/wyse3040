package wyse.domain.processrevervationorder.mapper

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import wyse.common.model.Reservation.ReservationForMyBatis
import wyse.common.model.ReservationStatus
import wyse.common.model.WyseStatus
import java.util.*

@Mapper
interface ReservedMapper {
    @Select("""
        select
         reservation.wyse_id,
         reservation.user_id,
         reservation.status,
         reservation.reservation_date,
         reservation.return_date
        from
         reservation
        inner join wyse
        on reservation.wyse_id = wyse.wyse_id
        where
         wyse.status in ('ACTIVATED') and
         reservation.status in ('WAITING')
        order by wyse_id, return_date asc;
        """)
    fun selectByReturnDate(): List<ReservationForMyBatis>

    @Update("""
        update wyse
        set
         status= #{status},
         reservation_date= #{reservationDate},
         return_date= #{returnDate}
        where
         wyse_id = #{wyseId}
        """)
    fun updateWyse(
            wyseId: String,
            status: WyseStatus,
            reservationDate: Date,
            returnDate: Date
    ): Int

    @Update("""
        update reservation
        set
         status= #{status},
        where
         wyse_id = #{wyseId} and
         reservation_date= #{reservationDate}
        """)
    fun updateReservation(
            wyseId: String,
            status: ReservationStatus,
            reservationDate: Date
    ): Int

}