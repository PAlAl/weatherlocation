package ru.test.weather.ui.views

import androidx.annotation.LayoutRes
import moxy.MvpAppCompatFragment
import ru.test.weather.ui.views.global.ProgressDialog

abstract class BaseFragment(@LayoutRes contentLayoutId:Int): MvpAppCompatFragment(contentLayoutId) {

    private val PROGRESS_DIALOG_TAG: String = "progress_dialog"

    protected fun showProgressDialog(progress: Boolean) {
        if (!isAdded) return

        val fragment = childFragmentManager.findFragmentByTag(PROGRESS_DIALOG_TAG)
        if (fragment != null && !progress) {
            (fragment as ProgressDialog).dismissAllowingStateLoss()
            childFragmentManager.executePendingTransactions()
        } else if (fragment == null && progress) {
            ProgressDialog()
                    .show(childFragmentManager, PROGRESS_DIALOG_TAG)
            childFragmentManager.executePendingTransactions()
        }
    }
}