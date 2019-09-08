package wyse.domain

import java.lang.RuntimeException

class WyseExceptionhandller {
    // Todo ステータスコードを例外クラスの引数に持たせたが意味があるのか？
    class UnavailableReservationException(msg: String? = null): RuntimeException(msg)
    class AlreadyReservedException(msg: String? = null): RuntimeException(msg)
    class DomainRuntimeException(msg: String? = null): RuntimeException(msg)

}