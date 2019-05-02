package chapter03

import chapter03.Problem02.*
import chapter03.COST.*

/**
 *  @스팀밀크 : 0.1
 *  @두유 : 0.15
 *  @모카 : 0.2
 *  @휘핑크림 : 0.1
 */
enum class COST(val cost: Double){
    MILK(0.1), SOY(0.15), MOCHA(0.2), WHIP(0.1)
}

/**
 *  Beverage 라는 기볼 클래스의 각 음료에 우유,두유, 모카, 휘핑크림이
 *  들어가는지 여부를 나타내는 인스턴스 변수를 추가해 봅시다.
 *
 *  프로젝트의 요구사항이나 요인 중에서 나중에 변경되었을 때 디자인에 영향을 미칠만한 것들을 적어 봅시다.
 */
class Problem02 {

    //음료를 나타내는 추상 클래스
    abstract class Beverage(
        val description: String,
        var milk: Boolean,
        var soy: Boolean,
        var mocha: Boolean,
        var whip: Boolean) {

        //각 음료 인스턴스마다 추가 사항에 해당하는 추가 가격까지 포함시킬 수 있도록
        //추상클래스가 아닌 구현을 하도록 하겠습니다.
        fun cost() {
            var cost = 0.0

            if(hasMilk()) cost += 0.1

            if(hasSoy()) cost += 0.15

            if(hasMocha()) cost += 0.2

            if(hasWhip()) cost += 0.1

            println("cost : $cost")
        }

        // 첨가물에 대한 부울값
        fun hasMilk() = milk
        fun hasSoy() = soy
        fun hasMocha() = mocha
        fun hasWhip() = whip

    }

    class EspressoWithSteamedMilk:
        Beverage("에스프레소, 스팀밀크",
            true, false, false, false)


    class EspressoWithSteamedMilkAndMocha:
        Beverage("에스프레소, 스팀밀크, 모카",
            true, false, true, false)
}

fun main() {

    EspressoWithSteamedMilk().cost()
    EspressoWithSteamedMilkAndMocha().cost()
}