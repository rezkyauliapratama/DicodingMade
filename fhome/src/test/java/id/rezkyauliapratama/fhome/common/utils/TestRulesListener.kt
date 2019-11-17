package id.rezkyauliapratama.fhome.common.utils

import io.kotlintest.Spec
import io.kotlintest.extensions.TestListener

object TestRulesListener : TestListener {

    override fun beforeSpec(spec: Spec) {
        instantTaskExecutorRule()
        println("Init test rules")
    }
}