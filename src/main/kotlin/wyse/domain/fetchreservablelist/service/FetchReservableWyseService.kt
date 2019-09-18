package wyse.domain.fetchreservablelist.service

import wyse.domain.fetchreservablelist.mapper.ReservableWyseMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wyse.domain.WyseExceptionhandller.DomainRuntimeException
import java.time.LocalDate

@Service
@Transactional
class FetchReservableWyseService(
        private val reservableWyseMapper: ReservableWyseMapper
) {
    fun findReservableWyse(localDateToday: LocalDate): Map<String, List<LocalDate>>  {

        try {
            val reservableWyse = reservableWyseMapper.find(localDateToday, localDateToday.plusWeeks(1))

            return reservableWyse.groupBy({ it.wyseId },{ it.reservableDate })

        } catch (e : Throwable) {
            throw DomainRuntimeException("予約可能なwyse一覧の取得に失敗しました.")
        }
    }
}

