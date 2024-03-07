package org.temp.exchange.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.temp.exchange.component.HttpClientService;
import org.temp.exchange.enums.RtnCode;
import org.temp.exchange.exception.SystemException;
import org.temp.exchange.model.taifex.request.TaiFexGetDailyExchangeRateRequest;
import org.temp.exchange.model.taifex.response.TaiFexGetDailyExchangeRateResponse;
import org.temp.exchange.properties.TaiFexProperties;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaiFexApiServiceImplTest {

    @Mock
    private HttpClientService httpClientService;

    @Mock
    private TaiFexProperties taiFexProperties;

    @InjectMocks
    private TaiFexApiServiceImpl taiFexApiService;

    @BeforeEach
    void setUp() {
        // 初始化操作，如有需要
    }

    @Test
    void getDailyExchangeRateSuccess() throws Exception {
        // 準備模擬響應
        TaiFexGetDailyExchangeRateResponse mockResponse = new TaiFexGetDailyExchangeRateResponse();
        // 設置mockResponse的屬性值
        List<TaiFexGetDailyExchangeRateResponse> mockResponseList = new ArrayList<>();
        mockResponseList.add(mockResponse);

        when(taiFexProperties.getApi()).thenReturn("http://example.com/api/");
        when(httpClientService.get(anyString(), any(ParameterizedTypeReference.class)))
                .thenReturn(new ResponseEntity<>(mockResponseList, HttpStatus.OK));

        // 執行
        List<TaiFexGetDailyExchangeRateResponse> result = taiFexApiService.getDailyExchangeRate(new TaiFexGetDailyExchangeRateRequest());

        // 驗證
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void getDailyExchangeRateThrowsSystemExceptionForNonSuccessResponse() {
        // 模擬非成功響應
        when(taiFexProperties.getApi()).thenReturn("http://example.com/api/");
        when(httpClientService.get(anyString(), any(ParameterizedTypeReference.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

        // 執行並驗證預期拋出的異常
        SystemException exception = assertThrows(SystemException.class, () -> {
            taiFexApiService.getDailyExchangeRate(new TaiFexGetDailyExchangeRateRequest());
        });

        // 驗證預期的異常被拋出
        assertEquals(new SystemException(RtnCode.R6666).getRtnCode(), exception.getRtnCode());
    }
}
