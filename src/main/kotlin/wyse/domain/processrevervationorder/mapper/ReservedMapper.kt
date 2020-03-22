package wyse.domain.processrevervationorder.mapper

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import wyse.common.model.entity.Reservation.ReservationForMyBatis
import wyse.common.model.entity.ReservationStatus
import wyse.common.model.entity.WyseStatus
import java.util.*

@Mapper
interface ReservedMapper {
    @Select("""
        select
         reserve.wyse_id,
         reserve.user_id,
         reserve.status,
         reserve.reservation_date,
         reserve.return_date
        from
         reservation_operation
        inner join wyse
        on reserve.wyse_id = wyse.wyse_id
        where
         wyse.status in ('ACTIVATED') and
         reserve.status in ('WAITING')
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
        update reserve
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