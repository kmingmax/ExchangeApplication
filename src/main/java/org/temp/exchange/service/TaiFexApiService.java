package org.temp.exchange.service;

import org.springframework.stereotype.Service;
import org.temp.exchange.exception.SystemException;
import org.temp.exchange.model.taifex.request.TaiFexGetDailyExchangeRateRequest;
import org.temp.exchange.model.taifex.response.TaiFexGetDailyExchangeRateResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public interface TaiFexApiService {

    List<TaiFexGetDailyExchangeRateResponse> getDailyExchangeRate(TaiFexGetDailyExchangeRateRequest taiFexGetDailyExchangeRateRequest) throws SystemException;
}
