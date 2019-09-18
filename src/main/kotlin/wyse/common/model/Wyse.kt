package wyse.common.model

import java.util.*

data class Wyse (
    val wyseId: String,
    val managementNubmer: String,
    val status: WyseStatus,
    val reservationDate: Date,
    val returnDate: Date,
    )
enum class WyseStatus {
    ACTIVE,
    USING,
    DEACTIVE
}