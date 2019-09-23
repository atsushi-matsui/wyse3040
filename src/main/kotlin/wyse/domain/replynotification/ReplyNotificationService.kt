package wyse.domain.replynotification

import wyse.BatchProcess
import org.springframework.stereotype.Service

@Service
class ReplyNotificationService: BatchProcess {
    // FIXME 返却日が過ぎたユーザに対して、メール（もしくはtocaro）を催促メールを送信するバッチを実装予定
    override fun process() {
    }
}