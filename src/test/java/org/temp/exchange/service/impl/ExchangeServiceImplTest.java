package org.temp.exchange.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.temp.exchange.database.entity.DailyExchangeData;
import org.temp.exchange.database.repository.DailyExchangeDataRepository;

import org.temp.exchange.model.taifex.response.TaiFexGetDailyExchangeRateResponse;
import org.temp.exchange.service.TaiFexApiService;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class ExchangeServiceImplTest {

    @Mock
    private DailyExchangeDataRepository dailyExchangeDataRepository;

    @Mock
    private TaiFexApiService taiFexApiService;

    @InjectMocks
    private ExchangeServiceImpl exchangeService;

    @BeforeEach
    void setUp() {
        // 初始化操作，如有需要
    }

    @Test
    void getDailyExchangeDataSuccess() throws Exception {
        // 模擬TaiFexApiService返回的數據
        TaiFexGetDailyExchangeRateResponse taiFexResponse = new TaiFexGetDailyExchangeRateResponse();
        taiFexResponse.setDate("2023-01-01");
        taiFexResponse.setUsdNtd("30.0");
        taiFexResponse.setRmbNtd("4.5");
        taiFexResponse.setUsdRmb("6.7");
        List<TaiFexGetDailyExchangeRateResponse> responseList = new ArrayList<>();
        responseList.add(taiFexResponse);

        when(taiFexApiService.getDailyExchangeRate(any())).thenReturn(responseList);

        // 執行
        List<DailyExchangeData> result = exchangeService.getDailyExchangeData();

        // 驗證
        assertNotNull(result);
        assertEquals(3, result.size()); // 預期有3條匯率數據
        verify(dailyExchangeDataRepository, times(1)).saveAll(anyList());
    }
}
