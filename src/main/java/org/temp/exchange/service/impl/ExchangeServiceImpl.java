package org.temp.exchange.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.temp.exchange.database.entity.DailyExchangeData;
import org.temp.exchange.database.repository.DailyExchangeDataRepository;
import org.temp.exchange.exception.SystemException;
import org.temp.exchange.model.taifex.request.TaiFexGetDailyExchangeRateRequest;
import org.temp.exchange.model.taifex.response.TaiFexGetDailyExchangeRateResponse;
import org.temp.exchange.service.ExchangeService;
import org.temp.exchange.service.TaiFexApiService;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class ExchangeServiceImpl  implements ExchangeService {

    private final DailyExchangeDataRepository dailyExchangeDataRepository;

    private final TaiFexApiService taiFexApiService;


    public List<DailyExchangeData> getDailyExchangeData() throws SystemException {
        List<DailyExchangeData> dailyExchangeDataList = new ArrayList<>();
        List<TaiFexGetDailyExchangeRateResponse> taiFexGetDailyExchangeRateResponseList = taiFexApiService.getDailyExchangeRate(new TaiFexGetDailyExchangeRateRequest());
        for (TaiFexGetDailyExchangeRateResponse taiFexGetDailyExchangeRateResponse : taiFexGetDailyExchangeRateResponseList) {

            DailyExchangeData usd2ntd = new DailyExchangeData();
            usd2ntd.setDate(taiFexGetDailyExchangeRateResponse.getDate());
            usd2ntd.setType("USD/NTD");
            usd2ntd.setRate(taiFexGetDailyExchangeRateResponse.getUsdNtd());

            DailyExchangeData rmb2ntd = new DailyExchangeData();
            rmb2ntd.setDate(taiFexGetDailyExchangeRateResponse.getDate());
            rmb2ntd.setType("RMB/NDT");
            rmb2ntd.setRate(taiFexGetDailyExchangeRateResponse.getRmbNtd());

            DailyExchangeData usd2rmb = new DailyExchangeData();
            usd2rmb.setDate(taiFexGetDailyExchangeRateResponse.getDate());
            usd2rmb.setType("USD/RMB");
            usd2rmb.setRate(taiFexGetDailyExchangeRateResponse.getUsdRmb());

            dailyExchangeDataList.add(usd2ntd);
            dailyExchangeDataList.add(rmb2ntd);
            dailyExchangeDataList.add(usd2rmb);
        }
        dailyExchangeDataRepository.saveAll(dailyExchangeDataList);

        return dailyExchangeDataList;
    }
}
