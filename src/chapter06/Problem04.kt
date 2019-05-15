package chapter06

import chapter06.Problem04.*

/**
 * 선풍기 명령어에 작업취고 기능 추가하기
 *
 */
class Problem04 {

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

        //TODO 동작이 안됨
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
     * 선풍기
     */
    class CeilingFan(var speed:Int = 0) {
        
        val HIGH = 3
        val MEDIUM = 2
        val LOW = 1
        val OFF = 1
        
        fun high() {
            println("high")
        }

        fun medium() {
            println("medium")
        }

        fun low() {
            println("low")
        }

        fun off() {
            println("ceilingFan off")
        }
    }
    
    class CeilingFanHighCommand(val ceilingFan: CeilingFan): Command {

        private var prevSpeed = CeilingFan().HIGH
        
        override fun execute() {
            prevSpeed = ceilingFan.speed
            ceilingFan.high()
        }

        //작업을 취소할 때는 선풍기 속도를 기존 속도로 설정합니다.
        override fun undo() {
            when(prevSpeed) {
                CeilingFan().HIGH -> {
                    ceilingFan.high()
                }
                CeilingFan().MEDIUM -> {
                    ceilingFan.medium()
                }
                CeilingFan().LOW -> {
                    ceilingFan.low()
                }
                CeilingFan().OFF -> {
                    ceilingFan.off()
                }
            }
            
        }
    }

    class CeilingFanMediumCommand(val ceilingFan: CeilingFan): Command {

        private var prevSpeed = CeilingFan().MEDIUM

        override fun execute() {
            prevSpeed = ceilingFan.speed
            ceilingFan.medium()
        }

        //작업을 취소할 때는 선풍기 속도를 기존 속도로 설정합니다.
        override fun undo() {
            when(prevSpeed) {
                CeilingFan().HIGH -> {
                    ceilingFan.high()
                }
                CeilingFan().MEDIUM -> {
                    ceilingFan.medium()
                }
                CeilingFan().LOW -> {
                    ceilingFan.low()
                }
                CeilingFan().OFF -> {
                    ceilingFan.off()
                }
            }

        }
    }

    class CeilingFanOffCommand(val ceilingFan: CeilingFan): Command {

        private var prevSpeed = CeilingFan().OFF

        override fun execute() {
            prevSpeed = ceilingFan.speed
            ceilingFan.off()
        }

        //작업을 취소할 때는 선풍기 속도를 기존 속도로 설정합니다.
        override fun undo() {
            when(prevSpeed) {
                CeilingFan().HIGH -> {
                    ceilingFan.high()
                }
                CeilingFan().MEDIUM -> {
                    ceilingFan.medium()
                }
                CeilingFan().LOW -> {
                    ceilingFan.low()
                }
                CeilingFan().OFF -> {
                    ceilingFan.off()
                }
            }

        }
    }
    
}

fun main() {

    val RemoteControlWithUndo = RemoteControlWithUndo()

    val ceillingFan = CeilingFan()

    val ceilingFanMedium = CeilingFanMediumCommand(ceillingFan)
    val ceilingFanHigh = CeilingFanHighCommand(ceillingFan)
    val ceilingFanOff = CeilingFanOffCommand(ceillingFan)

    RemoteControlWithUndo.setCommand(0, ceilingFanMedium, ceilingFanOff)
    RemoteControlWithUndo.setCommand(1, ceilingFanHigh, ceilingFanOff)

    println(RemoteControlWithUndo)

    RemoteControlWithUndo.onButtonWasPushed(0) //선풍기를 Medium으로 돌립니다.
    RemoteControlWithUndo.offButtonWasPushed(0) //선풍기를 끕니다.
    println()
    RemoteControlWithUndo.undoButtonWasPushed() //다시 Medium으로 돌아갑니다.
    println()
    RemoteControlWithUndo.onButtonWasPushed(1) //이번에는 High으로 돌립니다.
    RemoteControlWithUndo.undoButtonWasPushed() // 작업을 취소 했으므로 Medium으로 돌아갑니다.
}