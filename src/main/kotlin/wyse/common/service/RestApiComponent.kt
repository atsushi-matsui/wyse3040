package wyse.common.service

import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.web.client.RestTemplate
import wyse.common.model.request.SendGridRequest
import wyse.common.model.response.SendGridResponse
import java.lang.RuntimeException
import java.net.URI

class RestApiComponent(
    private val myRestTemplate: RestTemplate
) {
    fun <T: SendGridRequest, R: SendGridResponse> restApiComponent(request: T, response: R): R {
        val requestEntity = RequestEntity
                // FIXME configに設定を書き出す(restTemplateにも設定しているので不要なら削除を検討)
                .post(URI.create("https://api.sendgrid.com/v3/mail/send"))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                // FIXME api-keyを指定するようにする
                .header("Authorization", "")
                .body(request)

        // FIXME runCatchingに変更を検討
        try {
            val responseEntity = myRestTemplate.exchange(
                    URI.create("https://api.sendgrid.com/v3/mail/send"),
                    HttpMethod.POST,
                    requestEntity,
                    response.javaClass
            )

            return responseEntity.body
                    // FIXME 意味のある例外に変更
                    ?: throw RuntimeException("")
        } catch (e: Throwable) {
            throw e
        }


    }
}