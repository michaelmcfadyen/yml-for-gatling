package io.mcfadyen.gatling.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import scala.concurrent.duration.FiniteDuration;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SimulationConfig {

    @JsonProperty("title")
    private String title;
    @JsonProperty("ramp")
    private RampUpConfig rampUpConfig;
    @JsonProperty("scenarios")
    private List<Scenario> scenarios;
    @JsonProperty("maxDuration")
    private Duration maxDuration;

    public RampUpConfig getRampUpConfig() {
        return rampUpConfig;
    }

    public void setRampUpConfig(RampUpConfig rampUpConfig) {
        this.rampUpConfig = rampUpConfig;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Scenario> getScenarios() {
        return scenarios;
    }

    public void setScenarios(List<Scenario> scenarios) {
        this.scenarios = scenarios;
    }

    public FiniteDuration getMaxDuration() {
        return new FiniteDuration(maxDuration.getSeconds(), TimeUnit.SECONDS);
    }

    public void setMaxDuration(Duration maxDuration) {
        this.maxDuration = maxDuration;
    }
}
