package chapter04

import chapter04.Problem02.*

/**
 * 바뀌는 부분과 바뀌지 않은 부분으로 나눠 캡슐화를 하자
 *
 * 객체 생성 부분을 캘슐화 하며 객체 생성을 처리하는 클래스를 팩토리라고 부릅니다.
 */
class Problem02  {

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

    class PizzaStore(private val factory: SimplePizzaFactory) {

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

    //피자를 생성하는 작업을 한 클래스에 캡슐화시켜 놓으면 구현을 변경해야 하는 경우
    //여기저기 다 들어가서 고칠 필요가 없이 이 팩토리 클래스 하나만 고치면 됩니다.
    class SimplePizzaFactory {

        fun createPizza(type: String):Pizza? {

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

    val factory =  SimplePizzaFactory()
    PizzaStore(factory)
        .orderPizza("cheese")
}