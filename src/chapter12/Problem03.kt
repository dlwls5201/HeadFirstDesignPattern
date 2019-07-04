package chapter12

/**
 * 4. 여러 오리들에 대해서 한꺼번에 같은 작업을 적용할 수 있는 방법은 무엇일까요?
 * 컴포지트 패턴
 */
class Flock : Quackable {
    private val mQuackers = arrayListOf<Quackable>()

    fun add(vararg quackers: Quackable) {
        for(quacker in quackers) {
            mQuackers.add(quacker)
        }
    }

    override fun quack() {
        for (quacker in mQuackers) {
            quacker.quack()
        }
    }
}

fun main() {

    val duckFactory: AbstractDuckFactory = CountingDuckFactory()

    val mallardDuck: Quackable = duckFactory.createMallardDuck()
    val redheadDuck: Quackable = duckFactory.createRedheadDuck()
    val duckCall: Quackable = duckFactory.createDuckCall()
    val rubberDuck: Quackable = duckFactory.createRubberDuck()
    val goose: Quackable = duckFactory.createGoose()

    val flockOfDucks = Flock()

    flockOfDucks.add(mallardDuck, redheadDuck, duckCall, rubberDuck, goose)

    val flockOfMallards = Flock()

    val mallardOne = duckFactory.createMallardDuck()
    val mallardTwo = duckFactory.createMallardDuck()
    val mallardThree = duckFactory.createMallardDuck()
    val mallardFour = duckFactory.createMallardDuck()

    flockOfMallards.add(mallardOne, mallardTwo, mallardThree, mallardFour)

    flockOfDucks.add(flockOfMallards)

    println("모든 오리들에 대한 테스트")
   //모든 오리들에 대한 테스트
    simulate(flockOfDucks)

    println("\n물오리떼에 대해서만 테스트")
    //물오리떼에 대해서만 테스트
    simulate(flockOfMallards)

    println("\nThe ducks quacked ${QuackCounter.getQuacks()} times")
}

private fun simulate(duck: Quackable) {
    duck.quack()
}