package org.temp.exchange.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
import org.temp.exchange.model.response.base.ExchangeApiResponse;
import org.temp.exchange.service.ExchangeApiService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 匯率API
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api")
public class ExchangeApiController {

    private final ExchangeApiService exchangeApiService;


    @PostMapping(value = "/query")
    public ExchangeApiResponse<?> queryExchange(@RequestBody QueryExchangeDataRequest model, HttpServletRequest req, HttpServletResponse res)  {
        try {
            QueryExchangeDataResponse queryExchangeDataResponse = exchangeApiService.getExchangeData(model);
            return new ExchangeApiResponse<>(queryExchangeDataResponse);
        } catch(SystemException se) {
            return new ExchangeApiResponse<>(se);
        } catch(Exception e) {
            return new ExchangeApiResponse<>(RtnCode.R9999);
        }

    }

    @PostMapping(value = "/create")
    public ExchangeApiResponse<?> createExchange(@RequestBody CreateExchangeDataRequest model, HttpServletRequest req, HttpServletResponse res)  {
        try {
            CreateExchangeDataResponse createExchangeDataResponse = exchangeApiService.createExchangeData(model);
            return new ExchangeApiResponse<>(createExchangeDataResponse);
        } catch(SystemException se) {
            return new ExchangeApiResponse<>(se);
        } catch(Exception e) {
            return new ExchangeApiResponse<>(RtnCode.R9999);
        }

    }

    @PostMapping(value = "/update")
    public ExchangeApiResponse<?> updateExchange(@RequestBody UpdateExchangeDataRequest model, HttpServletRequest req, HttpServletResponse res)  {
        try {
            UpdateExchangeDataResponse updateExchangeDataResponse = exchangeApiService.updateExchangeData(model);
            return new ExchangeApiResponse<>(updateExchangeDataResponse);
        } catch(SystemException se) {
            return new ExchangeApiResponse<>(se);
        } catch(Exception e) {
            return new ExchangeApiResponse<>(RtnCode.R9999);
        }

    }

    @PostMapping(value = "/delete")
    public ExchangeApiResponse<?> deleteExchange(@RequestBody DeleteExchangeDataRequest model, HttpServletRequest req, HttpServletResponse res)  {
        try {
            DeleteExchangeDataResponse deleteExchangeDataResponse = exchangeApiService.deleteExchangeData(model);
            return new ExchangeApiResponse<>(deleteExchangeDataResponse);
        } catch(SystemException se) {
            return new ExchangeApiResponse<>(se);
        } catch(Exception e) {
            return new ExchangeApiResponse<>(RtnCode.R9999);
        }

    }










}
