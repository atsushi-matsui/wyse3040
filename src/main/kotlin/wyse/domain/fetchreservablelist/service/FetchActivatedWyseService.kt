package wyse.domain.fetchreservablelist.service

import wyse.common.mapper.WyseMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wyse.common.model.Wyse
import wyse.common.model.WyseStatus
import wyse.domain.WyseExceptionhandller.DomainRuntimeException
import java.time.LocalDate

@Service
@Transactional
class FetchActivatedWyseService(
    private val wyseMapper: WyseMapper
) {
    fun select(localDateToday: LocalDate): List<Wyse> {
        try {
            return wyseMapper.selectByWyseStatus(WyseStatus.ACTIVATED).map { it.of() }
        } catch (e : Throwable) {
            throw DomainRuntimeException("予約可能なwyse一覧の取得に失敗しました.", e)
        }
    }
}

