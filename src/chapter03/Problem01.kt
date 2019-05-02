package chapter03

/**
 *  배운 디자인 원칙 중 지켜지지 않은 것은 무엇일까요?
 *  2가지 원칙을 제대로 따르지 않고 있습니다.
 */
class Problem01 {

    //음료를 나타내는 추상 클래스
    abstract class Beverage(
        val description: String) {

        abstract fun cost(): Double
    }

    class EspressoWithSteamedmMilk:
        Beverage("에스프레소, 스팀밀크") {

        override fun cost() = 1.00
    }

    class EspressoWithSteamedmMilkAndMocha:
        Beverage("에스프레소, 스팀밀크, 모카") {

        override fun cost() = 1.55
    }
}

fun main() {

}
