package io.mcfadyen.gatling.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import scala.concurrent.duration.FiniteDuration;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class RampUpConfig {

    @JsonProperty("enabled")
    private boolean enabled;
    @JsonProperty("duration")
    private Duration duration;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public FiniteDuration getDuration() {
        return new FiniteDuration(duration.getSeconds(), TimeUnit.SECONDS);
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
