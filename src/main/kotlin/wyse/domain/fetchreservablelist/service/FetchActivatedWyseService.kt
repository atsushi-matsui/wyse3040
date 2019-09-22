package wyse.domain.fetchreservablelist.service

import wyse.common.mapper.WyseMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wyse.common.model.Wyse
import wyse.common.model.WyseStatus
import wyse.domain.WyseExceptionhandller.DomainRuntimeException
import java.sql.Date
import java.time.LocalDate

@Service
@Transactional
class FetchActivatedWyseService(
    private val wyseMapper: WyseMapper
) {
    fun select(localDateToday: LocalDate): List<Wyse> {
        try {

//            return listOf(
//                    Wyse(
//                            wyseId = "001id",
//                            managementNubmer = "0001manage",
//                            status = WyseStatus.ACTIVATED,
//                            reservationDate = Date.valueOf(localDateToday),
//                            returnDate = Date.valueOf(localDateToday)
//                            )
//            )
 //           val test = wyseMapper.select(WyseStatus.ACTIVATED)
            return wyseMapper.select(WyseStatus.ACTIVATED).map { it.of() }
        } catch (e : Throwable) {
            throw e
            //throw DomainRuntimeException("予約可能なwyse一覧の取得に失敗しました.")
        }
    }
}

