package puzzle

import puzzle.Puzzle04_2.*

class Puzzle04_2 {

    enum class 장르 {
        로맨스, 코미디, 호러
    }

    interface 영화


    class 로맨스영화: 영화

    class 코미디영화: 영화

    class 호러영화: 영화


    class 영화사(private val 공장: 영화공장) {

        fun 영화발표(종류: 장르): 영화? {

            return 공장.영화제작(종류)
        }

    }

    class 영화공장 {

        fun 영화제작(종류: 장르): 영화? {

            var 진성영화: 영화? = null

            when(종류) {

                장르.로맨스   ->  {
                    로맨스영화()
                }

                장르.코미디  ->  {
                    코미디영화()
                }

                장르.호러   ->  {
                    호러영화()
                }

            }

            return 진성영화

        }
    }

}

fun main() {

    영화사(영화공장()).영화발표(장르.로맨스)
}