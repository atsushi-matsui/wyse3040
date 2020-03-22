package wyse

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate

@Configuration
class RestConfig(
        private val myObjectMapper: ObjectMapper,
        private val mappingJacksonHttpConverter: MappingJackson2HttpMessageConverter
) {

    @Bean
    fun myRestTemplate(): RestTemplate {
        return RestTemplate(listOf(mappingJacksonHttpConverter))
    }

    @Bean
    fun mappingJacksonHttpConverter(): MappingJackson2HttpMessageConverter {
        return MappingJackson2HttpMessageConverter(myObjectMapper)
    }

    @Bean
    fun myObjectMapper(): ObjectMapper {
        return Jackson2ObjectMapperBuilder.json()
                .build()
    }
}
