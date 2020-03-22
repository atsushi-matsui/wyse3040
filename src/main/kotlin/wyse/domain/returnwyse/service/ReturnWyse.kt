package wyse.domain.returnwyse.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wyse.app.controller.model.ReturnForm
import wyse.common.mapper.ReturnOrderMapper
import wyse.common.mapper.WyseMapper
import wyse.common.model.entity.ReturnStatus
import wyse.common.model.entity.WyseStatus
import wyse.domain.WyseExceptionhandller.DomainRuntimeException
import wyse.domain.WyseExceptionhandller.InvalidWyseStatusException

/**
 シンクライアントの返却を行う機能
 **/
@Service
@Transactional
class ReturnWyse (
        private val wyseMapper: WyseMapper,
        private val returnOrderMapper: ReturnOrderMapper
){
    fun processOrder(returnForm: ReturnForm) {
        try {
            val returnTarget = wyseMapper.find(returnForm.wyseId).of()

            if (returnTarget.userId != returnForm.userId) throw InvalidWyseStatusException("予約したユーザと返却するユーザが異なります.")
            when (returnTarget.status) {
                WyseStatus.USING -> {
                    returnOrderMapper.insert(returnForm, ReturnStatus.COMPLETED)
                    wyseMapper.updateStatusAndDate(
                            wyseId = returnForm.wyseId,
                            status = WyseStatus.ACTIVATED,
                            reservationDate = null,
                            returnDate = null
                    )
                }
                WyseStatus.ACTIVATED, WyseStatus.DEACTIVATED -> InvalidWyseStatusException("シンクライアントは返却可能なステータスではありません.")
            }
        } catch (e: Throwable){
            throw DomainRuntimeException("テーブル更新中にエラーが発生しました", e)
        }
    }
}