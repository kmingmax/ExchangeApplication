package org.temp.exchange.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * 查詢匯率Request
 */
@Getter
@Setter
public class QueryExchangeDataRequest {
    /**
     * 類型
     */
    @Schema(description = "匯率類型", example = "USD/NTD", required = true)
    @JsonProperty("TYPE")
    String type;

    /**
     * 日期
     */
    @Schema(description = "日期(可為空)", example = "2024/03/08")
    @JsonProperty("DATE")
    String date;
}
