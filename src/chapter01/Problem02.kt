package chapter01

import chapter01.Problem02.*

/**
 *  인터페이스로 나눠서 구분하기
 *
 *  Flyable, Quackable 인터페이스를 사용하면 코드를 재사용할 수 없다.
 *
 *  하지만 행동을 바꿀 때마다 그 행동이 정의되어 있는 모든 서브클래스들을 전부 수정해야한다.
 */
class Problem02 {

    interface Flyable {
        fun fly()
    }

    interface Quackable {
        fun quack()
    }

    abstract class Duck {

        abstract fun display()

        fun swim() {
            println("수영을 한다")
        }

    }

    class RedHeadDuck: Duck(), Flyable, Quackable {

        override fun display() {
            println("-- 빨간 머리 오리")
        }

        override fun quack() {
            println("꽥꽥 소리를 낸다")
        }

        override fun fly() {
            println("날 수 있다.")
        }
    }

    class RubberDuck: Duck(), Quackable {

        override fun display() {
            println("-- 고무 오리")
        }
        override fun quack() {
            println("삑삑 소리를 낸다")
        }
    }

    class Decoy: Duck() {

        override fun display() {
            println("-- 가짜 오리")
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
    }

    with(Decoy()) {
        display()
        swim()
    }

}