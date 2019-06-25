package chapter10

import kotlin.random.Random

/**
 *  스테이트 패턴을 구현해놓았습니다.
 *  이제 열 번에 한 번 꼴로 알맹이를 하나 더 주는 게임을 마무리해보겠습니다.
 */
class Problem03 {

    interface State {

        //동전이 투입된 경우
        fun insertQuarter()

        //사용자가 동전을 반환 받으려고 하는 경우
        fun ejectQuarter()

        //손잡이를 돌리는 경우
        fun turnCrank()

        //알맹이 꺼내기
        fun dispense()
    }

    class GumballMachine(
        var count: Int = 0
    ) : State {
        
        val soldOutState: State
        val noQuarterState: State
        val hasQuarterState: State
        val soldState: State

        //TODO 추가1
        var winnerState: State

        var state: State

        init {
            soldOutState = SoldOutState(this)
            noQuarterState = NoQuarterState(this)
            hasQuarterState = HasQuarterState(this)
            soldState = SoldState(this)

            //TODO 추가2
            winnerState = WinnerState(this)

            state = soldOutState

            if (count > 0) state = noQuarterState

        }

        override fun insertQuarter() {
            state.insertQuarter()
        }

        override fun ejectQuarter() {
            state.ejectQuarter()
        }

        override fun turnCrank() {
            state.turnCrank()
            state.dispense()
        }

        override fun dispense() {
            state.dispense()
        }

        fun releaseBall() {
            println("A gumball comes rolling out the slot...")
            if (count != 0) count--
        }

        //TODO 추가5
        fun refill(count: Int) {
            println("${count}개 리필")
            this.count += count
            this.state = noQuarterState
        }

        override fun toString(): String {
            return "\n추식회사 왕뽑기\n남은 개수 : ${count}개\n${if(count > 0) "동전 투입 대기중" else "매진"}\n"
        }
    }

    class NoQuarterState(private val gumballMachine: GumballMachine) : State {

        override fun insertQuarter() {
            println("동전을 넣으셨습니다.")
            gumballMachine.state = gumballMachine.hasQuarterState
        }

        override fun ejectQuarter() {
            println("동전을 넣어주세요.")
        }

        override fun turnCrank() {
            println("동전을 넣어주세요.")
        }

        override fun dispense() {
            println("동전을 넣어주세요.")
        }
    }

    class HasQuarterState(private val gumballMachine: GumballMachine) : State {

        override fun insertQuarter() {
            println("동전은 한 개만 넣어주세요.")
        }

        override fun ejectQuarter() {
            println("동전이 반환됩니다.")
            gumballMachine.state = gumballMachine.noQuarterState
        }

        override fun turnCrank() {
            println("손잡이를 돌리셨습니다.")
            //TODO 추가4
            val winner = Random.nextInt(10)

            if (winner == 0 && gumballMachine.count > 1) {
                gumballMachine.state = gumballMachine.winnerState
            } else {
                gumballMachine.state = gumballMachine.soldState
            }
        }

        override fun dispense() {
            println("알맹이가 나갈 수 없습니다.")
        }
    }

    class SoldState(private val gumballMachine: GumballMachine) : State {

        override fun insertQuarter() {
            println("잠깐만 기다려 주세요. 알맹이가 나가고 있습니다.")
        }

        override fun ejectQuarter() {
            println("이미 알맹이를 뽑으셨숩니다.")
        }

        override fun turnCrank() {
            println("손잡이는 한번만 돌려주세요.")
        }

        override fun dispense() {
            gumballMachine.releaseBall()
            if (gumballMachine.count > 0) {
                gumballMachine.state = gumballMachine.noQuarterState
            } else {
                println("Oops, out of gumballs!")
                gumballMachine.state = gumballMachine.soldOutState
            }
        }
    }

    class SoldOutState(private val gumballMachine: GumballMachine) : State {

        override fun insertQuarter() {
            println("죄송합니다. 매진되었습니다.")
        }

        override fun ejectQuarter() {
            println("죄송합니다. 매진되었습니다.")
        }

        override fun turnCrank() {
            println("죄송합니다. 매진되었습니다.")
        }

        override fun dispense() {
            println("알맹이가 나갈 수 없습니다.")
        }
    }

    //TODO 추가3
    class WinnerState(private val gumballMachine: GumballMachine) : State {

        override fun insertQuarter() {
            println("동전을 넣어 주세요.")
        }

        override fun ejectQuarter() {
            println("동전을 넣어 주세요.")
        }

        override fun turnCrank() {
            println("동전을 넣어 주세요.")
        }

        override fun dispense() {
            println("축하드립니다! 알맹이를 하나 더 받으실 수 있습니다.")
            gumballMachine.releaseBall()
            if (gumballMachine.count == 0) {
                gumballMachine.state = gumballMachine.soldOutState
            } else {
                gumballMachine.releaseBall()
                if (gumballMachine.count > 0) {
                    gumballMachine.state = gumballMachine.noQuarterState
                } else {
                    println("더 이상 알맹이가 없습니다.")
                    gumballMachine.state = gumballMachine.soldOutState
                }
            }
        }
    }
}

fun main() {
    val gumballMachine = Problem03.GumballMachine(count = 1)

    println(gumballMachine)

    gumballMachine.insertQuarter()
    gumballMachine.turnCrank()

    println(gumballMachine)

    gumballMachine.insertQuarter()
    gumballMachine.turnCrank()

    //TODO 추가6
    gumballMachine.refill(10)

    println()

    gumballMachine.insertQuarter()
    gumballMachine.turnCrank()

    println(gumballMachine)
}
