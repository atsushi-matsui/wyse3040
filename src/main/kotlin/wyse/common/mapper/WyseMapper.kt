package wyse.common.mapper

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import wyse.common.model.Wyse.WyseForMyBatis
import wyse.common.model.WyseStatus
import java.util.*

@Mapper
interface WyseMapper {
    @Select("""
        select
         wyse_id,
         user_id,
         management_nubmer,
         status,
         reservation_date,
         return_date
        from
         wyse
        where
         status = #{wyseStatus}
        """)
    fun selectByWyseStatus(wyseStatus: WyseStatus): List<WyseForMyBatis>

    @Select("""
        select
         wyse_id,
         user_id,
         management_nubmer,
         status,
         reservation_date,
         return_date
        from
         wyse
        where
         wyse_id = #{wyseId}
        """)
    fun find(wyseId: String): WyseForMyBatis

    @Update("""
        update wyse
        set
         status = #{status},
         reservation_date = #{reservationDate},
         return_date = #{returnDate}
        where
         wyse_id = #{wyseId}
        """)
    fun updateStatusAndDates(
            wyseId: String,
            status: WyseStatus,
            reservationDate: Date?,
            returnDate: Date?
    ): Int
}