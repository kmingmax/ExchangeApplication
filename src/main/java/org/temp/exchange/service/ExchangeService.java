package org.temp.exchange.service;

import org.springframework.stereotype.Service;
import org.temp.exchange.database.entity.DailyExchangeData;
import org.temp.exchange.exception.SystemException;

import java.util.List;

@Service
public interface ExchangeService {
    /**
     * 取得API資料
     * @return List<DailyExchangeData>
     * @throws SystemException
     */
    List<DailyExchangeData> getDailyExchangeData() throws SystemException;
}
