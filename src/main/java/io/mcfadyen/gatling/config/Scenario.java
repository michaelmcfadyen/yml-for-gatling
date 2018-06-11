package io.mcfadyen.gatling.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Scenario {

    @JsonProperty("title")
    private String title;
    @JsonProperty("method")
    private String httpMethod;
    @JsonProperty("endpoint")
    private String endpoint;
    @JsonProperty("targetTps")
    private int targetTps;
    @JsonProperty("feeder")
    private List<FeederConfig> feederConfigs = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public int getTargetTps() {
        return targetTps;
    }

    public void setTargetTps(int targetTps) {
        this.targetTps = targetTps;
    }

    public List<FeederConfig> getFeederConfigs() {
        return feederConfigs;
    }

    public void setFeederConfigs(List<FeederConfig> feederConfigs) {
        this.feederConfigs = feederConfigs;
    }
}
