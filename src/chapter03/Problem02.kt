package chapter03

import chapter03.Problem02.*
import chapter03.COST.*

/**
 *  @스팀밀크 : 0.1
 *  @두유 : 0.15
 *  @모카 : 0.2
 *  @휘핑크림 : 0.1
 */
enum class COST(val cost: Double) {
    MILK(0.1), SOY(0.15), MOCHA(0.2), WHIP(0.1)
}

class Problem02 {

    //음료를 나타내는 추상 클래스
    abstract class Beverage(
        val description: String,
        var milk: Boolean,
        var soy: Boolean,
        var mocha: Boolean,
        var whip: Boolean
    ) {

        //각 음료 인스턴스마다 추가 사항에 해당하는 추가 가격까지 포함시킬 수 있도록
        //추상클래스가 아닌 구현을 하도록 하겠습니다.
        open fun cost(): Double {
            var sumCost = 0.0

            if (hasMilk()) sumCost += MILK.cost

            if (hasSoy()) sumCost += SOY.cost

            if (hasMocha()) sumCost += MOCHA.cost

            if (hasWhip()) sumCost += WHIP.cost

            return sumCost
        }

        // 첨가물에 대한 부울값
        fun hasMilk() = milk
        fun hasSoy() = soy
        fun hasMocha() = mocha
        fun hasWhip() = whip

    }

    class DarkRoast(
        milk: Boolean = false,
        soy: Boolean = false,
        mocha: Boolean = false,
        whip: Boolean = false
    ) :
        Beverage("최고의 다크 로스트", milk, soy, mocha, whip) {

        override fun cost(): Double {
            return 1.99 + super.cost()
        }

    }
}

fun main() {

    //우유 추가
    val darkRoast1 = DarkRoast(milk = true)

    //우유, 모카 추가
    val darkRoast2 = DarkRoast(milk = true, mocha = true)

    println(darkRoast1.description + " $" + darkRoast1.cost())
    println(darkRoast2.description + " $" + darkRoast2.cost())
}