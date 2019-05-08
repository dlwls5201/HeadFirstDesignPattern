package puzzle

/**
 *  액션 어드벤처 게임에서 사용할 클래스와 인터페이스가 있다.
 *  게임 캐릭터용 클래스, 무기의 행동에 관한 클래스
 *  각 캐릭터는 한 번에 한가지 무기만 사용할 수 있지만 게임 도중에 무기를 바꿀 수 있다.
 *
 *  추상 클래스 1개 : Character
 *  인터페이스 1개 : WeaponBehavior
 *  클래스 8개 : Queen, King, Troll, Knight / KnifeBehavior, BowAndArrowBehavior, AxeBehavior, SwordBehavior
 */





/**
 * 게임 캐릭터용 클래스
 */
abstract class Character {

    private var w: WeaponBehavior? = null

    abstract fun display()

    fun setWeapon(w: WeaponBehavior) {
        this.w = w
    }

    fun fight() {
        w?.useWeapon() ?: println("무기가 없습니다.")
    }
}

//기본적으로 중첩클래스이다. 내부 클래스가 되기 위해서는 inner 키워드를 사용해야 한다.
class Queen: Character() {

    override fun display() {
        println("여왕")
    }
}

class King: Character() {
    override fun display() {
        println("왕")
    }
}

class Troll: Character() {
    override fun display() {
        println("트롤")
    }
}

class Knight: Character() {
    override fun display() {
        println("나이트")
    }
}




/**
 * 무기의 행동에 관한 클래스
 */
interface WeaponBehavior {

    //코틀린 인터페이스 안에는 추상 메소드뿐 아니라 구현이 있는 메소드도 정의할 수 있다.
    //자바 8 인터페이스와 비슷하다.
    fun useWeapon()
}

class KnifeBehavior: WeaponBehavior {
    override fun useWeapon() {
        println("칼로 베기")
    }
}

class BowAndArrowBehavior: WeaponBehavior {
    override fun useWeapon() {
        println("활을 써서 화살을 발사")
    }
}

class AxeBehavior: WeaponBehavior {
    override fun useWeapon() {
        println("도끼로 찍기")
    }
}

class SwordBehavior: WeaponBehavior {
    override fun useWeapon() {
        println("검을 휘두르기")
    }
}




fun main() {

    with(Queen()) {

        display()

        //칼 장착
        setWeapon(KnifeBehavior())
        fight()

        //활 교체
        setWeapon(BowAndArrowBehavior())
        fight()
    }

}