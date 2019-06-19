package chapter10

class Problem01 {

    enum class STATE {
        SOLD_OUT, NO_QUARTER, HAS_QUARTER, SOLD
    }

    class GumballMachine(var count: Int = 0) {

        var state = STATE.SOLD_OUT

        init {
            if(count > 0)
                state = STATE.NO_QUARTER
        }

        /**
         * 행동 구현
         */
        //동전이 투입된 경우
        fun insertQuater() {
            when(state) {
                STATE.HAS_QUARTER -> {
                    println("동전은 한 개만 넣어주세요.")
                }
                STATE.NO_QUARTER -> {
                    state = STATE.HAS_QUARTER
                    println("동전을 넣으셨습니다.")
                }
                STATE.SOLD_OUT -> {
                    println("매진되었습니다. 다음 기회에 이용해주세요.")
                }
                STATE.SOLD -> {
                    println("잠깐만 기다려 주세요. 알맹이가 나가고 있습니다.")
                }
            }
        }

        //사용자가 동전을 반환 받으려고 하는 경우
        fun ejectQuarter() {
            when(state) {
                STATE.HAS_QUARTER -> {
                    println("동전이 반환됩니다.")
                    state = STATE.NO_QUARTER
                }
                STATE.NO_QUARTER -> {
                    println("동전을 넣어주세요.")
                }
                STATE.SOLD_OUT -> {
                    println("동전을 넣지 않으셨습니다. 동전이 반환되지 않습니다.")
                }
                STATE.SOLD -> {
                    println("이미 알맹이를 뽑으셨습니다.")
                }
            }
        }

        //손잡이를 돌리는 경우
        fun turnCrank() {
            when(state) {
                STATE.HAS_QUARTER -> {
                    println("손잡이를 돌리셨습니다.")
                    state = STATE.SOLD
                    dispense()
                }
                STATE.NO_QUARTER -> {
                    println("동전을 넣어주세요.")
                }
                STATE.SOLD_OUT -> {
                    println("매진되었습니다.")
                }
                STATE.SOLD -> {
                    println("손잡이는 한 번만 돌려주세요.")
                }
            }
        }

        //알맹이 꺼내기
        fun dispense() {
            when(state) {
                STATE.HAS_QUARTER -> {
                    println("알맹이가 나갈 수 없습니다.")
                }
                STATE.NO_QUARTER -> {
                    println("동전을 넣어주세요.")
                }
                STATE.SOLD_OUT -> {
                    println("매진입니다.")
                }
                STATE.SOLD -> {
                    println("알맹이가 나가고 있습니다.")
                    count--
                    if(count == 0) {
                        println("더 이상 알맹이가 없습니다.")
                        state = STATE.SOLD_OUT
                    } else {
                        state = STATE.NO_QUARTER
                    }
                }
            }
        }
    }
}

