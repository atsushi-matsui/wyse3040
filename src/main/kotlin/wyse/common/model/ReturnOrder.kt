package wyse.common.model

import java.util.Date

class ReturnOrder(
        val wyseId: String,
        val userId: String,
        val returnStatus: ReturnStatus,
        val returnDate: Date
) {
    data class ReturnOrderForMyBatis(
            val wyseId: String? = null,
            val userId: String? = null,
            val returnStatus: ReturnStatus? = null,
            val returnDate: Date? = null
    ){
        fun of(): ReturnOrder{
            return ReturnOrder(
                    wyseId = this.wyseId !!,
                    userId = this.userId !!,
                    returnStatus = this.returnStatus !!,
                    returnDate = this.returnDate !!
            )
        }
    }
}
enum class ReturnStatus{
    WAITING,
    COMPLETED
}