package com.arctouch.codechallenge.domain.executor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SchedulersFacade @Inject constructor() {

    fun io() : Scheduler {
        return Schedulers.io()
    }

    fun ui() : Scheduler {
        return AndroidSchedulers.mainThread()
    }
}