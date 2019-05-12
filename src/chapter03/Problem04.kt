package chapter03

import chapter03.Problem04.*

class Problem04 {

    interface Beverage{

        val description: String

        fun cost(): Double
    }


    class Espresso: Beverage {

        override var description = "에스프레소"

        override fun cost() = 1.99
    }

    // cost 함수를 오버라이드 해주지 않으면 에러가 발생합니다.
    class SteamMilk1(val beverage: Beverage): Beverage {

        override fun cost() =  0.1 + beverage.cost()

        override val description: String
            get() = "${beverage.description}, 스팀밀크"

    }

    // cost 함수를 오버라이드 해주지 않아도 동작합니다.
    class SteamMilk2(val beverage: Beverage): Beverage by beverage {

        //override fun cost() = 0.1 + beverage.cost()

        override val description: String
            get() = "${beverage.description}, 스팀밀크"
    }
}

fun main() {

    //에스프레소 주문
    var beverage: Beverage = Espresso()
    beverage = SteamMilk2(beverage)

    println("${beverage.description} $${beverage.cost()}")

}