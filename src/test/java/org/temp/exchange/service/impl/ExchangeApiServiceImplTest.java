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
import org.temp.exchange.enums.RtnCode;
import org.temp.exchange.exception.SystemException;
import org.temp.exchange.model.request.*;
import org.temp.exchange.model.response.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ExchangeApiServiceImplTest {

    @Mock
    private DailyExchangeDataRepository dailyExchangeDataRepository;

    @InjectMocks
    private ExchangeApiServiceImpl exchangeApiService;

    @BeforeEach
    void setUp() {
    }

    // ======================== getExchangeData Tests =========================

    @Test
    void getExchangeDataWithDateAndTypeSuccess() throws Exception {
        // 指定日期和類型 只有查到一筆
        // request
        QueryExchangeDataRequest request = new QueryExchangeDataRequest();
        request.setType("USD/NTD");
        request.setDate("20230101");

        // database
        DailyExchangeData mockData = new DailyExchangeData();
        mockData.setType("USD/NTD");
        mockData.setDate("20230101");
        mockData.setRate("1.2345");

        when(dailyExchangeDataRepository.findAllByDateAndType("20230101", "USD/NTD")).thenReturn(mockData);

        // 執行
        QueryExchangeDataResponse response = exchangeApiService.getExchangeData(request);

        // 驗證
        assertNotNull(response);
        assertEquals(1, response.getExchangeDataList().size());
        assertEquals("USD/NTD", response.getExchangeDataList().get(0).getType());
    }

    @Test
    void getExchangeDataWithTypeSuccess() throws Exception {
        // 準備輸入和模擬行為
        QueryExchangeDataRequest request = new QueryExchangeDataRequest();
        request.setType("USD/NTD");

        DailyExchangeData mockData1 = new DailyExchangeData();
        mockData1.setType("USD/NTD");
        mockData1.setDate("20230101");
        mockData1.setRate("1.2345");

        DailyExchangeData mockData2 = new DailyExchangeData();
        mockData2.setType("USD/NTD");
        mockData2.setDate("20230102");
        mockData2.setRate("1.2350");

        when(dailyExchangeDataRepository.findAllByType("USD/NTD")).thenReturn(Arrays.asList(mockData1, mockData2));

        // 執行
        QueryExchangeDataResponse response = exchangeApiService.getExchangeData(request);

        // 驗證
        assertNotNull(response);
        assertEquals(2, response.getExchangeDataList().size());
    }

    @Test
    void getExchangeDataThrowsExceptionForEmptyType() {
        QueryExchangeDataRequest request = new QueryExchangeDataRequest();
        // 沒有設置type

        SystemException exception = assertThrows(SystemException.class, () -> {
            exchangeApiService.getExchangeData(request);
        });

        assertEquals(new SystemException(RtnCode.R2222).getRtnCode(), exception.getRtnCode());
    }

    @Test
    void getExchangeDataThrowsExceptionForNotFound() {
        QueryExchangeDataRequest request = new QueryExchangeDataRequest();
        request.setType("USD/NTD");

        SystemException exception = assertThrows(SystemException.class, () -> {
            exchangeApiService.getExchangeData(request);
        });

        assertEquals(new SystemException(RtnCode.R3333).getRtnCode(), exception.getRtnCode());
    }

    @Test
    void createExchangeDataSuccess() throws Exception {
        // 準備輸入
        CreateExchangeDataRequest request = new CreateExchangeDataRequest();
        request.setDate("20230101");
        request.setType("USD/NTD");
        request.setRate("1.2345");

        // 模擬Repository的行為，當save被調用時返回模擬的DailyExchangeData
        DailyExchangeData savedData = new DailyExchangeData();
        savedData.setType(request.getType());
        savedData.setDate(request.getDate());
        savedData.setRate(request.getRate());
        when(dailyExchangeDataRepository.save(any(DailyExchangeData.class))).thenReturn(savedData);

        // 執行
        CreateExchangeDataResponse response = exchangeApiService.createExchangeData(request);

        // 驗證
        assertNotNull(response, "Response should not be null");
        verify(dailyExchangeDataRepository, times(1)).save(any(DailyExchangeData.class));
    }

    @Test
    void createExchangeDataThrowsExceptionForEmptyFields() {
        // 故意留空以觸發異常
        CreateExchangeDataRequest request = new CreateExchangeDataRequest();
        // 不設置任何參數，模擬不完整的輸入

        SystemException exception = assertThrows(SystemException.class, () -> {
            exchangeApiService.createExchangeData(request);
        });

        assertEquals(new SystemException(RtnCode.R2222).getRtnCode(), exception.getRtnCode());
    }
//
//    // ======================== updateExchangeData Tests =========================
//
    @Test
    void updateExchangeDataSuccess() throws Exception {
        // 準備輸入
        UpdateExchangeDataRequest request = new UpdateExchangeDataRequest();
        request.setDate("20230101");
        request.setType("USD/NTD");
        request.setRate("1.2350");


        DailyExchangeData existingData = new DailyExchangeData();
        existingData.setType("USD/NTD");
        existingData.setDate("20230101");
        existingData.setRate("1.2345");

        when(dailyExchangeDataRepository.findAllByDateAndType(request.getDate(), request.getType())).thenReturn(existingData);

        // 模擬更新數據的保存操作
        when(dailyExchangeDataRepository.save(any(DailyExchangeData.class))).thenReturn(existingData);

        // 執行
        UpdateExchangeDataResponse response = exchangeApiService.updateExchangeData(request);

        // 驗證
        assertNotNull(response, "Response should not be null");
        verify(dailyExchangeDataRepository, times(1)).save(existingData);
    }
//
@Test
    void updateExchangeDataForEmptyFields() {
        UpdateExchangeDataRequest request = new UpdateExchangeDataRequest();


        SystemException exception = assertThrows(SystemException.class, () -> {
            exchangeApiService.updateExchangeData(request);
        });

        // 驗證預期的異常被拋出
        assertEquals(new SystemException(RtnCode.R2222).getRtnCode(), exception.getRtnCode());
    }

    @Test
    void updateExchangeDataNotFound() {
        // 準備輸入，設定一個很可能不存在的匯率數據
        UpdateExchangeDataRequest request = new UpdateExchangeDataRequest();
        request.setDate("20230101");
        request.setType("USD/NTD");
        request.setRate("1.2350");

        // 模擬找不到指定的匯率數據
        when(dailyExchangeDataRepository.findAllByDateAndType(request.getDate(), request.getType())).thenReturn(null);

        // 嘗試更新不存在的匯率數據，預期會拋出異常
        SystemException exception = assertThrows(SystemException.class, () -> {
            exchangeApiService.updateExchangeData(request);
        });

        // 驗證預期的異常被拋出
        assertEquals(new SystemException(RtnCode.R3333).getRtnCode(), exception.getRtnCode());
    }

//    // ======================== deleteExchangeData Tests =========================


    @Test
    void deleteExchangeDataThrowsExceptionForEmptyFields() {
        DeleteExchangeDataRequest request = new DeleteExchangeDataRequest();

        SystemException exception = assertThrows(SystemException.class, () -> {
            exchangeApiService.deleteExchangeData(request);
        });

        assertEquals(new SystemException(RtnCode.R2222).getRtnCode(), exception.getRtnCode());
    }

    @Test
    void deleteExchangeDataThrowsExceptionForNotFound() {
        DeleteExchangeDataRequest request = new DeleteExchangeDataRequest();
        request.setDate("20230101");
        request.setType("USD/NTD");

        when(dailyExchangeDataRepository.findAllByDateAndType(request.getDate(), request.getType())).thenReturn(null);

        SystemException exception = assertThrows(SystemException.class, () -> {
            exchangeApiService.deleteExchangeData(request);
        });

        // 驗證預期的異常被拋出
        assertEquals(new SystemException(RtnCode.R3333).getRtnCode(), exception.getRtnCode());
    }
}
