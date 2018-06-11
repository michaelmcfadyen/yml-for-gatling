package io.mcfadyen.gatling.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.mcfadyen.gatling.config.SimulationConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConfigResolver {

    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    // check classpath first
    // if not present check local path
    public static SimulationConfig loadResource(String configFile) throws IOException {
        mapper.registerModule(new JavaTimeModule());
        InputStream resourceAsStream = ConfigResolver.class.getClassLoader().getResourceAsStream(configFile);

        if (resourceAsStream == null) {
            // check local path
            resourceAsStream = new FileInputStream(configFile);

            if (resourceAsStream == null) {
                throw new IllegalArgumentException(String.format("File %s does not exist", configFile));
            }
        }

        return mapper.readValue(resourceAsStream, SimulationConfig.class);
    }
}
