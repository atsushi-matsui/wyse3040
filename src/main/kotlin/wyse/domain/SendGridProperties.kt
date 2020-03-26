package wyse.domain

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

//FIXME パッケージの配置場所は要件検討

@Component
@ConfigurationProperties(prefix = "sendgrid")
class SendGridProperties {
    var url = ""
    var auth = ""
}