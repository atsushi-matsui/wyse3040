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

@Controller
@RequestMapping("/wyse")
class WyseController(
        private val fetchActivatedWyseService: FetchActivatedWyseService,
        private val reservationService: ReservationService,
        private val returnWyse: ReturnWyse
        ) {
    @RequestMapping("/activated")
    fun findActivatedWyseList(model: Model): String {
        val activatedWyseList = fetchActivatedWyseService.select(LocalDate.now())
        model.addAttribute("activatedWyseList",activatedWyseList)
        return "activatedWyseList"
    }

    /**
    ここから下はリアルタイムAPI
     */

    @RequestMapping("/reservation")
    fun reservation(reservationForm: ReservationForm, model: Model): String {
        try {
            reservationService.processOrder(reservationForm)
        } catch (e: UnavailableReservationException) {
            model.addAttribute("errorMessage", e.message)
            // FIXME thymeleaf未作成
            return "error/reservation"
        } catch (e: DomainRuntimeException) {
            model.addAttribute("errorMessage", e.message)
            // FIXME thymeleaf未作成
            return "error/domain/process"
        }
        // FIXME thymeleaf未作成
        return "completed"
    }

    @RequestMapping("/returnwyse")
    fun returnWyse (returnForm: ReturnForm, model: Model): String{
        try {
            returnWyse.processOrder(returnForm)
        } catch(e: InvalidWyseStatusException) {
            model.addAttribute("errorMessage", e.message)
            // FIXME thymeleaf未作成
            return "error/reservation"
        } catch (e: DomainRuntimeException) {
            model.addAttribute("errorMessage", e.message)
            // FIXME thymeleaf未作成
            return "error/domain/process"
        }
        // FIXME thymeleaf未作成
        return "completed"
    }
}