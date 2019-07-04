package chapter12


interface Quackable {
    fun quack()
}

/**
 * 2. 오라 클래스는 그대로 두면서 오리가 꽥소리를 낸 회수를 세려면 어떻게 해야 할까요?
 * 데코레이터 패턴
 */
class QuackCounter(
    private val duck: Quackable) : Quackable {

    companion object {

        var numberOfQuacks = 0

        fun getQuacks() = numberOfQuacks
    }

    override fun quack() {
        duck.quack()
        numberOfQuacks++
    }
}

class MallardDuck : Quackable {
    override fun quack() {
        println("Quack")
    }
}

class RedheadDuck : Quackable {
    override fun quack() {
        println("Quack")
    }
}

class DuckCall : Quackable {
    override fun quack() {
        println("Kwak")
    }
}

class RubberDuck : Quackable {
    override fun quack() {
        println("Squeak")
    }
}

class Goose {
    fun hock() {
        println("Honk")
    }
}

/**
 * 1. Duck을 집어넣을 수 있는 곳이라면 어디든지 Goose도 집어 넣을 수 있게 해주는
 * 어댑터 패턴
 */
class GooseAdapter(
    private val goose: Goose) : Quackable {

    override fun quack() {
        goose.hock()
    }

}

fun main() {
    val mallardDuck: Quackable = QuackCounter(MallardDuck())
    val redheadDuck: Quackable = QuackCounter(RedheadDuck())
    val duckCall: Quackable = QuackCounter(DuckCall())
    val rubberDuck: Quackable = QuackCounter(RubberDuck())
    val goose: Quackable = QuackCounter(GooseAdapter(Goose()))

    println("Duck Simulator")

    simulate(mallardDuck)
    simulate(redheadDuck)
    simulate(duckCall)
    simulate(rubberDuck)
    simulate(goose)

    println("The ducks quacked ${QuackCounter.getQuacks()} times")
}

private fun simulate(duck: Quackable) {
    duck.quack()
}