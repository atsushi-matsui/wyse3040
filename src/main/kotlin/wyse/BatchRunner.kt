package wyse

import org.springframework.boot.CommandLineRunner

class BatchRunner(
        private val service: BatchProcess
       // private val context: ConfigurableApplicationContext
): CommandLineRunner {
    override fun run(vararg args: String?) {
        service.process()
       // context.getBean(service.javaClass.name)

    }



}