package ru.test.weather.ui.presenters

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter
import moxy.MvpView

open class BasePresenter <V : MvpView> : MvpPresenter<V>(){
    private var disposables = CompositeDisposable()

    protected fun <T : Any> Single<T>.subscribeDispose(block: (t: T) -> Unit, errorBlock: (t: Throwable) -> Unit = {}) {
        disposables.add(
                this.subscribe({ block(it) }, { errorBlock(it) })
        )
    }

    protected fun <T : Any> Observable<T>.subscribeDispose(block: (t: T) -> Unit, errorBlock: (t: Throwable) -> Unit = {}) {
        disposables.add(
                this.subscribe({ block(it) }, { errorBlock(it) })
        )
    }

    override fun onDestroy() {
        disposables.dispose()
    }
}