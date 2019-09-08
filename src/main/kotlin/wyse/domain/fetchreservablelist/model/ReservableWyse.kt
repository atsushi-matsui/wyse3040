package wyse.domain.fetchreservablelist.model

import java.time.LocalDate

data class ReservableWyse (
        val reservableDate: LocalDate,
        val wyseId: String
        )