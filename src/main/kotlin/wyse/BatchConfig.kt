package wyse

import wyse.domain.replynotification.ReplyNotificationService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import wyse.domain.processrevervationorder.service.ProcessReservationOrder

@Configuration
class BatchConfig(
        private val replyNotificationService: ReplyNotificationService,
        private val processReservationOrder: ProcessReservationOrder
        ) {
    @Bean
    fun replyNotification(): BatchRunner  {
        return BatchRunner(replyNotificationService)
    }
    @Bean
    fun reservedCompleted(): BatchRunner  {
        return BatchRunner(processReservationOrder)

    }

}