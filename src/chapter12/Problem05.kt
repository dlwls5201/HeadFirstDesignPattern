package chapter12

/**
 * MVC
 */
interface BeadModelInterface {

    fun initialize()
    fun on()
    fun off()
    fun setBPM(bpm: Int)

    fun getBPM(): Int
    //fun registerObserver(o: BeadObserver)
    //fun remoiveObserver(o: BeadObserver)
    //fun registerObserver(o: BPMObserver)
    //fun removeObserver(o: BPMObserver)
}

interface ControllerInterface {

    fun start()
    fun stop()
    fun increaseBPM()
    fun decreaseBPM()
    fun setBPM(bpm: Int)
}

