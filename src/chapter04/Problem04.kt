package chapter04

import chapter04.Problem04.*

/**
 * 피자 프렌차이즈 사업
 *
 * 다른 스타일의 피자(뉴욕, 시카고, 캘리포니아 등)가 생기며 분점마다 다른 방식으로 피자를 만들기 시작합니다.
 * 이에 맞춰 피자 가게와 피자 제작 과정 전체를 하나로 묶어주는 프레임워가 필요해 졌습니다.
 */
class Problem04  {

    abstract class Pizza(
        val name: String,
        val dough: String,
        val sauce: String,
        val toppingsArrayList: ArrayList<String> = arrayListOf()
    ) {

        fun prepare() {
            println("prepare $name")
            println("tossing $dough")
            println("adding $sauce")
            println("adding toppings")
            for(topping in toppingsArrayList) {
                println("topping $topping")
            }
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

    class NYStyleCheesePizza: Pizza(
        name = "NYStyleCheesePizza",
        dough = "Thin Crust Dough",
        sauce = "Marinara Sauce"
    )

    //class NYStyleGreekPizza: Pizza()

    //class NYStylePepperoniPizza: Pizza()


    class ChicagoStyleCheesePizza: Pizza(
        name = "ChicagoStyleCheesePizza",
        dough = "Extra Thick Crust Dough",
        sauce = "Plum Tomato Sauce"
    )

    //class ChicagoStyleGreekPizza: Pizza()

    //class ChicagoStylePepperoniPizza: Pizza()


    //createPizza 를 추상 메소드로 선언하고,
    //각 지역마다 고유의 스타일에 맞게 PizzaStore의 서브클래스를 만들도록 합니다.
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

        /**
         *  팩토리 메소드 선언
         *
         *  구상 클래스의 인스턴스를 만드는 일을 한 객체에서 전부 처리하는 방식에서 일련의 서브클래스에서 처리하는 방식으로 넘어오게 되었습니다.
         *  Pizza 인스턴스를 만드는 일은 이제 팩토리 역활을 하는 메소드에서 맡아서 처리합니다.
         *
         *  팩토리 메소드는 객체 생성을 처리하며, 팩토리 메소드를 이용하면 객체를 생성하는 작업을 서브클래스에 캡슐화시킬 수 있습니다.
         */
        abstract fun createPizza(type: String): Pizza?

    }

    class NYPizzaStore : PizzaStore() {

        override fun createPizza(type: String):Pizza? {

            var pizza: Pizza? = null

            when(type) {

                "cheese" -> {
                    pizza = NYStyleCheesePizza()
                }
                "greek" -> {
                    //pizza = NYStyleGreekPizza()
                }
                "pepperoni" -> {
                    //pizza = NYStylePepperoniPizza()
                }
            }

            return pizza
        }
    }

    class ChicagoPizzaStore : PizzaStore(){

        override fun createPizza(type: String):Pizza? {

            var pizza: Pizza? = null

            when(type) {

                "cheese" -> {
                    pizza = ChicagoStyleCheesePizza()
                }
                "greek" -> {
                    //pizza = ChicagoStyleGreekPizza()
                }
                "pepperoni" -> {
                    //pizza = ChicagoStylePepperoniPizza()
                }
            }

            return pizza
        }
    }

}

/**
 *  주문하기
 *
 *  에단은 뉴욕풍 피자를 좋아하고
 *  조엘은 시카고풍 피자를 좋아합니다.
 */
fun main() {

    val nyStore = NYPizzaStore()
    val chicagoStyle = ChicagoPizzaStore()

    var pizza: Pizza?

    pizza = nyStore.orderPizza("cheese")
    println("Ethan ordered a : ${pizza?.name} \n")

    pizza = chicagoStyle.orderPizza("cheese")
    println("Joel ordered a : ${pizza?.name} \n")
}