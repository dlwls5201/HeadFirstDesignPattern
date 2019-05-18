package chapter07

import chapter07.Problem02.*

/**
 * 오리가 부족하여 칠면조를 대신 사용해야 되는 상홥입니다. 물론 인터페이스가 다르기 때문에 칠면조 객체를 바로 사용할 수는 없습니다.
 */
class Problem02 {

    interface Duck {
        fun quack()
        fun fly()
    }

    class MallardDuck: Duck {
        override fun quack() {
            println("Quack")
        }

        override fun fly() {
            println("I'm flying")
        }
    }

    //칠면조를 만나 볼까요?
    interface Turkey {
        fun gobble() //칠면조는 골골거리는 소리를 냅니다.
        fun fly() //칠면도는 날 수 있긴 합니다.
    }

    class WildTurkey: Turkey {
        override fun gobble() {
            println("Gobble gobble")
        }

        override fun fly() {
            println("I'm flying a short distance")
        }
    }

    //칠면조를 오리로 만들어 주는 어댑터
    class TurkeyAdapter(val turkey: Turkey): Duck {
        override fun quack() {
            turkey.gobble()
        }

        override fun fly() {
            for(i in 0..5) {
                turkey.fly()
            }
        }
    }
}

fun main() {
    
    val duck = MallardDuck()
    
    val turkey = WildTurkey()
    val turkeyAdapter = TurkeyAdapter(turkey)
    
    println("--- turkey says")
    turkey.gobble()
    turkey.fly()

    println("--- duck says")
    duck.quack()

    println("--- turkeyAdapter says")
    turkeyAdapter.quack()
    turkeyAdapter.fly()
}
