package chapter07

/**
 * 오리처럼 걷고 꽥꽥거린다면 반드시 오리라고 할 수 없습니다.
 * 오리 어댑터로 감싼 칠면조일 수도 있습니다...
 */
class Problem01 {

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
        fun fly() //칠면조는 날 수 있긴 합니다.
    }

    class WildTurky: Turkey {
        override fun gobble() {
            println("Gobble gobble")
        }

        override fun fly() {
            println("I'm flying a short distance")
        }
    }
}

fun main() {

}
