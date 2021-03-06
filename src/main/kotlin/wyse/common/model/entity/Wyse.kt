package wyse.common.model.entity

import java.util.*

data class Wyse (
        val wyseId: String,
        val userId: String,
        val managementNubmer: String,
        val status: WyseStatus,
        val reservationDate: Date?,
        val returnDate: Date?
    ){
    data class WyseForMyBatis (
            var wyseId: String? = null,
            var userId: String? = null,
            var managementNubmer: String? = null,
            var status: WyseStatus? = null,
            var reservationDate: Date? = null,
            var returnDate: Date? = null
    ){
        fun of (): Wyse {
           return Wyse(
                   wyseId = this.wyseId!!,
                   userId = this.userId!!,
                   managementNubmer = this.managementNubmer!!,
                   status = this.status!!,
                   reservationDate = this.reservationDate!!,
                   returnDate = this.returnDate!!
           )
        }
    }

}
enum class WyseStatus {
    /** 利用可能 **/
    ACTIVATED,
    /**　 **/
    USING,
    /**　故障 **/
    DEACTIVATED
}