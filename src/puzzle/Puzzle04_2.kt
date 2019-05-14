package puzzle

import puzzle.Puzzle04_2.*

/**
 * 팩토리 메소드 패턴
 */
class Puzzle04_2 {

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




    abstract class 영화팩토리 {

        abstract fun 영화제작(장르: 영화장르): 영화
    }

    class 한국영화팩토리: 영화팩토리() {
        override fun 영화제작(장르: 영화장르): 영화 {
            return when(장르) {
                영화장르.로맨스  ->  {
                    로맨스영화()
                }
                영화장르.호러 ->  {
                    호러영화()
                }
            }
        }
    }

    class 미국영화팩토리: 영화팩토리() {
        override fun 영화제작(장르: 영화장르): 영화 {
            return when(장르) {
                영화장르.로맨스  ->  {
                    로맨스영화()
                }
                영화장르.호러 ->  {
                    호러영화()
                }
            }
        }
    }

    class 영화공장(val 영화사: 영화팩토리) {

        fun 영화제작(장르: 영화장르): 영화 {
            return 영화사.영화제작(장르).apply { 제작(장르) }
        }
    }
}

fun main() {
    영화공장(한국영화팩토리()).영화제작(영화장르.로맨스)
}