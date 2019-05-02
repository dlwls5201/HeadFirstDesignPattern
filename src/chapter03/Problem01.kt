package chapter03

/**
 *  배운 디자인 원칙 중 지켜지지 않은 것은 무엇일까요?
 *
 *  1. 애플리케이션에서 달라지는 부분을 찾아내고, 달라지지 않는 부분으로부터 분리시킨다.
 *  2. 구현이 아닌 인터페이스에 맞춰서 프로그래밍한다.
 *  3. 상속보다는 구성을 활용한다.
 *  4. 서로 상호작용을 하는 객체 사이에서는 가능하면 느슨하게 결합하는 디자인을 사용해야한다.
 *
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
