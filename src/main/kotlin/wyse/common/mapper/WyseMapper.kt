package wyse.common.mapper

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import wyse.common.model.Wyse
import wyse.common.model.WyseStatus

@Mapper
interface WyseMapper {
    @Select("""
        select
         wyse_id,
         management_nubmer,
         status,
         reservation_date,
         return_date
        from
         wyse
        where
         status = #{wyseStatus}
        """)
    fun select(wyseStatus: WyseStatus): List<Wyse>
}