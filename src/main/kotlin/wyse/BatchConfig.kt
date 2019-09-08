package wyse

import wyse.domain.replynotification.ReplyNotificationService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BatchConfig(
        private val replyNotificationService: ReplyNotificationService
) {
    @Bean
    fun replyNotification(): BatchRunner  {
        return BatchRunner(replyNotificationService)
    }
}