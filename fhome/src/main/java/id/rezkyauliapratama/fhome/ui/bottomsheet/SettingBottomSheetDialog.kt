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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.innovation.libcore.data.locale.LocaleManager
import id.innovation.libcore.di.helper.CoreInjectHelper.provideCoreComponent
import id.innovation.libcore.di.module.PresenterModule
import id.innovation.libuicomponent.R
import id.rezkyauliapratama.fhome.di.DaggerFeatureComponent
import kotlinx.android.synthetic.main.dialog_setting.*
import id.rezkyauliapratama.fhome.R as R2
import id.rezkyauliapratama.fhome.ui.HomeViewModel
import javax.inject.Inject
import id.innovation.libcore.ui.common.SafeObserver
import id.rezkyauliapratama.dicodingmade.service.AlarmService
import timber.log.Timber

class SettingBottomSheetDialog : BottomSheetDialogFragment(), View.OnClickListener,
    RadioGroup.OnCheckedChangeListener {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    var chooseLanguage: String = ""

    val shareViewModel by lazy {
        ViewModelProviders.of(requireActivity(), mViewModelFactory)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CommonBottomSheetDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R2.layout.dialog_setting, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        injectDI()

        view.viewTreeObserver.addOnGlobalLayoutListener {
            val dialog = dialog as BottomSheetDialog?
            val bottomSheet =
                dialog?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
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
        rgDailyReminder.setOnCheckedChangeListener(this)
        rgReleaseReminder.setOnCheckedChangeListener(this)

        LocaleManager.getLanguagePref(requireContext()).apply {
            when (this) {
                LocaleManager.INDONESIA -> rbIndonesia.isChecked = true
                LocaleManager.ENGLISH -> rbEnglish.isChecked = true
            }
        }

        shareViewModel.dailyReminderLiveData.observe(requireActivity(), SafeObserver(::handleDailyReminder))
        shareViewModel.releaseReminderLiveData.observe(requireActivity(), SafeObserver(::handleReleaseReminder))
    }

    private fun handleReleaseReminder(isActive: Boolean) {
        if (isActive){
            rbOnReleaseReminder.isChecked = true
            val serviceIntent = Intent(context, AlarmService::class.java)
            serviceIntent.putExtra(AlarmService.RELEASE_REMINDER_ACTIVE, isActive)
            requireContext().startService(serviceIntent)
        } else {
            rbOffReleaseReminder.isChecked = true
            AlarmService.cancelReleaseReminder(requireContext())
        }

    }

    private fun handleDailyReminder(isActive: Boolean) {
        Timber.e("handleDailyReminder : $isActive")
        if (isActive){
            rbOnDailyReminder.isChecked = true
            val serviceIntent = Intent(context, AlarmService::class.java)
            serviceIntent.putExtra(AlarmService.DAILY_REMINDER_ACTIVE, isActive)
            requireContext().startService(serviceIntent)
        } else {
            rbOffDailyReminder.isChecked = true
            AlarmService.cancelDailyReminder(requireContext())
        }
    }

    private fun injectDI() {
        DaggerFeatureComponent
            .builder()
            .coreComponent(provideCoreComponent(requireActivity().applicationContext))
            .presenterModule(PresenterModule(requireActivity()))
            .build()
            .inject(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {

        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        Timber.e("checkId : ${checkedId}")
        when  {
            checkedId == rbEnglish.id && group == rgLanguage-> {
                chooseLanguage = LocaleManager.ENGLISH
            }
            checkedId == rbIndonesia.id && group == rgLanguage -> {
                chooseLanguage = LocaleManager.INDONESIA
            }
            checkedId == rbOffDailyReminder.id && group == rgDailyReminder-> {
                shareViewModel.setStatusDailyReminder(false)
            }
            checkedId == rbOnDailyReminder.id && group == rgDailyReminder -> {
                shareViewModel.setStatusDailyReminder(true)
            }
            checkedId == rbOffReleaseReminder.id && group == rgReleaseReminder -> {
                shareViewModel.setStatusReleaseReminder(false)
            }
            checkedId == rbOnReleaseReminder.id && group == rgReleaseReminder  -> {
                shareViewModel.setStatusReleaseReminder(true)
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
