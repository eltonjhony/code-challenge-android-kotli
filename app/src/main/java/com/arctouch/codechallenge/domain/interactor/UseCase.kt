package com.arctouch.codechallenge.domain.interactor

import com.arctouch.codechallenge.domain.executor.SchedulersFacade
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

abstract class UseCase<T, in Params>(private val schedulersFacade: SchedulersFacade) where T : Any {

    private val compositeDisposable = CompositeDisposable()

    open fun dispose() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    operator fun invoke(params: Params, onResult: (T?, Throwable?) -> Unit = { _: T?, _: Throwable? -> }) {
        compositeDisposable.add(run(params)
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .subscribe { result, throwable -> onResult(result, throwable) })
    }

    abstract fun run(params: Params): Single<T>
}