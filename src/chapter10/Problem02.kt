package chapter10

/**
 * 새로운 요구사항이 들어왔습니다.
 * 10번에 1번 꼴로 알맹이가 2개씩 나오도록 구현해주세요.
 *
 * 구현방법
 * 이번에는 각 상태의 모든 행동을 한 클래스에 집어넣을 것입니다. 그렇게 하면 행동을 국지화시킬 수 있기 때문에 코드를 수정하거나 이해하기가 훨씬 쉬워질 거에요.
 *
 * 1. 우선 뽑기 기계와 관련된 모든 행동에 대한 메소드가 들어있는 State 인터페이스를 정의해야 합니다.
 * 2. 그 다음에는 기계의 모든 상태에 대해서 상태 클래스를 구현해야 합니다. 기계가 어떤 상태에 있다면, 그 상태에 해당하는 상태 클래스가 모든 작업을 책임져야 되죠.
 * 3. 마지막으로 조건문 코드를 전부 없애고 상태 클래스에 모든 작업을 위임합니다.
 */
class Problem02 {

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
        var soldOutState: State,
        var noQuarterState: State,
        var hasQuarterState: State,
        var soldState: State,
        var state: State = soldOutState,
        var count: Int = 0
    ) {
        init {
            soldOutState = SoldOutState(this)
            noQuarterState = NoQuarterState(this)
            hasQuarterState = HasQuarterState(this)
            soldState = SoldState(this)
            if(count > 0) state = noQuarterState
        }

        fun releaseBall() {
            println("A gumball comes rolling out the slot...")
            if(count != 0) count--
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
            gumballMachine.state = gumballMachine.soldState
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
            if(gumballMachine.count > 0) {
                gumballMachine.state = gumballMachine.noQuarterState
            } else {
                println("Oops, out of gumballs!")
                gumballMachine.state = gumballMachine.soldOutState
            }
        }
    }

    class SoldOutState(private val gumballMachine: GumballMachine) : State {

        override fun insertQuarter() {

        }

        override fun ejectQuarter() {

        }

        override fun turnCrank() {

        }

        override fun dispense() {

        }
    }
}
