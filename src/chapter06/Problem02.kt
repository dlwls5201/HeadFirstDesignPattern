package chapter06

import chapter06.Problem02.*

/**
 * RemoteControl 를 만들어 줍니다.
 */
class Problem02 {

    //Command 인터페이스 만들기
    interface Command {
        fun execute()
    }

    class RemoteControl {

        val onCommands = Array<Command?>(7) { null }
        val offCommands = Array<Command?>(7) { null }

        fun setCommand(slot: Int, onCommand: Command, offCommand: Command) {
            onCommands[slot] = onCommand
            offCommands[slot] = offCommand
        }

        fun onButtonWasPushed(slot: Int) {
            onCommands[slot]?.execute()
        }

        fun offButtonWasPushed(slot: Int) {
            offCommands[slot]?.execute()
        }

        override fun toString(): String {
            val stringBuff = StringBuffer()
            stringBuff.append("\n---- Remote Control ----\n")
            for(i in 0 until onCommands.size) {
                stringBuff.append("[slot $i] ${onCommands[i]?.javaClass?.name}       ${offCommands[i]?.javaClass?.name} \n")
            }
            return stringBuff.toString()
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
            println("GarageDoor is Open")
        }

        fun down() {
            println("GarageDoor is down")
        }

        fun stop() {

        }

        fun lightOn() {

        }
        fun lightOff() {

        }
    }

    class GrageDoorOpenCommand(val garageDoor: GarageDoor): Command {
        override fun execute() {
            with(garageDoor) {
                up()
            }
        }

    }

    class GrageDoorCloseCommand(val garageDoor: GarageDoor): Command {
        override fun execute() {
            with(garageDoor) {
                down()
            }
        }

    }

    /**
     * 오디오
     */
    class Stereo {

        fun on() {
            println("Stereo on")
        }

        fun off() {
            println("Stereo off")
        }

        fun setCD() {
            println("Stereo setCD")
        }

        fun setVolume(volume: Int) {
            println("Stereo setVolume $volume")
        }
    }

    class StereoOnWithCDCommand(val stereo: Stereo): Command {

        override fun execute() {
            stereo.on()
            stereo.setCD()
            stereo.setVolume(11)
        }
    }

    class StereoOffCommand(val stereo: Stereo): Command {

        override fun execute() {
            stereo.off()
        }

    }
}

fun main() {

    val remoteControl = RemoteControl()

    val light = Light()
    val garageDoor = GarageDoor()
    val stereo = Stereo()

    val lightOn = LightOnCommand(light)
    val lightOff = LightOffCommand(light)

    val garageDoorOn = GrageDoorOpenCommand(garageDoor)
    val garageDoorOff = GrageDoorCloseCommand(garageDoor)

    val stereoOnWithCD = StereoOnWithCDCommand(stereo)
    val steretOff = StereoOffCommand(stereo)

    remoteControl.setCommand(0, lightOn, lightOff)
    remoteControl.setCommand(1, garageDoorOn, garageDoorOff)
    remoteControl.setCommand(2, stereoOnWithCD, steretOff)

    println(remoteControl)

    remoteControl.onButtonWasPushed(0)
    remoteControl.offButtonWasPushed(0)
    println()
    remoteControl.onButtonWasPushed(1)
    remoteControl.offButtonWasPushed(1)
    println()
    remoteControl.onButtonWasPushed(2)
    remoteControl.offButtonWasPushed(2)
}