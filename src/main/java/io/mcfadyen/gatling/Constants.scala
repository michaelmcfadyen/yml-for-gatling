package io.mcfadyen.gatling

object Constants {

  val baseUrl: String = validateProperty("gatling.base.url")
  val configFile: String = validateProperty("gatling.yml.file")

  private def validateProperty(property: String) = {
    val prop = System.getProperty(property)
    if(prop == null)
      throw new IllegalArgumentException(s"$property is required")

    prop
  }
}
