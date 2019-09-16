package wyse

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WyseApplication

fun main(args: Array<String>) {
	runApplication<WyseApplication>(*args)
}