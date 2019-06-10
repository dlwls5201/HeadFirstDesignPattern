package chapter09

import chapter09.Problem05.*
import java.util.*
import kotlin.collections.ArrayList

/**
 *  카페메뉴 고치기
 */
class Problem05 {

    data class MenuItem(
        val name: String = "",
        val description: String = "",
        val vegetarian: Boolean = false,
        val price: Double = 0.0
    )

    interface Menu {

        fun createIterator(): Iterator<MenuItem>
    }

    class PancakeHouseMenu : Menu {

        private val menuItems: ArrayList<MenuItem> = arrayListOf()

        init {

            menuItems.add(MenuItem("펜케이크 세트 1", "세트 1", true, 2.99))
            menuItems.add(MenuItem("펜케이크 세트 2", "세트 2", true, 1.99))
            menuItems.add(MenuItem("펜케이크 세트 3", "세트 3", true, 0.99))
        }

        override fun createIterator() = menuItems.iterator()
    }

    class DinerMenu : Menu {

        private val menuItems: Array<MenuItem> = Array(6) { MenuItem() }

        init {

            menuItems[0] = MenuItem("음식 1", "채식주의자용", true, 2.99)
            menuItems[1] = MenuItem("음식 2", "혼합", false, 1.99)
            menuItems[2] = MenuItem("음식 3", "육식", false, 0.99)
        }
        
        override fun createIterator() = menuItems.iterator()
    }

    class CafeMenu : Menu {

        private val menuItems = Hashtable<String, MenuItem>()

        init {

            addItem("카페 1", "초코케이크", true, 2.99)
            addItem("카페 2", "딸기케이크", false, 1.99)
            addItem("카페 3", "바나나케이크", false, 0.99)
        }

        fun addItem(name: String, description: String, vegetarian: Boolean, price: Double) {
            val menuItem = MenuItem(name, description, vegetarian, price)
            menuItems[name] = menuItem
        }

        override fun createIterator(): Iterator<MenuItem> {
            return menuItems.values.iterator()
        }
    }


    class Waitress(var pancakeHouseMenu: Menu, var dinerMenu: Menu, var cafeMenu: Menu) {

        /**
         *  printMenu()를 세 번이나 호출해야 됩니다.
         *  새로운 메뉴가 추가될 때마다 Waitress에 코드를 추가해야 한다는 것도 큰 문제입니다. OCP에 위배!
         *
         *  메뉴 구현을 분리시키고 반복작업에 필요한 부분은 반복자로 뽀ㅃ아낸 것만 해도 매우 훌륭하죠.
         *  하지만 여전히 여러 메뉴를 서로 다른 독립적인 객체로 다루고 있다는 문제가 있습니다.
         *  여러 메뉴를 한꺼번에 관리할 수 있는 방법이 필요합니다.
         */
        fun printMenu() {

            val pancakeHouseMenu = pancakeHouseMenu.createIterator()

            val dinerMenu = dinerMenu.createIterator()

            val cafeMenu = cafeMenu.createIterator()

            println("-- 아침메뉴 --")
            printMenu(pancakeHouseMenu)

            println("-- 저녁메뉴 --")
            printMenu(dinerMenu)

            println("-- 카페메뉴 --")
            printMenu(cafeMenu)
        }

        private fun printMenu(iterator: Iterator<MenuItem>) {
            while (iterator.hasNext()) {
                val menuItem = iterator.next()

                if(menuItem.name.isNotEmpty())
                println(menuItem)
            }
        }

        fun printBreakfastMenu() {

        }

        fun printLunchMenu() {

        }

        fun printVegetarianMenu() {

        }

        fun isItemVegetaian(name: String): Boolean {
            return false
        }

    }
}

fun main() {

    val pancakeHouseMenu = PancakeHouseMenu()

    val dinerMenu = DinerMenu()

    val cafeMenu = CafeMenu()

    Waitress(pancakeHouseMenu, dinerMenu, cafeMenu).printMenu()
}