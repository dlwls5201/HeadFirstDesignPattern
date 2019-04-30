package chapter02

/**
 *  문제
 *  구체적인 구현에 맞춰서 코딩했기 때문에 프로그램을 고치지 않고는 다른 디스플레이 항목을 추가/제거 할 수 없다.
 */
class Problem01 {

    class WeatherData(
        var temperature: Float,
        var humidity: Float,
        var pressure: Float
    ) {

        fun measurementsChanged() {

            //TODO currentConditionsDisplay.update(temperature, humidity, pressure)
            //TODO statisticsDisplay.update(temperature, humidity, pressure)
            //TODO forecastDisplay.update(temperature, humidity, pressure)
        }
    }
}

fun main() {

}

