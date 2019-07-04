package chapter12

/**
 * 5. 꽥꽥거리는 오리들을 하나씩 실시간으로 추적할 수 있는 기능믄 적용할 수 있을까요?
 * 옵저버 패턴
 */
//관찰의 대상
interface QuackObservable {
    fun registerObserver(observer: Observer) // 관찰자 등록
    fun notifyObservers() // 관찰자에게 연락을 돌리기 위한 메소드
}

interface Quackable04 : QuackObservable {
    fun quack()
}

//quack 갯수를 가져올 수 있는 데코레이터
class QuackCounter04(
    private val duck: Quackable04) : Quackable04 {

    companion object {

        var numberOfQuacks = 0

        fun getQuacks() = numberOfQuacks
    }

    override fun quack() {
        duck.quack()
        numberOfQuacks++
    }

    override fun registerObserver(observer: Observer) {
        duck.registerObserver(observer)
    }

    override fun notifyObservers() {
        //TODO 않해줘도 동작 않함
        duck.notifyObservers()
    }

    override fun toString(): String {
        return "Decorator $duck"
    }
}

/**
 * 보조클래스
 *
 * 등록 및 연락용 코드를 Observable 이라는 한 클래스에 캡슐화해 놓은 다음 구성을 통해서 QuackObservable 에 포함시키는 겁니다.
 * 이렇게 하면 실제 코드는 한 군데에만 작성해 놓고, QuackObservable 에서는 필요한 작업을 Observable 이라는 보조 클래스에 전부 위임하면 됩니다.
 */
class Observable(
    private val duck: QuackObservable
) : QuackObservable {
    private val observers = arrayListOf<Observer>()

    override fun registerObserver(observer: Observer) {
        observers.add(observer)
    }

    override fun notifyObservers() {
        val iterator = observers.iterator()
        while (iterator.hasNext()) {
            val observer = iterator.next()
            observer.update(duck)
        }
    }
}

//관찰자
interface Observer {
    fun update(duck: QuackObservable)
}

class Quackologist : Observer {

    override fun update(duck: QuackObservable) {
        println("Quackologist : $duck just quacked.")
    }
}

//오리 객체 생성
class MallardDuck04 : Quackable04 {
    private val observable = Observable(this)

    override fun quack() {
        println("Quack")
        notifyObservers()
    }

    override fun registerObserver(observer: Observer) {
        observable.registerObserver(observer)
    }

    override fun notifyObservers() {
        observable.notifyObservers()
    }

    override fun toString(): String {
        return "MallardDuck04"
    }
}

class RedheadDuck04 : Quackable04 {
    val observable = Observable(this)

    override fun quack() {
        println("Quack")
        notifyObservers()
    }

    override fun registerObserver(observer: Observer) {
        observable.registerObserver(observer)
    }

    override fun notifyObservers() {
        observable.notifyObservers()
    }

    override fun toString(): String {
        return "RedheadDuck04"
    }
}

class DuckCall04 : Quackable04 {
    val observable = Observable(this)

    override fun quack() {
        println("Kwak")
        notifyObservers()
    }

    override fun registerObserver(observer: Observer) {
        observable.registerObserver(observer)
    }

    override fun notifyObservers() {
        observable.notifyObservers()
    }

    override fun toString(): String {
        return "DuckCall04"
    }
}

class RubberDuck04 : Quackable04 {
    val observable = Observable(this)

    override fun quack() {
        println("Squeak")
        notifyObservers()
    }

    override fun registerObserver(observer: Observer) {
        observable.registerObserver(observer)
    }

    override fun notifyObservers() {
        observable.notifyObservers()
    }

    override fun toString(): String {
        return "RubberDuck04"
    }
}

//팩토리 패턴
abstract class AbstractDuckFactory04 {

    abstract fun createMallardDuck(): Quackable04
    abstract fun createRedheadDuck(): Quackable04
    abstract fun createDuckCall(): Quackable04
    abstract fun createRubberDuck(): Quackable04
}

class DuckFactory04 : AbstractDuckFactory04() {

    override fun createMallardDuck(): Quackable04 {
        return MallardDuck04()
    }

    override fun createRedheadDuck(): Quackable04 {
        return RedheadDuck04()
    }

    override fun createDuckCall(): Quackable04 {
        return DuckCall04()
    }

    override fun createRubberDuck(): Quackable04 {
        return RubberDuck04()
    }

}

class CountingDuckFactory04 : AbstractDuckFactory04() {

    override fun createMallardDuck(): Quackable04 {
        return QuackCounter04(MallardDuck04())
    }

    override fun createRedheadDuck(): Quackable04 {
        return QuackCounter04(RedheadDuck04())
    }

    override fun createDuckCall(): Quackable04 {
        return QuackCounter04(DuckCall04())
    }

    override fun createRubberDuck(): Quackable04 {
        return QuackCounter04(RubberDuck04())
    }

}

//컴포지트 패턴
class Flock04 : Quackable04 {

    private val mQuackers = arrayListOf<Quackable04>()

    fun add(vararg quackers: Quackable04) {
        for(quacker in quackers) {
            mQuackers.add(quacker)
        }
    }

    override fun quack() {
        for (quacker in mQuackers) {
            quacker.quack()
        }
    }

    override fun registerObserver(observer: Observer) {
        for (quacker in mQuackers) {
            quacker.registerObserver(observer)
        }
    }

    override fun notifyObservers() {
        //Quackable 객체에서 알아서 옵저버한테 연락을 돌리기 때문에 Flock 자체에서는 아무 일도 하지 않아도 됩니다.
    }
}



fun main() {

    // 오리 팩토리와 오리 생성
    val duckFactory: AbstractDuckFactory04 = CountingDuckFactory04()

    val mallardDuck: Quackable04 = duckFactory.createMallardDuck()
    val redheadDuck: Quackable04 = duckFactory.createRedheadDuck()
    val duckCall: Quackable04 = duckFactory.createDuckCall()
    val rubberDuck: Quackable04 = duckFactory.createRubberDuck()

    // 오리떼 생성
    val flockOfDucks = Flock04()
    flockOfDucks.add(mallardDuck, redheadDuck, duckCall, rubberDuck)

    println("\nDuck Simulator : With Observer")
    val quackologist = Quackologist()

    println()

    flockOfDucks.registerObserver(quackologist)
    //mallardDuck.registerObserver(quackologist)
    //redheadDuck.registerObserver(quackologist)
    //duckCall.registerObserver(quackologist)
    //rubberDuck.registerObserver(quackologist)

    simulate(flockOfDucks)
    //simulate(mallardDuck)
    //simulate(redheadDuck)
    //simulate(duckCall)
    //simulate(rubberDuck)

    println("\nThe ducks quacked ${QuackCounter04.getQuacks()} times")
}

private fun simulate(duck: Quackable04) {
    duck.quack()
}