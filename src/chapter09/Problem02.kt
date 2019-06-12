package chapter09

import chapter09.Problem02.*

/**
 *  ArrayList 와 Array 를 검색 하기 위해서는 별도의 printMenu()가 필요하다.
 *
 *  다른 종류의 컬렉션들을 하나의 함수로 확인하기 위해 Iterator 인터페이스를 만들어서 처리합니다.
 */
class Problem02 {

    interface Iterator<T> {

        fun hasNext(): Boolean

        fun next(): T
    }

    data class MenuItem(
        val name: String,
        val description: String,
        val vegetarian: Boolean,
        val price: Double
    )

    /**
     * PancakeHouseMenu와 DinerMenu는 createIterator() 라는 동일한 메소드를 제공하지만 아직 같은 인터페이스를 구현하고 있지 않습니다.
     * 이에 Waitress의 구상메뉴 클래스에 대한 의존성을 없애보겠습니다.
     */
    class PancakeHouseMenu(
        val menuItems: ArrayList<MenuItem> = arrayListOf()
    ) {
        init {

            menuItems.add(MenuItem("펜케이크 세트 1", "세트 1", true, 2.99))
            menuItems.add(MenuItem("펜케이크 세트 2", "세트 2", true, 1.99))
            menuItems.add(MenuItem("펜케이크 세트 3", "세트 3", true, 0.99))
        }

       fun createIterator() = PancakeHouseMenuIterator(menuItems)
    }


    class PancakeHouseMenuIterator(
        private val menuItems: ArrayList<MenuItem>
    ): Iterator<MenuItem> {

        private var position = 0

        override fun next(): MenuItem {

            val menuItem = menuItems[position]
            position++
            return menuItem
        }

        override fun hasNext(): Boolean {
            return position < menuItems.size
        }
    }


    class DinerMenu(
        private val menuItems: Array<MenuItem?> = Array(6) { null }
    ) {

        init {

            menuItems[0] = MenuItem("음식 1", "채식주의자용", true, 2.99)
            menuItems[1] = MenuItem("음식 2", "혼합", false, 1.99)
            menuItems[2] = MenuItem("음식 3", "육식", false, 0.99)
        }
        
        fun createIterator() = DinerMenuIterator(menuItems)
    }

    class DinerMenuIterator(
        private val menuItems: Array<MenuItem?>
    ) : Iterator<MenuItem> {

        private var position = 0

        override fun next(): MenuItem {

            val menuItem = menuItems[position]
            position++

            return menuItem!!
        }

        override fun hasNext(): Boolean {
            return !(position >= menuItems.size || menuItems[position] == null)
        }

    }

    class Waitress(var pancakeHouseMenu: PancakeHouseMenu, var dinerMenu: DinerMenu) {

        fun printMenu() {

            val pancakeHouseMenu = pancakeHouseMenu.createIterator()

            val dinerMenu = dinerMenu.createIterator()

            println("-- 아침메뉴 --")
            printMenu(pancakeHouseMenu)

            println("-- 저녁메뉴 --")
            printMenu(dinerMenu)
        }

        private fun printMenu(iterator: Iterator<MenuItem>) {
            while (iterator.hasNext()) {
                val menuItem = iterator.next()
                println(menuItem.name)
            }
        }
    }
}

fun main() {

    val pancakeHouseMenu = PancakeHouseMenu()

    val dinerMenu = DinerMenu()

    Waitress(pancakeHouseMenu, dinerMenu).printMenu()
}