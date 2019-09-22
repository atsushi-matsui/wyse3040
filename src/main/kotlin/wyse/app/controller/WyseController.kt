package wyse.app.controller

import wyse.domain.fetchreservablelist.service.FetchActivatedWyseService
import wyse.domain.reserve.model.Reservation
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import java.time.LocalDate

@Controller
@RequestMapping("/wyse")
class WyseController(
        private val fetchActivatedWyseService: FetchActivatedWyseService
) {
    @RequestMapping("/activated")
    fun findActivatedWyseList(model: Model): String {
        val activatedWyseList = fetchActivatedWyseService.select(LocalDate.now())
        model.addAttribute("activatedWyseList",activatedWyseList)
        return "activatedWyseList"
    }

    @RequestMapping("reserve")
    fun reserve(reservation: Reservation) {

    }
}