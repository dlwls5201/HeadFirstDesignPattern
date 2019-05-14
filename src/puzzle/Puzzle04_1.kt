package puzzle

import puzzle.Puzzle04_1.*

class Puzzle04_1 {

    enum class 영화장르 {
        로맨스, 호러
    }

    abstract class 영화 {

        abstract fun 연출()

        abstract fun 연기()

        fun 제작(장르: 영화장르) {
            연출()
            연기()
            println("$장르 영화 제작")
        }
    }

    class 로맨스영화: 영화() {
        override fun 연출() {
            println("로맨스 연출을 한다.")
        }
        override fun 연기() {
            println("로맨스 연기를 한다.")
        }
    }

    class 호러영화: 영화() {
        override fun 연출() {
            println("호러 연출을 한다.")
        }
        override fun 연기() {
            println("호러 연기를 한다.")
        }
    }

    class 영화공장 {

        fun 영화제작(장르: 영화장르): 영화 {

            return when(장르) {
                영화장르.로맨스 ->  {
                    로맨스영화()
                }
                영화장르.호러 ->  {
                    호러영화()
                }
            }.apply { 제작(장르) }
        }
    }
}

fun main() {
    영화공장().영화제작(영화장르.로맨스)
}