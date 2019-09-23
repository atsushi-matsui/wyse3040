package wyse

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import wyse.domain.replynotification.ReplyNotificationService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import wyse.domain.processrevervationorder.service.ProcessReservationOrderService

@Configuration
class BatchConfig(
        private val replyNotificationService: ReplyNotificationService,
        private val processReservationOrderService: ProcessReservationOrderService
        ) {
    @Bean
    @ConditionalOnProperty(value = "batch.execute" , havingValue = "ReplyNotificationService")
    fun replyNotification(): BatchRunner  {
        return BatchRunner(replyNotificationService)
    }
    @Bean
    @ConditionalOnProperty(value = "batch.execute" , havingValue = "processReservationOrderService")
    fun processReservationOrder(): BatchRunner  {
        return BatchRunner(processReservationOrderService)

    }

}