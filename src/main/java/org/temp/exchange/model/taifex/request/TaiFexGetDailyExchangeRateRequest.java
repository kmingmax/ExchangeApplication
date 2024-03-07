package org.temp.exchange.model.taifex.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import org.temp.exchange.model.taifex.BaseTaiFexApiRequest;

/**
 * TaiFexGetDailyExchangeRateRequest
 */
@Getter
@Setter
@AllArgsConstructor
public class TaiFexGetDailyExchangeRateRequest extends BaseTaiFexApiRequest {

    private  final HttpMethod method = HttpMethod.GET;

    private  final String url = "/DailyForeignExchangeRates";
}
