package io.mcfadyen.gatling.resolver

import io.gatling.core.Predef._
import io.gatling.core.controller.inject.InjectionStep
import io.mcfadyen.gatling.config.RampUpConfig

import scala.concurrent.duration.FiniteDuration

object InjectionProfileResolver {

  def resolve(rampConfig: RampUpConfig, targetTps: Int, maxDuration: FiniteDuration): Seq[InjectionStep] = {
    var injectionSteps: Seq[InjectionStep] = Seq(constantUsersPerSec(targetTps) during maxDuration)
    if (rampConfig.isEnabled) {
      injectionSteps = Seq(rampUsersPerSec(1) to targetTps during rampConfig.getDuration) ++: injectionSteps
    }

    injectionSteps
  }
}
