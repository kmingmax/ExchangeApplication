package org.temp.exchange.model.taifex;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;

@Getter
@Setter
public class BaseTaiFexApiRequest {

    private HttpMethod method;

    private String url;

}
