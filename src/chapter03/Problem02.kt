package chapter03

import chapter03.Problem02.*

/**
 * 음료 클래스에 우유, 두유, 모카, 휘핑크림 추기해 보자
 *
 * @우유 500원
 * @두유 600원
 * @모카 800원
 * @휘핑 1000원
 */
/*enum class MyCost(val cost: Int) {
    BEVERAGE(1000), MILK(500), SOY(600), MOCHA(800), WHIP(1000)
}*/

/**
 *  나중에 변경되었을 때 디자인에 영향을 미칠만한 것들을 적어 봅시다.
 */
class Problem02 {

    //음료를 나타내는 추상 클래스
    abstract class Beverage(
        val description: String,
        var milk: Boolean = false,
        var soy: Boolean = false,
        var mocha: Boolean = false,
        var whip: Boolean = false) {

        //각 음료 인스턴스마다 추가 사항에 해당하는 추가 가격까지 포함시킬 수 있도록
        //추상클래스가 아닌 구현을 하도록 하겠습니다.
        fun cost() {
            var cost = 0

            if(hasMilk()) cost += 500

            if(hasSoy()) cost += 600

            if(hasMocha()) cost += 800

            if(hasWhip()) cost += 1000

            println("cost : $cost")
        }

        // 첨가물에 대한 부울값
        fun hasMilk() = milk
        fun hasSoy() = soy
        fun hasMocha() = mocha
        fun hasWhip() = whip
    }

    class EspressoWithSteamedMilk:
        Beverage("스팀 밀크 에스프레소입니다", milk = true)


    class EspressoWithSteamedmMilkAndMocha:
        Beverage("스팀 밀크 카라멜 에스프레소입니다", milk = true, mocha = true)
}

fun main() {

    EspressoWithSteamedMilk().cost()
    EspressoWithSteamedmMilkAndMocha().cost()
}