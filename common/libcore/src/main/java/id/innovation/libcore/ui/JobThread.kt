package id.innovation.libcore.ui

import id.innovation.libcore.data.executors.JobExecutor
import id.innovation.libcore.domain.executors.PreExecutionThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class JobThread : PreExecutionThread {

    override val scheduler: Scheduler
        get() = Schedulers.from(JobExecutor())
}
