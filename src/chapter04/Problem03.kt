package chapter04

import chapter04.Problem03.*

/**
 * 피자 프렌차이즈 사업
 *
 * 이제 여러 동네에도 PizzaStore 가 있었으면 하는 바램에 프렌차이즈를 운영하게 되었습니다.
 * 지역별로 조금씩 다른 (뉴욕과 시카고 스타일의) PizzaStore 을 만들어 보겠습니다.
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

    PizzaStore(NYPizzaFactory()).orderPizza("cheese")

    PizzaStore(ChicagoPizzaFactory()).orderPizza("cheese")
}