package wyse.app.controller.model

import org.springframework.format.annotation.DateTimeFormat
import java.util.Date
import javax.validation.constraints.NotBlank

data class ReservationForm (
        @NotBlank(message = "userIdは必須項目です.")
        val userId: String,
        @NotBlank(message = "wyseIdは必須項目です.")
        val wyseId: String,
        @NotBlank(message = "予約日は必須項目です.")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        val reservationDate: Date,
        @NotBlank(message = "返却日は必須項目です.")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        val returnDate: Date
)