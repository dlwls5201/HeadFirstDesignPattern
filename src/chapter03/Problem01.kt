package chapter03

/**
 *  배운 디자인 원칙 중 지켜지지 않은 것은 무엇일까요?
 */
//음료를 나타내는 추상 클래스
abstract class Beverage(var description: String) {

    abstract fun cost()
}

class EspressoWithSteamedmMilk: Beverage("스팀 밀크 에스프레소입니다") {

    override fun cost() {
        println("2000원")
   }
}

class EspressoWithSteamedmMilkAndCaramel: Beverage("스팀 밀크 카라멜 에스프레소입니다") {

    override fun cost() {
        println("2500원")
    }
}