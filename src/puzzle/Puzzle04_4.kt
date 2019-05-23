package puzzle

import puzzle.Puzzle04_4.*

class Puzzle04_4 {

    enum class 장르 {
        로맨스, 코미디, 호러
    }

    interface 영화


    class 한국로맨스영화: 영화

    class 한국코미디영화: 영화

    class 한국호러영화: 영화


    class 일본로맨스영화: 영화

    class 일본코미디영화: 영화

    class 일본호러영화: 영화


    abstract class 영화사 {

        fun 영화발표(종류: 장르): 영화? {

            val 진성영화 = 영화제작(종류)

            return 진성영화
        }

        /**
         *  팩토리 메소드 패턴
         *
         *  객체를 생성하기 위한 인터페이스를 정의하는데, 어떤 클래스의 인스턴스를 만들지는 서브클래스에서 결정하게 만듭니다.
         *  팩토리 메소드 패턴을 이용하면 클래스의 인스턴스를 만드는 일을 서브클래스에게 맡깁니다.
         */
        abstract fun 영화제작(종류: 장르): 영화?

    }

    class 한국영화사: 영화사() {

        override fun 영화제작(종류: 장르): 영화? {

            var 진성영화: 영화? = null

            when(종류) {

                장르.로맨스   ->  {
                    한국로맨스영화()
                }

                장르.코미디  ->  {
                    한국코미디영화()
                }

                장르.호러   ->  {
                    한국호러영화()
                }

            }

            return 진성영화

        }
    }

    class 일본영화사: 영화사() {

        override fun 영화제작(종류: 장르): 영화? {

            var 진성영화: 영화? = null

            when(종류) {

                장르.로맨스   ->  {
                    일본로맨스영화()
                }

                장르.코미디  ->  {
                    일본코미디영화()
                }

                장르.호러   ->  {
                    일본호러영화()
                }

            }

            return 진성영화

        }
    }

}

fun main() {

    한국영화사().영화발표(장르.로맨스)
}