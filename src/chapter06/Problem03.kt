package chapter06

import chapter06.Problem03.*

/**
 * 마지막으로 했던 작업이 취소되는 undo 기능을 추가하겠습니다.
 */
class Problem03 {

    //Command 인터페이스 만들기
    interface Command {
        fun execute()
        fun undo()

    }

    class RemoteControlWithUndo {

        val onCommands = Array<Command?>(7) { null }
        val offCommands = Array<Command?>(7) { null }

        var undoCommand:Command? = null

        fun setCommand(slot: Int, onCommand: Command, offCommand: Command) {
            onCommands[slot] = onCommand
            offCommands[slot] = offCommand
        }

        fun onButtonWasPushed(slot: Int) {
            onCommands[slot]?.execute()
            undoCommand = onCommands[slot]
        }

        fun offButtonWasPushed(slot: Int) {
            offCommands[slot]?.execute()
            undoCommand = offCommands[slot]
        }

        fun undoButtonWasPushed() {
            undoCommand?.undo()
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

        override fun undo() {
            light.off()
        }
    }

    //전등을 끄기 위한 커맨드 클래스 구현
    class LightOffCommand(val light: Light): Command {
        override fun execute() {
            light.off()
        }

        override fun undo() {
            light.on()
        }
    }


    /**
     * 쓰레기통
     */
    class GarageDoor {

        fun up() {
            println("Garage Door is Open")
        }

        fun down() {
            println("Garage Door is down")
        }

        fun stop() {}

        fun lightOn() {}

        fun lightOff() {}
    }

    class GargeDoorOpenCommand(val garageDoor: GarageDoor): Command {
        override fun execute() {
            garageDoor.up()
        }

        override fun undo() {
            garageDoor.down()
        }
    }

    class GargeDoorCloseCommand(val garageDoor: GarageDoor): Command {
        override fun execute() {
            garageDoor.down()
        }

        override fun undo() {
            garageDoor.up()
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

        override fun undo() {
            stereo.off()
        }
    }

    class StereoOffCommand(val stereo: Stereo): Command {

        override fun execute() {
            stereo.off()
        }

        override fun undo() {
            stereo.on()
            stereo.setCD()
            stereo.setVolume(11)
        }

    }
}

fun main() {

    val remoteControlWithUndo = RemoteControlWithUndo()

    val light = Light()
    val garageDoor = GarageDoor()
    val stereo = Stereo()

    val lightOn = LightOnCommand(light)
    val lightOff = LightOffCommand(light)

    val garageDoorOn = GargeDoorOpenCommand(garageDoor)
    val garageDoorOff = GargeDoorCloseCommand(garageDoor)

    val stereoOnWithCD = StereoOnWithCDCommand(stereo)
    val stereoOff = StereoOffCommand(stereo)

    remoteControlWithUndo.setCommand(0, lightOn, lightOff)
    remoteControlWithUndo.setCommand(1, garageDoorOn, garageDoorOff)
    remoteControlWithUndo.setCommand(2, stereoOnWithCD, stereoOff)

    println(remoteControlWithUndo)

    remoteControlWithUndo.onButtonWasPushed(0)
    remoteControlWithUndo.undoButtonWasPushed()
    println()
    remoteControlWithUndo.onButtonWasPushed(1)
    remoteControlWithUndo.offButtonWasPushed(1)
    println()
    remoteControlWithUndo.onButtonWasPushed(2)
    remoteControlWithUndo.offButtonWasPushed(2)
    println()
    remoteControlWithUndo.undoButtonWasPushed()
}