package chapter11

/*interface State {

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
    val location: String,
    var count: Int = 0
) {

    val soldOutState: State
    val noQuarterState: State
    val hasQuarterState: State
    var soldState: State

    var state: State

    init {
        soldOutState = SoldOutState(this)
        noQuarterState = NoQuarterState(this)
        hasQuarterState = HasQuarterState(this)
        soldState = SoldState(this)

        state = soldOutState
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
        println("매진")
    }

    override fun ejectQuarter() {
        println("매진")
    }

    override fun turnCrank() {
        println("매진")
    }

    override fun dispense() {
        println("알맹이가 나갈 수 없습니다.")
    }
}

class GumballMonitor(val machine: GumballMachine) {

    fun report() {
        println("뽑기 기계 위치: ${machine.location}")
        println("현재 재고: ${machine.count} 개")
        println("현재 상태: ${machine.state}")
    }
}*/

