package id.rezkyauliapratama.fdetailmovie.common.utils

import id.rezkyauliapratama.fdetailmovie.common.utils.instantTaskExecutorRule
import io.kotlintest.Spec
import io.kotlintest.extensions.TestListener

object TestRulesListener : TestListener {

    override fun beforeSpec(spec: Spec) {
        instantTaskExecutorRule()
        println("Init test rules")
    }
}