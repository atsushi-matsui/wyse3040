package wyse.domain

import java.lang.RuntimeException

//FIXME パッケージの配置場所は要件検討
class WyseExceptionhandller {
    // Todo ステータスコードを例外クラスの引数に持たせたが意味があるのか？
    class UnavailableReservationException(msg: String? = null): RuntimeException(msg)
    class AlreadyReservedException(msg: String? = null): RuntimeException(msg)
    class InvalidWyseStatusException(msg: String? = null): RuntimeException(msg)
    class DomainRuntimeException(msg: String? = null, throwable: Throwable?): RuntimeException(msg)

}