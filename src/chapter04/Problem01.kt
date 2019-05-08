package chapter04

class Problem01  {

    interface Pizza {

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

    class CheesePizza: Pizza

    class GreekPizza: Pizza

    class PepperoniPizza: Pizza

    fun orderPizza(type: String):Pizza? {

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

        pizza?.run {
            prepare()
            bake()
            cut()
            box()
        }
        return pizza
    }

}

fun main() {

    val problem01 = Problem01()
    problem01.orderPizza("cheese")
}