package id.innovation.libcore.ui
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import id.co.rezkyauliapratama.libcore.R

const val Y_OFFSET = 150

object ToastUtils {
    fun showToast(context: Context?, message: String?, icon: Int = 0) {
        if (context != null && message != null) {
            val toast = Toast(context)
            val layoutInflater =
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val layout = layoutInflater.inflate(R.layout.layout_custom_toast, null)
            val tvMessage = layout.findViewById<TextView>(R.id.tvMessage)
            tvMessage.text = message
            if (icon != 0) {
                val ivError = layout.findViewById<AppCompatImageView>(R.id.ivError)
                ivError.setImageResource(icon)
            }
            toast.view = layout
            toast.setGravity(Gravity.BOTTOM, 0, Y_OFFSET)
            toast.show()
        }
    }

    fun showToast(context: Context?, message: Int, icon: Int = 0) {
        showToast(context, context?.getString(message), icon)
    }
}
