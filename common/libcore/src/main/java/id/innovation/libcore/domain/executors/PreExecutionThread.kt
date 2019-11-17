package id.innovation.libcore.domain.executors

import io.reactivex.Scheduler

interface PreExecutionThread {
    val scheduler: Scheduler
}
