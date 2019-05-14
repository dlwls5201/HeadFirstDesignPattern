package chapter04

import chapter04.Problem05.*

/**
 *  분점마다 거리가 있어 사용하는 재료의 원산지가 다릅니다.
 *  분점에서 좋은 재료를 사용하도록 관리해봅시다. 원재료를 생산하는 공장을 만들고 분점까지 재료를 배달할 수 있게 해보겠습니다.
 */
class Problem05 {

    abstract class Dough

    abstract class Sauce

    abstract class Cheese

    //우선 모든 원재료를 생산할 팩토리를 위한 인터페이스를 정의해봅니다.
    interface PizzaIngredientFactory {

        fun createDough(): Dough
        fun createSauce(): Sauce
        fun createCheese(): Cheese
        //...
    }

    class NYPizzaIngredientFactory : PizzaIngredientFactory {

        override fun createDough(): Dough {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun createSauce(): Sauce {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun createCheese(): Cheese {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    //NYStyleCheesePizza, ChicagoStyleCheesePizza 를 CheesePizza 로 합치고 재료 공장을 추가했습니다.
    abstract class Pizza(
        var dough: Dough? = null,
        var sauce: Sauce? = null,
        var cheese: Cheese? = null
        ) {

        abstract fun prepare()

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

    class CheesePizza(val ingredientFactory: PizzaIngredientFactory): Pizza() {

        override fun prepare() {

            dough = ingredientFactory.createDough()

            sauce = ingredientFactory.createSauce()

            cheese = ingredientFactory.createCheese()
        }
    }



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

        abstract fun createPizza(type: String):Pizza?
    }

    class NYPizzaStore : PizzaStore() {

        /**
         * 추상 팩토리 패턴에서는 인터페이스를 이용하여 서로 연관된, 또는 의존하는 객체를 구상 클래스를 지정하지 않고도 생성할 수 있습니다.
         * 서로 연관된 또는 의존적인 객체들로 이루어진 제품군을 생성하기 위한 인터페이스를 제공합니다. 구상 클래스는 서브클래스에 의해 만들어지죠.
         */
        override fun createPizza(type: String):Pizza? {

            var pizza: Pizza? = null

            val nyIgredientFactory = NYPizzaIngredientFactory()

            when(type) {

                "cheese" -> {
                    pizza = CheesePizza(nyIgredientFactory)
                }
                "greek" -> {
                    //pizza = GreekPizza(nyIgredientFactory)
                }
                "pepperoni" -> {
                    //pizza = PepperoniPizza(nyIgredientFactory)
                }
            }

            return pizza
        }
    }

}

fun main() {

    NYPizzaStore().orderPizza("cheese")

}