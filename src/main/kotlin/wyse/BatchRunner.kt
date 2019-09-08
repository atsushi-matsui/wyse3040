package wyse

import org.springframework.boot.CommandLineRunner
import org.springframework.context.ConfigurableApplicationContext

class BatchRunner(
        private val servise: BatchProcess
       // private val context: ConfigurableApplicationContext
): CommandLineRunner {
    override fun run(vararg args: String?) {
        servise.process()
       // context.getBean(servise.javaClass.name)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }



}