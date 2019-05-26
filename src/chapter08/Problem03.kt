package chapter08

/**
 *  커피와 티를 만드는 알고리즘은 같다.
 *  틀(템플릿)을 만들어서 사용하기
 */
class Problem03 {

    abstract class CaffeineBeverage {

        fun prepareRecipe() {
            boilWater()
            brew()
            pourInCup()
            addCondiments()
        }

        abstract fun brew()

        abstract fun addCondiments()

        private fun boilWater() {
            println("물 끓이는 중")
        }

        private fun pourInCup() {
            println("컵에 따르는 중")
        }
    }

    class Coffee : CaffeineBeverage() {

        override fun brew() {
            println("필터를 통해서 커피를 우려내는 중")
        }

        override fun addCondiments() {
            println("설탕과 우유를 추가하는 중")
        }
    }

    class Tea : CaffeineBeverage() {

        override fun brew() {
            println("차를 우려내는 중")
        }

        override fun addCondiments() {
            println("레몬을 추가하는 중")
        }

    }
}