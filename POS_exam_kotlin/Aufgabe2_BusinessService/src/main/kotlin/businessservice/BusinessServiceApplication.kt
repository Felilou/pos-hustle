package businessservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BusinessServiceApplication

fun main(args: Array<String>) {
    runApplication<BusinessServiceApplication>(*args)
}
