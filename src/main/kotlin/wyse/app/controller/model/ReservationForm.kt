package wyse.app.controller.model

import java.util.Date
import javax.validation.constraints.NotBlank

data class ReservationForm (
        @NotBlank
        val userId: String,
        @NotBlank
        val wyseId: String,
        @NotBlank
        val reservationDate: Date,
        @NotBlank
        val returnDate: Date
)