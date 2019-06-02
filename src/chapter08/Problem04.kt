package chapter08

import java.io.BufferedReader
import java.io.InputStreamReader
import chapter08.Problem04.*

/**
 *  후크 사용하기
 */
class Problem04 {

    abstract class CaffeineBeverage {

        fun prepareRecipe() {
            boilWater()
            brew()
            pourInCup()

            /**
             *  후크
             *
             *  알고리즘의 특정 부분이 선택적으로 적용된다든가 하는 경우에는 후크를 쓰면 되죠.
             *  후크를 쓰면 서브클래스에서 필요한 경우에 후크를 구현할 수도 있지만, 반드시 구현해야 하는건 아니니까요.
             */
            if(customerWantsCondiments()) {
                addCondiments()
            }
        }

        abstract fun brew()

        abstract fun addCondiments()

        private fun boilWater() {
            println("물 끓이는 중")
        }

        private fun pourInCup() {
            println("컵에 따르는 중")
        }

        open fun customerWantsCondiments() = true
    }

    class CoffeeWithHook : CaffeineBeverage() {

        override fun brew() {
            println("필터를 통해서 커피를 우려내는 중")
        }

        override fun addCondiments() {
            println("설탕과 우유를 추가하는 중")
        }

        override fun customerWantsCondiments(): Boolean {
            val answer = getUserInput()

            return answer.toLowerCase().startsWith("y")
        }

        private fun getUserInput(): String {

            val answer: String?

            println("커피에 우유와 설탕을 넣어 드릴까요? (y/n) ")

            val `in` = BufferedReader(InputStreamReader(System.`in`))

            answer = `in`.readLine()

            return answer ?: "no"

        }
    }

    class TeaWithHook : CaffeineBeverage() {

        override fun brew() {
            println("차를 우려내는 중")
        }

        override fun addCondiments() {
            println("레몬을 추가하는 중")
        }

    }
}

fun main() {

    val coffeeWithHook = CoffeeWithHook()

    coffeeWithHook.prepareRecipe()
}