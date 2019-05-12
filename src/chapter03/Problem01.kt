package chapter03

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

    class EspressoWithSteamedMilkAndMocha:
        Beverage("에스프레소, 스팀밀크, 모카") {

        override fun cost() = 1.55
    }
}

fun main() {

}
