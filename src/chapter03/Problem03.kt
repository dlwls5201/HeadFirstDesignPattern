package chapter03

import chapter03.Problem03.*

class Problem03 {

    //추상 구상요소
    //Component
    abstract class Beverage(
        //getDescription 은 구현되어 있습니다.
        open val description: String = "제목 없음") {

        //cost 는 서브클래스에서 구현합나다.
        abstract fun cost(): Double
    }

    //추상 데코레이터
    //Decorator
    abstract class CondimentDecorator: Beverage() {
        abstract override val description: String
    }

    /**
     *  ConcreteComponent에 새로운 행동을 동적으로 추가하게 됩니다.
     *
     *  구성 요소를 나타내는 구상 클래스 (구상 구성요소)
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
     *  ConcreteDecorator에는 그 객체가 장식하고 있는것을 위한 인스턴스 변수가 있습니다.
     *
     *  @스팀밀크 : 0.1
     *  @모카 : 0.2
     *  @두유 : 0.15
     *  @휘핑크림 : 0.1
     */
    class SteamMilk(val beverage: Beverage): Beverage() {

        override fun cost() =  0.1 + beverage.cost()

        override val description: String
            get() = "${beverage.description}, 스팀밀크"

    }

    class Mocha(val beverage: Beverage): Beverage() {

        override fun cost() =  0.2 + beverage.cost()

        override val description: String
            get() = "${beverage.description}, 모카"

    }

    class Soy(val beverage: Beverage): Beverage() {

        override fun cost() =  0.15 + beverage.cost()

        override val description: String
            get() = "${beverage.description}, 두유"

    }

    class Whip(val beverage: Beverage): Beverage() {

        override fun cost() =  0.1 + beverage.cost()

        override val description: String
             get() = "${beverage.description}, 휘핑크림"

    }
}

fun main() {

    //에스프레소 주문
    val beverage: Beverage = Espresso()
    println("${beverage.description} $${beverage.cost()}")

    //다크로스트에 모카 2개 휘핑크림 1개 추가
    var beverage2: Beverage = DarkRoast()
    beverage2 = Mocha(beverage2)
    beverage2 = Mocha(beverage2)
    beverage2 = Whip(beverage2)
    println("${beverage2.description} $${beverage2.cost()}")

    //하우스블랜드에 소이, 모카, 휘핑크림 추가
    var beverage3: Beverage = HouseBlend()
    beverage3 = Soy(beverage3)
    beverage3 = Mocha(beverage3)
    beverage3 = Whip(beverage3)
    println("${beverage3.description} $${beverage3.cost()}")
}