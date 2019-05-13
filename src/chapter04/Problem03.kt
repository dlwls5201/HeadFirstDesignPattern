package chapter04

import chapter04.Problem03.*

/**
 *
 */
class Problem03  {

    abstract class Pizza {

        fun prepare() {
            println("prepare")
        }

        fun bake() {
            println("bake")
        }

        fun cut() {
            println("cut")
        }

        fun box() {
            println("box")
        }
    }

    class CheesePizza: Pizza()

    class GreekPizza: Pizza()

    class PepperoniPizza: Pizza()

    class PizzaStore(private val factory: PizzaFactory) {

        fun orderPizza(type: String): Pizza? {

            val pizza = factory.createPizza(type)

            pizza?.run {
                prepare()
                bake()
                cut()
                box()
            }

            return pizza
        }

    }

    abstract class PizzaFactory {

        abstract fun createPizza(type: String):Pizza?
    }

    class NYPizzaFactory: PizzaFactory() {

        override fun createPizza(type: String):Pizza? {

            var pizza: Pizza? = null

            when(type) {

                "cheese" -> {
                    pizza = CheesePizza()
                }
                "greek" -> {
                    pizza = GreekPizza()
                }
                "pepperoni" -> {
                    pizza = PepperoniPizza()
                }
            }

            return pizza
        }

    }

    class ChicagoPizzaFactory: PizzaFactory() {

        override fun createPizza(type: String):Pizza? {

            var pizza: Pizza? = null

            when(type) {

                "cheese" -> {
                    pizza = CheesePizza()
                }
                "greek" -> {
                    pizza = GreekPizza()
                }
                "pepperoni" -> {
                    pizza = PepperoniPizza()
                }
            }

            return pizza
        }

    }

}

fun main() {

    PizzaStore(NYPizzaFactory())
        .orderPizza("cheese")
}