package org.temp.exchange.service.impl;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.temp.exchange.database.entity.DailyExchangeData;
import org.temp.exchange.database.repository.DailyExchangeDataRepository;
import org.temp.exchange.enums.RtnCode;
import org.temp.exchange.exception.SystemException;
import org.temp.exchange.model.request.CreateExchangeDataRequest;
import org.temp.exchange.model.request.DeleteExchangeDataRequest;
import org.temp.exchange.model.request.QueryExchangeDataRequest;
import org.temp.exchange.model.request.UpdateExchangeDataRequest;
import org.temp.exchange.model.response.CreateExchangeDataResponse;
import org.temp.exchange.model.response.DeleteExchangeDataResponse;
import org.temp.exchange.model.response.QueryExchangeDataResponse;
import org.temp.exchange.model.response.UpdateExchangeDataResponse;
import org.temp.exchange.service.ExchangeApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ExchangeApiServiceImpl implements ExchangeApiService{

    private final DailyExchangeDataRepository dailyExchangeDataRepository;

    /**
     * @param model QueryExchangeDataRequest
     * @return QueryExchangeDataResponse
     * @throws SystemException
     */
    @Override
    public QueryExchangeDataResponse getExchangeData(QueryExchangeDataRequest model) throws SystemException {
        if (StringUtils.isEmpty(model.getType())) {
            throw new SystemException(RtnCode.R2222);
        }
        QueryExchangeDataResponse response = new QueryExchangeDataResponse();
        if (StringUtils.isEmpty(model.getDate())) {
            List<DailyExchangeData> dailyExchangeDataArrayList = dailyExchangeDataRepository.findAllByType(model.getType());
            if (dailyExchangeDataArrayList.isEmpty()) {
                throw new SystemException(RtnCode.R3333);
            } else {
                List<QueryExchangeDataResponse.ExchangeData> exchangeDataArrayList = dailyExchangeDataArrayList.stream()
                        .map(
                                dailyExchangeData ->  {
                                    QueryExchangeDataResponse.ExchangeData data = new QueryExchangeDataResponse.ExchangeData();
                                    data.setType(dailyExchangeData.getType());
                                    data.setDate(dailyExchangeData.getDate());
                                    data.setExchangeRate(dailyExchangeData.getRate());
                                    return data;
                                }
                        ) .collect(Collectors.toList());
                response.setExchangeDataList(exchangeDataArrayList);

            }
        } else {
            DailyExchangeData dailyExchangeData = dailyExchangeDataRepository.findAllByDateAndType(model.getDate(), model.getType());
            if (dailyExchangeData == null) {
                throw new SystemException(RtnCode.R3333);
            }
            List<QueryExchangeDataResponse.ExchangeData> exchangeDataArrayList = new ArrayList<>();
            QueryExchangeDataResponse.ExchangeData data = new QueryExchangeDataResponse.ExchangeData();
            data.setType(dailyExchangeData.getType());
            data.setDate(dailyExchangeData.getDate());
            data.setExchangeRate(dailyExchangeData.getRate());
            exchangeDataArrayList.add(data);
            response.setExchangeDataList(exchangeDataArrayList);
        }

        return response;
    }

    /**
     * @param model CreateExchangeDataRequest
     * @return CreateExchangeDataResponse
     * @throws SystemException
     */
    @Override
    public CreateExchangeDataResponse createExchangeData(CreateExchangeDataRequest model) throws SystemException {
        if (StringUtils.isEmpty(model.getDate()) | StringUtils.isEmpty(model.getType()) | StringUtils.isEmpty(model.getRate())) {
            throw new SystemException(RtnCode.R2222);
        }
        DailyExchangeData dailyExchangeData = new DailyExchangeData();
        dailyExchangeData.setType(model.getType());
        dailyExchangeData.setDate(model.getDate());
        dailyExchangeData.setRate(model.getRate());
        dailyExchangeDataRepository.save(dailyExchangeData);
        return new CreateExchangeDataResponse();
    }

    /**
     * @param model UpdateExchangeDataRequest
     * @return UpdateExchangeDataResponse
     * @throws SystemException
     */
    @Override
    public UpdateExchangeDataResponse updateExchangeData(UpdateExchangeDataRequest model) throws SystemException {
        if (StringUtils.isEmpty(model.getDate()) | StringUtils.isEmpty(model.getType()) | StringUtils.isEmpty(model.getRate())) {
            throw new SystemException(RtnCode.R2222);
        }
        DailyExchangeData dailyExchangeData = dailyExchangeDataRepository.findAllByDateAndType(model.getDate(), model.getType());
        if (dailyExchangeData == null) {
            throw new SystemException(RtnCode.R3333);
        }
        dailyExchangeData.setRate(model.getRate());
        dailyExchangeDataRepository.save(dailyExchangeData);
        return new UpdateExchangeDataResponse();
    }

    /**
     * @param model DeleteExchangeDataRequest
     * @return DeleteExchangeDataResponse
     * @throws SystemException
     */
    @Override
    public DeleteExchangeDataResponse deleteExchangeData(DeleteExchangeDataRequest model) throws SystemException {
        if (StringUtils.isEmpty(model.getDate()) | StringUtils.isEmpty(model.getType())) {
            throw new SystemException(RtnCode.R2222);
        }
        DailyExchangeData dailyExchangeData = dailyExchangeDataRepository.findAllByDateAndType(model.getDate(), model.getType());
        if (dailyExchangeData == null) {
            throw new SystemException(RtnCode.R3333);
        }
        dailyExchangeDataRepository.delete(dailyExchangeData);
        return new DeleteExchangeDataResponse();
    }
}
