package wyse.app.controller.model

import java.util.Date
import javax.validation.constraints.NotBlank

data class ReturnForm (
        @NotBlank
        val userId: String,
        @NotBlank
        val wyseId: String,
        @NotBlank
        val returnDate: Date
)