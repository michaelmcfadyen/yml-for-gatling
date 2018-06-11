package io.mcfadyen.gatling.resolver

import io.gatling.core.Predef._
import io.gatling.core.feeder.RecordSeqFeederBuilder
import io.gatling.core.feeder.SeparatedValuesParser.{CommaSeparator, SemicolonSeparator, TabulationSeparator}
import io.mcfadyen.gatling.config.FeederConfig

object FeederResolver {

  def resolveFeeder(feeder: FeederConfig): RecordSeqFeederBuilder[String] = {
    separatedValues(feeder.getFile, '"', resolvedSeparator(feeder.getType), '\0').circular
  }

  private def resolvedSeparator(feederType: String): Char = {
    feederType match {
      case "csv" => CommaSeparator
      case "ssv" => SemicolonSeparator
      case "tsv" => TabulationSeparator
      case _ => throw new IllegalArgumentException(s"Invalid feeder type $feederType. Supported feeders are [csv, ssv, tsv]")
    }
  }
}
