package wyse

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoSecondApplication

fun main(args: Array<String>) {
	runApplication<DemoSecondApplication>(*args)
}
