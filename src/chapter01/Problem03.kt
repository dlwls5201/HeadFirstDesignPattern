package chapter01

import chapter01.Problem03.*

/**
 *  디자인 원칙
 *
 *  1. 애플리케이션에서 달라지는 부분을 찾아내고, 달라지지 않는 부분으로부터 분리시킨다.
 *  2. 상속보다는 구성을 활용한다.
 *  3. 구현이 아닌 인터페이스에 맞춰서 프로그래밍 한다.
 *
 */
class Problem03 {

    //FlyBehavior 에 따른 두 개의 행동 구현 클래스
    interface FlyBehavior {
        fun fly()
    }

    class FlyWithWings: FlyBehavior {
        override fun fly() {
            println("날 수 있다.")
        }
    }

    class FlyNoWay: FlyBehavior {
        override fun fly() {
            println("날 지 못한다.")
        }
    }

    //TODO 2. 로켓 추진으로 날기 추가
    class FlyRocketPowered: FlyBehavior {
        override fun fly() {
            println("로켓 추진으로 납니다.")
        }

    }


    //QuackBehavior 에 따른 두 개의 행동 구현 클래스
    interface QuackBehavior {
        fun quack()
    }

    class Quack: QuackBehavior {
        override fun quack() {
            println("꽥꽥 소리를 낸다.")
        }
    }

    class Squack: QuackBehavior {
        override fun quack() {
            println("삑삑 소리를 낸다")
        }
    }

    class MuteQuack: QuackBehavior {
        override fun quack() {
            println("<<< 조용 >>>")
        }
    }


    abstract class Duck {

        private var flyBehavior: FlyBehavior? = null
        private var quackBehavior: QuackBehavior? = null

        abstract fun display()

        fun setFlyBehavior(flyBehavior: FlyBehavior) {
            this.flyBehavior = flyBehavior
        }

        fun setQuackBehavior(quackBehavior: QuackBehavior) {
            this.quackBehavior = quackBehavior
        }

        fun performFly() {
            flyBehavior?.fly()
        }

        fun performQuack() {
            quackBehavior?.quack()
        }

        fun swim() {
            println("수영을 한다")
        }

    }

    class RedHeadDuck: Duck() {

        override fun display() {
            println("-- 빨간 머리 오리")
        }
    }

    class RubberDuck: Duck() {

        override fun display() {
            println("-- 고무 오리")
        }
    }

    class Decoy: Duck() {

        override fun display() {
            println("-- 가짜 오리")
        }

    }

    //TODO 1. model duck 추가하기
    class ModelDuck: Duck() {

        init {

            //모형 오리는 못납니다.
            setFlyBehavior(FlyNoWay())
            //모형 오리는 꽥꽥 소리를 냅니다.
            setQuackBehavior(Quack())
        }

        override fun display() {
            println("-- 모형 오리")
        }

    }


}

fun main() {

    with(RedHeadDuck()) {
        display()
        swim()

        //날 수 있다.
        setFlyBehavior(FlyWithWings())
        //꽥꽥 소리를 낸다.
        setQuackBehavior(Quack())

        performFly()
        performQuack()
    }

    with(RubberDuck()) {
        display()
        swim()

        //날 수 없다.
        setFlyBehavior(FlyNoWay())
        //삑삑 소리를 낸다.
        setQuackBehavior(Squack())

        performFly()
        performQuack()
    }

    with(Decoy()) {
        display()
        swim()

        //날 수 없다.
        setFlyBehavior(FlyNoWay())
        //소리를 못 낸다.
        setQuackBehavior(MuteQuack())

        performFly()
        performQuack()
    }

    with(ModelDuck()) {
        display()
        swim()

        performFly()
        performQuack()

        //로켓 추진을 달아 주었습니다.
        setFlyBehavior(FlyRocketPowered())
        performFly()
    }

}