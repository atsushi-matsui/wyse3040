package wyse.domain.fetchreservablelist.mapper

import wyse.domain.fetchreservablelist.model.ReservableWyse
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import java.time.LocalDate

@Mapper
interface ReservableWyseMapper {

    // 指定日から１週間のwyseの使用状況を取得
    @Select("""
        select
         reservable_date,
         wyse_id
        from
         reservable_wyse
        where
         reservable_date BETWEEN #{localDateToday} AND #{localDateAfterAWeek}
        order by wyse_id
        """)
    fun find(localDateToday: LocalDate, localDateAfterAWeek: LocalDate): List<ReservableWyse>
}