package puzzle

class Puzzle04_1 {

    enum class 장르 {
        로맨스, 코미디, 호러
    }

    interface 영화


    class 로맨스영화: 영화

    class 코미디영화: 영화

    class 호러영화: 영화


    fun 영화발표(종류: 장르): 영화? {

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

fun main() {

}