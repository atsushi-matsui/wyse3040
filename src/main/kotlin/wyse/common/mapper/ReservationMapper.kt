package wyse.common.mapper

import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import wyse.app.controller.model.ReservationForm
import wyse.common.model.Reservation.ReservationForMyBatis
import wyse.common.model.ReservationStatus

@Mapper
interface ReservationMapper {

    @Select("""
        select
         wyse_id,
         user_id,
         status,
         reservation_date,
         return_date
        from
         reserve
        where
         wyse_id = #{wyseId} and
         status = #{status}
        order by reservation_date
        """)
    fun selectByWyseIdAndStatus(wyseId: String, status: ReservationStatus): List<ReservationForMyBatis>

    @Insert("""
        insert into reserve(
         wyse_id,
         user_id,
         status,
         reservation_date,
         return_date,
         create_datetime,
         update_datetime
         )
        valuse(
        #{reservationForm.wyseId},
        #{reservationForm.userId},
        #{status},
        #{reservationForm.reservationDate},
        #{reservationForm.returnDate},
        sysdate(),
        sysdate()
        )
     """)
    fun insert(reservationForm: ReservationForm, status: ReservationStatus)
}