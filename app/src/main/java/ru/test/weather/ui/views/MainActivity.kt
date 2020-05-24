package ru.test.weather.ui.views

import android.content.pm.PackageManager.PERMISSION_DENIED
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.test.weather.R
import ru.test.weather.ui.WeatherApplication
import ru.test.weather.ui.presenters.main.IMainView
import ru.test.weather.ui.presenters.main.MainPresenter
import ru.test.weather.ui.views.global.DefaultDialog
import ru.test.weather.ui.views.weather.WeatherFragment
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), IMainView {

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as WeatherApplication).getComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun openWeatherScreen() {
        supportFragmentManager.beginTransaction()
                .add(R.id.container, WeatherFragment())
                .commit()
    }

    override fun checkPermission(permission: String, callback: (Boolean) -> Unit) {
        callback(ContextCompat.checkSelfPermission(this, permission) == PermissionChecker.PERMISSION_GRANTED)
    }

    override fun requestPermission(permission: String, requestCode: Int, @StringRes dialogTitle: Int, @StringRes dialogMessage: Int,
                                   @StringRes dialogCancelTitle: Int, @StringRes dialogOkTitle: Int) {
        if (shouldShowRequestPermissionRationale(this, permission))
            showPermissionExplanation(permission, requestCode, getString(dialogTitle), getString(dialogMessage),
                    getString(dialogCancelTitle), getString(dialogOkTitle))
        else
            startPermissionRequest(permission, requestCode)
    }

    private fun showPermissionExplanation(permission: String, requestCode: Int, title: String, message: String, cancelTitle: String, okTitle: String) {
        DefaultDialog(this, title, message, cancelTitle, okTitle, {
            startPermissionRequest(permission, requestCode)
        }, {
            presenter.onRequestPermissionsResult(requestCode, PERMISSION_DENIED)
        }).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        presenter.onRequestPermissionsResult(requestCode, if (grantResults.any()) grantResults[0] else PERMISSION_DENIED)
    }

    private fun startPermissionRequest(permission: String, code: Int) {
        requestPermissions(this, arrayOf(permission), code)
    }
}
