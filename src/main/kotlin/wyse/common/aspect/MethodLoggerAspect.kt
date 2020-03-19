package wyse.common.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component

@Aspect
@Component
// FIXME ドメイン層のクラスにAOPを入れ込むことを検討
class MethodLoggerAspect {

    //domain直下とその配下パッケージでAspect処理を実行
    @Before("within(wyse.domain..)")
    fun beforeLog(jp: JoinPoint) {
        print("${jp.signature}の処理を開始します. ")
    }

    @AfterReturning("within(wyse.domain..)")
    fun afterRunningLog(jp: JoinPoint) {
        print("${jp.signature}が正常終了しました. ")
    }

    @AfterThrowing("within(wyse.domain..)")
    fun afterThrowingLog(jp: JoinPoint) {
        print("${jp.signature}が異常終了しました. ")
    }
}