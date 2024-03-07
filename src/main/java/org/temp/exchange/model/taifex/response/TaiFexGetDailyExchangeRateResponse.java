package org.temp.exchange.model.taifex.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 匯率回傳Response
 */
@Getter
@Setter
public class TaiFexGetDailyExchangeRateResponse implements Serializable {
    @JsonProperty("Date")
    private String date;

    @JsonProperty("USD/NTD")
    private String usdNtd;

    @JsonProperty("RMB/NTD")
    private String rmbNtd;

    @JsonProperty("EUR/USD")
    private String eurUsd;

    @JsonProperty("USD/JPY")
    private String usdJpy;

    @JsonProperty("GBP/USD")
    private String gbpUsd;

    @JsonProperty("AUD/USD")
    private String audUsd;

    @JsonProperty("USD/HKD")
    private String usdHkd;

    @JsonProperty("USD/RMB")
    private String usdRmb;

    @JsonProperty("USD/ZAR")
    private String usdZar;

    @JsonProperty("NZD/USD")
    private String nzdUsd;
}
