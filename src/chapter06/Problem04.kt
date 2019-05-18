package chapter06

import chapter06.Problem04.*

/**
 *  작업취소 기능을 구현할 때 간단한 상태를 저장해야 하는 경우
 */
class Problem04 {

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
     *  작업취소 기능을 추가하려면 선풍기의 이전 속도 설정을 저장해 뒀다가 undo() 메소드가 호출되면
     *  기존 설정으로 되돌아가면 됩니다.
     */
    class CeilingFan(val location: String, var speed: Int = OFF) {

        companion object {
            const val HIGH = 3
            const val MEDIUM = 2
            const val LOW = 1
            const val OFF = 0
        }

        fun high() {
            speed = HIGH
            println("$location ceiling fan is on high")
        }

        fun medium() {
            speed = MEDIUM
            println("$location ceiling fan is on medium")
        }

        fun low() {
            speed = LOW
            println("$location ceiling fan is on low")
        }

        fun off() {
            speed = OFF
            println("$location ceiling fan is on off")
        }

    }

    class CeilingFanHighCommand(val ceilingFan: CeilingFan): Command {

        var prevSpeed = CeilingFan.OFF

        override fun execute() {
            prevSpeed = ceilingFan.speed
            ceilingFan.high()
        }

        override fun undo() {
            when(prevSpeed) {
                CeilingFan.HIGH -> {
                    ceilingFan.high()
                }
                CeilingFan.MEDIUM -> {
                    ceilingFan.medium()
                }
                CeilingFan.LOW -> {
                    ceilingFan.low()
                }
                CeilingFan.OFF -> {
                    ceilingFan.off()
                }
            }
        }

    }

    class CeilingFanMediumCommand(val ceilingFan: CeilingFan): Command {

        var prevSpeed = CeilingFan.OFF

        override fun execute() {
            prevSpeed = ceilingFan.speed
            ceilingFan.medium()
        }

        override fun undo() {
            when(prevSpeed) {
                CeilingFan.HIGH -> {
                    ceilingFan.high()
                }
                CeilingFan.MEDIUM -> {
                    ceilingFan.medium()
                }
                CeilingFan.LOW -> {
                    ceilingFan.low()
                }
                CeilingFan.OFF -> {
                    ceilingFan.off()
                }
            }
        }

    }

    class CeilingFanOffCommand(val ceilingFan: CeilingFan): Command {

        var prevSpeed = CeilingFan.OFF

        override fun execute() {
            prevSpeed = ceilingFan.speed
            ceilingFan.off()
        }

        override fun undo() {
            when(prevSpeed) {
                CeilingFan.HIGH -> {
                    ceilingFan.high()
                }
                CeilingFan.MEDIUM -> {
                    ceilingFan.medium()
                }
                CeilingFan.LOW -> {
                    ceilingFan.low()
                }
                CeilingFan.OFF -> {
                    ceilingFan.off()
                }
            }
        }

    }


}

fun main() {

    val remoteControl = RemoteControlWithUndo()

    val ceilingFan = CeilingFan("Living Room")

    val ceilingFanMedium = CeilingFanMediumCommand(ceilingFan)
    val ceilingFanHigh = CeilingFanHighCommand(ceilingFan)
    val ceilingFanOff = CeilingFanOffCommand(ceilingFan)

    remoteControl.setCommand(0, ceilingFanMedium, ceilingFanOff)
    remoteControl.setCommand(1, ceilingFanHigh, ceilingFanOff)

    println(remoteControl)

    remoteControl.onButtonWasPushed(0)
    remoteControl.offButtonWasPushed(0)
    println()
    remoteControl.undoButtonWasPushed()
    println()
    remoteControl.onButtonWasPushed(1)
    remoteControl.undoButtonWasPushed()

}