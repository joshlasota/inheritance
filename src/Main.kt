import java.util.*

open class Customer(
    var customerName: String,
    var customerPhone: String,
    var customerAddress: String,
    var squareFootage: Double
)

class Commercial(
    customerName: String,
    customerPhone: String,
    customerAddress: String,
    squareFootage: Double,
    var propertyName: String,
    var multiProperty: Boolean = false
) : Customer(customerName, customerPhone, customerAddress, squareFootage) {
    companion object {
        const val COMMERCIAL_RATE = 5.0
    }

    fun calculateWeeklyCharges(): Double {
        val baseCharge = squareFootage / 1000 * COMMERCIAL_RATE
        val totalCharge = if (multiProperty) baseCharge * 0.9 else baseCharge
        return totalCharge
    }
}

class Residential(
    customerName: String,
    customerPhone: String,
    customerAddress: String,
    squareFootage: Double,
    var senior: Boolean = false
) : Customer(customerName, customerPhone, customerAddress, squareFootage) {
    companion object {
        const val RESIDENTIAL_RATE = 6.0
    }

    fun calculateWeeklyCharges(): Double {
        val baseCharge = squareFootage / 1000 * RESIDENTIAL_RATE
        val totalCharge = if (senior) baseCharge * 0.85 else baseCharge
        return totalCharge
    }
}

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        var choice: Int
        do {
            println("Menu:")
            println("1. Residential Customer")
            println("2. Commercial Customer")
            println("3. Done")
            println("Enter your choice:")
            choice = scanner.nextInt()
            scanner.nextLine()

            when (choice) {
                1 -> {
                    println("Enter customer name:")
                    val name = scanner.nextLine()
                    println("Enter customer phone:")
                    val phone = scanner.nextLine()
                    println("Enter customer address:")
                    val address = scanner.nextLine()
                    println("Enter square footage:")
                    val footage = scanner.nextDouble()
                    println("Is the customer a senior? (true/false):")
                    val senior = scanner.nextBoolean()

                    val residentialCustomer = Residential(name, phone, address, footage, senior)
                    displayQuote(residentialCustomer)
                }
                2 -> {
                    println("Enter customer name:")
                    val name = scanner.nextLine()
                    println("Enter customer phone:")
                    val phone = scanner.nextLine()
                    println("Enter customer address:")
                    val address = scanner.nextLine()
                    println("Enter square footage:")
                    val footage = scanner.nextDouble()
                    println("Enter property name:")
                    scanner.nextLine() // Consume newline
                    val propName = scanner.nextLine()
                    println("Does the customer have multiple properties? (true/false):")
                    val multiProperty = scanner.nextBoolean()

                    val commercialCustomer = Commercial(name, phone, address, footage, propName, multiProperty)
                    displayQuote(commercialCustomer)
                }
                3 -> println("Exiting...")
                else -> println("Invalid choice. Please enter 1, 2, or 3.")
            }
        } while (choice != 3)

        scanner.close()
    }

    private fun displayQuote(customer: Customer) {
        println("Customer Name: ${customer.customerName}")
        println("Customer Phone: ${customer.customerPhone}")
        println("Customer Address: ${customer.customerAddress}")
        println("Square Footage: ${customer.squareFootage}")
        if (customer is Residential) {
            println("Residential Rate: ${Residential.RESIDENTIAL_RATE}")
            println("Senior: ${customer.senior}")
            println("Weekly Charge: $${customer.calculateWeeklyCharges()}")
        } else if (customer is Commercial) {
            println("Property Name: ${customer.propertyName}")
            println("Commercial Rate: ${Commercial.COMMERCIAL_RATE}")
            println("Multiple Properties: ${customer.multiProperty}")
            println("Weekly Charge: $${customer.calculateWeeklyCharges()}")
        }
        println()
    }
}
