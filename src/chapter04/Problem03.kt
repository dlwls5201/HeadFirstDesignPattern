package chapter04

import chapter04.Problem03.*

/**
 * 피자 프렌차이즈 사업
 *
 * 다른 스타일의 피자(뉴욕, 시카고, 캘리포니아 등)
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


    class NYStyleCheesePizza: Pizza()

    class NYStyleGreekPizza: Pizza()

    class NYStylePepperoniPizza: Pizza()


    class ChicagoStyleCheesePizza: Pizza()

    class ChicagoStyleGreekPizza: Pizza()

    class ChicagoStylePepperoniPizza: Pizza()


    //PizzaStore의 orderPizza() 메소드에 이미 주문시스템이 잘 갖춰져 있습니다. 이 주문 시스템 자체는 모는 분점에서 똑같이 진행되어야 합니다.
    //각 분점마다 달라질 수 있는것은 피자의 스타일 뿐입니다.
    abstract class PizzaStore {

        fun orderPizza(type: String): Pizza? {

            val pizza = createPizza(type)

            pizza?.run {
                prepare()
                bake()
                cut()
                box()
            }

            return pizza
        }

        abstract fun createPizza(type: String): Pizza?

    }

    class NYPizzaFactory : PizzaStore() {

        override fun createPizza(type: String):Pizza? {

            var pizza: Pizza? = null

            when(type) {

                "cheese" -> {
                    pizza = NYStyleCheesePizza()
                }
                "greek" -> {
                    pizza = NYStyleGreekPizza()
                }
                "pepperoni" -> {
                    pizza = NYStylePepperoniPizza()
                }
            }

            return pizza
        }
    }

    class CicagoPizzaFactory : PizzaStore(){

        override fun createPizza(type: String):Pizza? {

            var pizza: Pizza? = null

            when(type) {

                "cheese" -> {
                    pizza = ChicagoStyleCheesePizza()
                }
                "greek" -> {
                    pizza = ChicagoStyleGreekPizza()
                }
                "pepperoni" -> {
                    pizza = ChicagoStylePepperoniPizza()
                }
            }

            return pizza
        }
    }

}

fun main() {

    val pizzaStore: PizzaStore = NYPizzaFactory()
    pizzaStore.orderPizza("cheese")
}