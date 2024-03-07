package org.temp.exchange.service;

import org.springframework.stereotype.Service;
import org.temp.exchange.exception.SystemException;
import org.temp.exchange.model.request.CreateExchangeDataRequest;
import org.temp.exchange.model.request.DeleteExchangeDataRequest;
import org.temp.exchange.model.request.QueryExchangeDataRequest;
import org.temp.exchange.model.request.UpdateExchangeDataRequest;
import org.temp.exchange.model.response.CreateExchangeDataResponse;
import org.temp.exchange.model.response.DeleteExchangeDataResponse;
import org.temp.exchange.model.response.QueryExchangeDataResponse;
import org.temp.exchange.model.response.UpdateExchangeDataResponse;
@Service
public interface ExchangeApiService {

    /**
     * 查詢匯率
     * @param model QueryExchangeDataRequest
     * @return QueryExchangeDataResponse
     * @throws SystemException
     */
    public QueryExchangeDataResponse getExchangeData(QueryExchangeDataRequest model) throws SystemException;

    /**
     * 新增匯率
     * @param model CreateExchangeDataRequest
     * @return CreateExchangeDataResponse
     * @throws SystemException
     */
    public CreateExchangeDataResponse createExchangeData(CreateExchangeDataRequest model) throws SystemException;

    /**
     * 修改匯率
     * @param model UpdateExchangeDataRequest
     * @return UpdateExchangeDataResponse
     * @throws SystemException
     */
    public UpdateExchangeDataResponse updateExchangeData(UpdateExchangeDataRequest model) throws SystemException;

    /**
     * 刪除匯率
     * @param model DeleteExchangeDataRequest
     * @return DeleteExchangeDataResponse
     * @throws SystemException
     */
    public DeleteExchangeDataResponse deleteExchangeData(DeleteExchangeDataRequest model) throws SystemException;


}
