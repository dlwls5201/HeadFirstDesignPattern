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
        var milk: Boolean = false,
        var soy: Boolean = false,
        var mocha: Boolean = false,
        var whip: Boolean = false) {

        //각 음료 인스턴스마다 추가 사항에 해당하는 추가 가격까지 포함시킬 수 있도록
        //추상클래스가 아닌 구현을 하도록 하겠습니다.
        open fun cost(): Double {
            var sumCost = 0.0

            if(hasMilk()) sumCost += MILK.cost

            if(hasSoy()) sumCost += SOY.cost

            if(hasMocha()) sumCost += MOCHA.cost

            if(hasWhip()) sumCost += WHIP.cost

            return sumCost
        }

        // 첨가물에 대한 부울값
        fun hasMilk() = milk
        fun hasSoy() = soy
        fun hasMocha() = mocha
        fun hasWhip() = whip

    }

    class DarkRoastWithSteamMilk:
        Beverage("최고의 다크 로스트, 스팀밀크", milk = true) {

        override fun cost(): Double {
            return 1.99 + super.cost()
        }

    }

    class DarkRoastWithSteamedMilkAndMocha:
        Beverage("최고의 다크 로스트, 스팀밀크, 모카", milk = true, mocha = true) {

        override fun cost(): Double {
            return 1.99 + super.cost()
        }
    }
}

fun main() {

    val darkRoast = DarkRoastWithSteamMilk()
    val darkRoast2 = DarkRoastWithSteamedMilkAndMocha()

    println(darkRoast.description + " $" + darkRoast.cost())
    println(darkRoast2.description + " $" + darkRoast2.cost())
}