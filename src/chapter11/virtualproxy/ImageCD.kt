package chapter11.virtualproxy

import java.net.URL

interface Icon {

    fun getIconWidth(): Int

    fun getIconHeight(): Int

    fun paintIcon()
}

/*class ImageProxy(
    private val url: URL) : Icon {

    //이미지
    var imageIcon: Icon? = null
    var retrieving = false

    override fun getIconWidth(): Int {
        return imageIcon?.getIconWidth() ?: 800
    }

    override fun getIconHeight(): Int {
        return imageIcon?.getIconHeight() ?: 600
    }

    override fun paintIcon() {
        if(imageIcon != null) {

        } else {
            // 로딩 화면 그리기
            if(!retrieving) {
                retrieving = true
                //ImageIcon 생성
            }
        }
    }
}*/

class ImageProxy(
    private val url: URL) : Icon {

    //이미지
    var imageIcon: Icon? = null
    var retrieving = false

    //상태
    val cachedState: CachedState
    val notcachedState: NotCachedState

    //상태 변수
    var state: State

    //초기화
    init {

        cachedState = CachedState(this)
        notcachedState = NotCachedState(this)

        state = notcachedState
    }

    override fun getIconWidth(): Int {
        return state.getImageWidth()
    }

    override fun getIconHeight(): Int {
        return state.getImageHeight()
    }

    override fun paintIcon() {
        if(imageIcon != null) {

        } else {
            // 로딩 화면 그리기
            if(!retrieving) {
                retrieving = true
                //ImageIcon 생성

                //상태 변환
                state = cachedState
            }
        }
    }
}

/**\
 * 상태
 *
 * 1.캐싱 된 상태(ImageLoaded)
 * 2.캐싱 되지 않은 상태(ImageNotLoaded)
 */
interface State {

    /**
     * 행동
     */
    fun getImageWidth(): Int

    fun getImageHeight(): Int
}

class CachedState(
    private val imageProxy: ImageProxy
): State {

    override fun getImageWidth() = imageProxy.imageIcon!!.getIconWidth()

    override fun getImageHeight()= imageProxy.imageIcon!!.getIconHeight()
}

class NotCachedState(
    private val imageProxy: ImageProxy
): State {

    override fun getImageWidth() = 800

    override fun getImageHeight() = 600

}



