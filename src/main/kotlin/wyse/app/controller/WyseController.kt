package wyse.app.controller

import wyse.domain.fetchreservablelist.service.FetchReservableWyseService
import wyse.domain.reserve.model.Reservation
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import java.time.LocalDate

@Controller
@RequestMapping("wyse")
class WyseController(
        //private val model: Model,
        private val fetchReservableWyseService: FetchReservableWyseService
) {
    @RequestMapping("reservable")
    fun findReservablebleWyseList(model: Model): String {
        val reservableWyseList = fetchReservableWyseService.findReservableWyse(LocalDate.now())
        model.addAttribute(reservableWyseList)
//        reservableWyseList.map {
//            model.addAttribute(it.key, it.key)
//            model.addAttribute(it.key.plus("ReservableDateList"), it.value)
//        }
        return "reservable/listReservableWyse"
    }

    @RequestMapping("reserve")
    fun reserve(reservation: Reservation) {

    }
}