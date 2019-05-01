package chapter03

import chapter03.Problem03.*

class Problem03 {

    //추상 구상요소
    abstract class Beverage(
        open val description: String = "제목 없음") {

        abstract fun cost(): Double
    }

    //추상 데코레이터
    abstract class CondimentDecorator: Beverage() {
        abstract override val description: String
    }

    /**
     *  구상 구성요소
     *
     *  @에스프레소 : 1.99
     *  @하우스블랜드 : 0.89
     *  @다크로스트 : 0.99
     *  @디카페인 : 1.05
     */
    class Espresso: Beverage("에스프레소") {

        override fun cost() = 1.99
    }

    class HouseBlend: Beverage("하우스 블랜드 커피") {

        override fun cost() = 0.89
    }

    class DarkRoast: Beverage("다크로스트") {

        override fun cost() = 0.99
    }

    class Decaf: Beverage("디카페인") {

        override fun cost() = 1.05
    }

    /**
     *  @스팀 밀크 : 0.1
     *  @모카 : 0.2
     *  @두유 : 0.15
     *  @휘핑 크림 : 0.1
     */
    class SteamMilk(val beverage: Beverage): CondimentDecorator() {

        override fun cost() =  0.1 + beverage.cost()

        override val description: String
            get() = "${beverage.description}, 스팀밀크"

    }

    class Mocha(val beverage: Beverage): CondimentDecorator() {

        override fun cost() =  0.2 + beverage.cost()

        override val description: String = "${beverage.description}, 모카"

    }

    class Soy(val beverage: Beverage): CondimentDecorator() {

        override fun cost() =  0.15 + beverage.cost()

        override val description: String = "${beverage.description}, 두유"

    }

    class Whip(val beverage: Beverage): CondimentDecorator() {

        override fun cost() =  0.1 + beverage.cost()

        override val description: String
             get() = "${beverage.description}, 휘핑크림"

    }
}

fun main() {

    val beverage = Espresso()
    println("${beverage.description} $${beverage.cost()}")

    var beverage2 = DarkRoast()
    //TODO 이게 무슨 일이지??
    beverage2 = Mocha(beverage2)
}