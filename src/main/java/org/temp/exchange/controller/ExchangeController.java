package org.temp.exchange.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.temp.exchange.database.entity.DailyExchangeData;
import org.temp.exchange.enums.RtnCode;
import org.temp.exchange.exception.SystemException;
import org.temp.exchange.model.response.DeleteExchangeDataResponse;
import org.temp.exchange.model.response.base.ExchangeApiResponse;
import org.temp.exchange.service.ExchangeService;

import java.util.List;

/**
 * 匯率頁面
 */
@Controller
@AllArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    @GetMapping(value = "/")
    public String model(Model model) throws SystemException {
        try {
            List<DailyExchangeData> exchangeDataList = exchangeService.getDailyExchangeData();
            model.addAttribute("data", exchangeDataList);

        } catch(SystemException se) {
            model.addAttribute("error", se.getRtnMsg());
        } catch(Exception e) {
            model.addAttribute("error", RtnCode.R9999.getMessage());
        }
        return "exchange";
    }
}
