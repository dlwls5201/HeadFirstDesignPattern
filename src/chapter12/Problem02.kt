package chapter12

/**
 * 3. 오리를 생성하고 데코레이터로 감싸는 부분을 따로 빼내서 캡슐화하면 어떨까요?
 * 팩토리 패턴
 */
abstract class AbstractDuckFactory {

    abstract fun createMallardDuck(): Quackable
    abstract fun createRedheadDuck(): Quackable
    abstract fun createDuckCall(): Quackable
    abstract fun createRubberDuck(): Quackable

    abstract fun createGoose(): Quackable
}

class DuckFactory : AbstractDuckFactory() {

    override fun createMallardDuck(): Quackable {
        return MallardDuck()
    }

    override fun createRedheadDuck(): Quackable {
        return RedheadDuck()
    }

    override fun createDuckCall(): Quackable {
        return DuckCall()
    }

    override fun createRubberDuck(): Quackable {
        return RubberDuck()
    }

    override fun createGoose(): Quackable {
        return GooseAdapter(Goose())
    }
}

class CountingDuckFactory : AbstractDuckFactory() {

    override fun createMallardDuck(): Quackable {
        return QuackCounter(MallardDuck())
    }

    override fun createRedheadDuck(): Quackable {
        return QuackCounter(RedheadDuck())
    }

    override fun createDuckCall(): Quackable {
        return QuackCounter(DuckCall())
    }

    override fun createRubberDuck(): Quackable {
        return QuackCounter(RubberDuck())
    }

    override fun createGoose(): Quackable {
        return QuackCounter(GooseAdapter(Goose()))
    }
}


fun main() {

    val duckFactory: AbstractDuckFactory = CountingDuckFactory()

    val mallardDuck: Quackable = duckFactory.createMallardDuck()
    val redheadDuck: Quackable = duckFactory.createRedheadDuck()
    val duckCall: Quackable = duckFactory.createDuckCall()
    val rubberDuck: Quackable = duckFactory.createRubberDuck()
    val goose: Quackable = duckFactory.createGoose()

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