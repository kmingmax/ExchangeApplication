package org.temp.exchange.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "查詢結果")
    @JsonProperty("EXCHANGE_DATA_LIST")
    List<ExchangeData> exchangeDataList;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ExchangeData {
        @Schema(description = "匯率類型", example = "USD/NTD")
        @JsonProperty("TYPE")
        String type;

        @Schema(description = "日期類型", example = "20240401")
        @JsonProperty("DATE")
        String date;

        @Schema(description = "匯率", example = "40")
        @JsonProperty("EXCHANGE_RATE")
        String exchangeRate;
    }
}
