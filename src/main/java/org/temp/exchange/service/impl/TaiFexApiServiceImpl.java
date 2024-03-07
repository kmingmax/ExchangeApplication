package org.temp.exchange.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.temp.exchange.component.HttpClientService;
import org.temp.exchange.enums.RtnCode;
import org.temp.exchange.exception.SystemException;
import org.temp.exchange.model.taifex.request.TaiFexGetDailyExchangeRateRequest;
import org.temp.exchange.model.taifex.response.TaiFexGetDailyExchangeRateResponse;
import org.temp.exchange.properties.TaiFexProperties;
import org.temp.exchange.service.TaiFexApiService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaiFexApiServiceImpl implements TaiFexApiService {

    private final HttpClientService httpClientService;

    private final TaiFexProperties taiFexProperties;

    /**
     * @param taiFexGetDailyExchangeRateRequest  TaiFexGetDailyExchangeRateRequest
     * @return ArrayList<TaiFexGetDailyExchangeRateResponse>
     * @throws Exception
     */
    @Override
    public List<TaiFexGetDailyExchangeRateResponse> getDailyExchangeRate(TaiFexGetDailyExchangeRateRequest taiFexGetDailyExchangeRateRequest) throws SystemException {
        ParameterizedTypeReference<List<TaiFexGetDailyExchangeRateResponse>> responseType = new ParameterizedTypeReference<List<TaiFexGetDailyExchangeRateResponse>>() {};
        ResponseEntity<List<TaiFexGetDailyExchangeRateResponse>> response = httpClientService.get(taiFexProperties.getApi() + taiFexGetDailyExchangeRateRequest.getUrl(), responseType);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            System.out.println((response));
            throw new SystemException(RtnCode.R6666);
        }
    }
}
