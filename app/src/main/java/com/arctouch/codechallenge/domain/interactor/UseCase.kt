package com.arctouch.codechallenge.domain.interactor

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class UseCase<T, in Params> where T : Any {

    private val compositeDisposable = CompositeDisposable()

    open fun dispose() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    operator fun invoke(params: Params, onResult: (T?, Throwable?) -> Unit = { _: T?, _: Throwable? -> }) {
        compositeDisposable.add(run(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result, throwable -> onResult(result, throwable) })
    }

    abstract fun run(params: Params): Single<T>
}