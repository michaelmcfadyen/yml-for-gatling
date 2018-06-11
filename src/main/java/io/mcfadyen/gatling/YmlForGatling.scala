package io.mcfadyen.gatling

import io.gatling.core.Predef.{scenario, _}
import io.gatling.core.controller.inject.InjectionStep
import io.gatling.core.structure.{PopulationBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.mcfadyen.gatling.resolver.{FeederResolver, InjectionProfileResolver}

import scala.collection.JavaConverters._
import scala.concurrent.duration.FiniteDuration

class YmlForGatling extends Simulation {

  private def buildScenario(scenarioConfig: config.Scenario, maxDuration: FiniteDuration, rampConfig: config.RampUpConfig): PopulationBuilder = {

    var scenarioDefinition: ScenarioBuilder = scenario(scenarioConfig.getTitle)

    for (feedConfig <- collectionAsScalaIterable(scenarioConfig.getFeederConfigs)) scenarioDefinition = scenarioDefinition.feed(FeederResolver.resolveFeeder(feedConfig))

    val httpCall = http(scenarioConfig.getTitle)
      .httpRequest(scenarioConfig.getHttpMethod, scenarioConfig.getEndpoint)

    val injectionSteps: Seq[InjectionStep] = InjectionProfileResolver.resolve(rampConfig, scenarioConfig.getTargetTps, maxDuration)

    scenarioDefinition.exec(httpCall)
      .inject(injectionSteps)
  }

  def run(): Unit = {
    val config = resolver.ConfigResolver.loadResource(Constants.configFile)

    val simulation: List[PopulationBuilder] = (for (scenarioConfig <- collectionAsScalaIterable(config.getScenarios)) yield buildScenario(scenarioConfig, config.getMaxDuration, config.getRampUpConfig)).toList

    setUp(simulation)
      .protocols(http.baseURL(Constants.baseUrl))
      .maxDuration(if (config.getRampUpConfig.isEnabled) config.getMaxDuration + config.getRampUpConfig.getDuration else config.getMaxDuration)
  }

  run()
}