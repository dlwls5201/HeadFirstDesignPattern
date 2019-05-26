package chapter08

class Problem01 {

    class Coffee {

        fun prepareRecipe() {
            boilWater()
            brewCoffeeGrinds()
            pourInCup()
            addSugarAndMilk()
        }

        private fun boilWater() {
            println("물 끓이는 중")
        }

        private fun brewCoffeeGrinds() {
            println("필터를 통해서 커피를 우려내는 중")
        }

        private fun pourInCup() {
            println("컵에 따르는 중")
        }

        private fun addSugarAndMilk() {
            println("설탕과 우유를 추가하는 중")
        }

    }

    class Tea {

        fun prepareRecipe() {
            boilWater()
            steepTeaBag()
            pourInCup()
            addLemon()
        }

        private fun boilWater() {
            println("물 끓이는 중")
        }

        private fun steepTeaBag() {
            println("차를 우려내는 중")
        }

        private fun pourInCup() {
            println("컵에 따르는 중")
        }

        private fun addLemon() {
            println("레몬을 추가하는 중")
        }

    }
}