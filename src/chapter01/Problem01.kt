package chapter01

import chapter01.Problem01.*

/**
 *  문제
 *  fly() 기능이 추가되면 모든 하위 클래스에 추가된다.
 *  fly() 기능이 필요없는 부분에도 구현이 된다.
 */
class Problem01 {

    abstract class Duck {

        abstract fun display()

        open fun quack() {
            println("꽥꽥 소리를 낸다")
        }

        fun swim() {
            println("수영을 한다")
        }

        //TODO 1. 날기 기능이 추가
        open fun fly() {
            println("날 수 있다.")
        }
    }

    class RedHeadDuck: Duck() {

        override fun display() {
            println("-- 빨간 머리 오리")
        }
    }

    //TODO 2. 고무오리는 날 수 없습니다.
    class RubberDuck: Duck() {

        override fun display() {
            println("-- 고무 오리")
        }

        override fun quack() {
            println("삑삑 소리를 낸다")
        }

        override fun fly() {
            //아무것도 하지 않도록 오버라이드
        }
    }

    class Decoy: Duck() {

        override fun display() {
            println("-- 가짜 오리")
        }

        override fun quack() {
            //아무것도 하지 않도록 오버라이드
        }
        override fun fly() {
            //아무것도 하지 않도록 오버라이드
        }
    }

}

fun main() {

    with(RedHeadDuck()) {
        display()
        quack()
        swim()
        fly()
    }

    with(RubberDuck()) {
        display()
        quack()
        swim()
        //TODO 3. fly() 사용할 수 있지만 기능이 없다.
        fly()
    }

    with(Decoy()) {
        display()
        quack()
        swim()
        //TODO 3. fly() 사용할 수 있지만 기능이 없다.
        fly()
    }
}