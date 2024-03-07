package org.temp.exchange.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
/**
 * 查詢匯率Response
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QueryExchangeDataResponse {


    @JsonProperty("EXCHANGE_DATA_LIST")
    List<ExchangeData> exchangeDataList;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ExchangeData {
        @JsonProperty("TYPE")
        String type;

        @JsonProperty("DATE")
        String date;

        @JsonProperty("EXCHANGE_RATE")
        String exchangeRate;
    }
}
