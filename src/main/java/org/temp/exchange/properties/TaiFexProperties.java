package org.temp.exchange.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "taifex")
@Data
@ToString
public class TaiFexProperties {
    private String api;
}
