package chapter06

import chapter06.Problem01.*

/**
 * 커맨드객체를 사용해 보겠습니다.
 */
class Problem01 {

    //Command 인터페이스 만들기
    interface Command {
        fun execute()
    }

    //커맨드 객체 사용하기
    class SimpleRemoteControl {

        var slot: Command? = null

        fun setCommand(command: Command) {
            slot = command
        }

        fun buttonWasPressed() {
            slot?.execute()
        }
    }

    /**
     * 전등
     */
    class Light {

        fun on() {
            println("Light is on")
        }

        fun off() {
            println("Light is off")
        }
    }

    //전등을 켜기 위한 커맨드 클래스 구현
    class LightOnCommand(val light: Light): Command {
        override fun execute() {
            light.on()
        }
    }

    //전등을 끄기 위한 커맨드 클래스 구현
    class LightOffCommand(val light: Light): Command {
        override fun execute() {
            light.off()
        }
    }


    /**
     * 쓰레기통
     */
    class GarageDoor {

        fun up() {
            println("Garage Door is Open")
        }

        fun down() {}

        fun stop() {}

        fun lightOn() {}

        fun lightOff() {}
    }

    class GarageDoorOpenCommand(val garageDoor: GarageDoor): Command {
        override fun execute() {
            with(garageDoor) {
                up()
            }
        }

    }

}

fun main() {

    val remote = SimpleRemoteControl()

    val light = Light()
    val garageDoor = GarageDoor()

    val lightOn = LightOnCommand(light)
    val garageOpen = GarageDoorOpenCommand(garageDoor)

    with(remote) {
        setCommand(lightOn)
        buttonWasPressed()

        setCommand(garageOpen)
        buttonWasPressed()
    }

}