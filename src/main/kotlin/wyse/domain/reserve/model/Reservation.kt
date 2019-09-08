package wyse.domain.reserve.model

import java.time.LocalDate

data class Reservation (
        val reservationId: String,
        val startDateTime: LocalDate,
        val endDateTime: LocalDate,
        val reservedDate: LocalDate,
        val wyseId: String,
        val userId: String
)