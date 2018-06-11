package io.mcfadyen.gatling

import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder

object Engine extends App {

  val props = new GatlingPropertiesBuilder()
    .simulationClass("io.mcfadyen.gatling.YmlForGatling")

  Gatling.fromMap(props.build)
}
