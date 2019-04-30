package chapter02

import chapter02.Problem02.*

class Problem02 {

    interface Subject {

        fun registerObserver(o: Observer)

        fun removeObserver(o: Observer)

        fun notifyObservers()
    }

    interface Observer {

        fun update(temperature: Float, humidity: Float, pressure: Float)
    }

    interface DisplayElement {
        fun display()
    }

    class WeatherData(
        val observers: ArrayList<Observer> = ArrayList(),
        var temperature: Float = -1f,
        var humidity: Float = -1f,
        var pressure: Float = -1f
    ) : Subject {

        override fun registerObserver(o: Observer) {
            observers.add(o)
        }

        override fun removeObserver(o: Observer) {
            println("contains : ${observers.contains(o)}")
            //println("test contains : ${observers.contains()}")
            if(observers.contains(o)) observers.remove(o)
        }

        override fun notifyObservers() {
            for(observer in observers) {
                observer.update(temperature, humidity, pressure)
            }
        }


        fun measurementsChanged() {
            notifyObservers()
        }

        fun setMeasurements(temperature: Float, humidity: Float, pressure: Float) {
            this.temperature = temperature
            this.humidity = humidity
            this.pressure = pressure
            measurementsChanged()
        }
    }

    class CurrentConditionDisplay(weatherData: Subject) : Observer, DisplayElement {

        private var temperature: Float? = null
        private var humidity: Float? = null
        private var pressure: Float? = null

        init {
            weatherData.registerObserver(this)
        }

        override fun update(temperature: Float, humidity: Float, pressure: Float) {
            this.temperature = temperature
            this.humidity = humidity
            this.pressure = pressure

            display()
        }

        override fun display() {
            println("Current conditions : $temperature , humidity : $humidity , pressure : $pressure")
        }

    }

    class StatisticsDisplay(weatherData: Subject) : Observer, DisplayElement {

        private var temperature: Float? = null
        private var humidity: Float? = null
        private var pressure: Float? = null

        init {
            weatherData.registerObserver(this)
        }

        override fun update(temperature: Float, humidity: Float, pressure: Float) {
            this.temperature = temperature
            this.humidity = humidity
            this.pressure = pressure

            display()
        }

        override fun display() {
            println("StatisticsDisplay conditions : $temperature , humidity : $humidity , pressure : $pressure")
        }

    }
}

fun main() {
    val weatherData = WeatherData()

    CurrentConditionDisplay(weatherData)
    StatisticsDisplay(weatherData)

    weatherData.setMeasurements(80f, 65f ,30.4f)
    weatherData.setMeasurements(82f, 70f ,29.2f)
    weatherData.setMeasurements(78f, 90f ,39.2f)
}
