package id.rezkyauliapratama.fhome.ui.bottomsheet

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RadioGroup
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.innovation.libcore.data.locale.LocaleManager
import id.innovation.libcore.di.CoreInjectHelper
import id.innovation.libcore.di.PresenterModule
import id.innovation.libuicomponent.R
import id.rezkyauliapratama.fhome.di.DaggerFeatureComponent
import kotlinx.android.synthetic.main.dialog_change_language.*
import id.rezkyauliapratama.fhome.R as R2


class SettingBottomSheetDialog : BottomSheetDialogFragment(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    var chooseLanguage: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CommonBottomSheetDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R2.layout.dialog_change_language, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        injectDI()

        view.viewTreeObserver.addOnGlobalLayoutListener {
            val dialog = dialog as BottomSheetDialog?
            val bottomSheet = dialog?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            val behavior = BottomSheetBehavior.from(bottomSheet)

            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.peekHeight = 0

            behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                        dismiss()
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                }
            })

            initView()
        }
    }

    private fun initView() {
        rgLanguage.setOnCheckedChangeListener(this)

        LocaleManager.getLanguagePref(requireContext()).apply {
            when (this) {
                LocaleManager.INDONESIA -> rbIndonesia.isChecked = true
                LocaleManager.ENGLISH -> rbEnglish.isChecked = true
            }
        }
    }

    private fun injectDI() {
        DaggerFeatureComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(requireActivity().applicationContext))
            .presenterModule(PresenterModule(requireActivity()))
            .build()
            .inject(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {

        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            rbEnglish.id -> {
                chooseLanguage = LocaleManager.ENGLISH
            }
            rbIndonesia.id -> {
                chooseLanguage = LocaleManager.INDONESIA
            }
        }
    }

    private fun setNewLocale(@LocaleManager.LocaleDef language: String) {
        LocaleManager.setNewLocale(requireContext(), language)
        val intent = requireActivity().intent
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    private fun checkNewLanguage() {
        if (chooseLanguage != LocaleManager.getLanguagePref(requireContext())) {
            setNewLocale(chooseLanguage)
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        checkNewLanguage()
        super.onDismiss(dialog)
    }

}
