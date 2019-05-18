package puzzle

import puzzle.Puzzle06_1.*

class Puzzle06_1 {


    /**
     * 1. 클라이언트는 커맨드 객체를 생성해야 합니다. 커맨드 객체를 리시버에 전달할 일련의 행동으로 구성되죠
     * 커맨드 객체에는 행동과 리시버에 대한 정보가 같이 들어 있습니다.
     */
    class Client {

        fun createOrder() = Order()
    }


    interface Command {
        /**
         *  2. 커맨드 객체에서 제공하는 메소드는 execute() 하나뿐입니다. 이 메소드에는 행동을 캡슐화하며,
         *  리시버에 있는 특정 행동을 처리하기 위한 메소드를 호출하기 위핸 메소드 입니다.
         */
        fun execute()
    }

    class Order {

        var waitress: Command? = null

        /**
         * 3. 클라이언트에서는 인보커 객체의 setCommand() 메소드를 호출하는데, 이때 커맨드 객체를 넘겨줍니다.
         * 그 커맨드 객체는 나중에 쓰이기 전까지 인보커 객체에 보관됩니다.
         */
        fun takeOrder(command: Command) {
            this.waitress = command
        }

        fun orderUp() {
            waitress?.execute()
        }
    }

    class Chef {

        fun makeBurger() {
            println("햄버거를 만듬")
        }

        fun makeShaker() {
            println("쉐이커를 만듬")
        }
    }

    class ChefCommand(val chef: Chef): Command {
        override fun execute() {
            chef.makeBurger()
            chef.makeShaker()
        }
    }

}

/**
 * 1. 클라이언트에서 커맨드 객체 생성
 * 2. setCommand()를 호출하여 인보커에 커맨드 객체를 저장
 * 3. 나중에 클라이언트에서 인보커한테 그 명령을 실행 시켜 달라는 요청을 함
 * 4. 인보커는 명령을 실행합니다.
 */
fun main() {

    val client = Client()

    //1. 고객은 createOrder 통해 주문서를 생성합니다.
    val order = client.createOrder()

    //4. 주문서는 주방장에게 makeBurger(), makeShake() 호출을 지시합니다.
    val chef = Chef()
    val cookCommand = ChefCommand(chef)

    //2. 주문서는  takeOrder 통해 웨이트리스에게 주문을 전달합니다.
    order.takeOrder(cookCommand)

    //3. 웨이트리스는 orderUp 통해 주문서가 주방장에게 지시를 내리도록 합니다.
    order.orderUp()

}