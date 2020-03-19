package wyse.app.controller

import wyse.domain.fetchreservablelist.service.FetchActivatedWyseService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import wyse.app.controller.model.ReservationForm
import wyse.app.controller.model.ReturnForm
import wyse.domain.WyseExceptionhandller.InvalidWyseStatusException
import wyse.domain.WyseExceptionhandller.DomainRuntimeException
import wyse.domain.WyseExceptionhandller.UnavailableReservationException
import wyse.domain.reservation.service.ReservationService
import wyse.domain.returnwyse.service.ReturnWyse
import java.time.LocalDate

// FIXME restControllerに置き換えてバックエンドシステムの実装のみを行う
@Controller
@RequestMapping("/wyse")
class WyseController(
        private val fetchActivatedWyseService: FetchActivatedWyseService,
        private val reservationService: ReservationService,
        private val returnWyse: ReturnWyse
        ) {

    /**
      利用可能なwyseの一覧を取得する
     **/
    @RequestMapping("/activated")
    fun findActivatedWyseList(model: Model): String {
        val activatedWyseList = fetchActivatedWyseService.select(LocalDate.now())
        model.addAttribute("activatedWyseList",activatedWyseList)
        return "activatedWyseList"
    }

    /**
      wyse利用の予約オーダを受け取る
     */
    @RequestMapping("/reserve")
    fun reserve(reservationForm: ReservationForm, model: Model): String {
        try {
            // FIXME userIDとwyseIDがDBに登録済みかチッェクする処理を実装
            reservationService.processOrder(reservationForm)
        } catch (e: UnavailableReservationException) {
            model.addAttribute("errorMessage", e.message)
            // FIXME thymeleaf未作成
            return "error/date"
        } catch (e: DomainRuntimeException) {
            model.addAttribute("errorMessage", e.message)
            // FIXME thymeleaf未作成
            return "error/domain"
        }
        // FIXME thymeleaf未作成
        return "reserve/completed"
    }

    /**
      wyse返却の予約オーダを受け取る
     */
    @RequestMapping("/return")
    fun dropOff (returnForm: ReturnForm, model: Model): String{
        try {
            // FIXME userIDとwyseIDがDBに登録済みかチッェクする処理を実装

            returnWyse.processOrder(returnForm)
        } catch(e: InvalidWyseStatusException) {
            model.addAttribute("errorMessage", e.message)
            // FIXME thymeleaf未作成
            return "error/status"
        } catch (e: DomainRuntimeException) {
            model.addAttribute("errorMessage", e.message)
            // FIXME thymeleaf未作成
            return "error/domain"
        }
        // FIXME thymeleaf未作成
        return "returnwyse/completed"
    }
}