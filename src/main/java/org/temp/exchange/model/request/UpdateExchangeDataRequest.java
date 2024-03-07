package org.temp.exchange.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * 修改匯率Request
 */
@Getter
@Setter
public class UpdateExchangeDataRequest {
    /**
     * 類型
     */
    @Schema(description = "匯率類型", example = "USD/NTD", required = true)
    @JsonProperty("TYPE")
    String type;

    /**
     * 日期
     */
    @Schema(description = "日期", example = "2024/03/08", required = true)
    @JsonProperty("DATE")
    String date;

    /**
     * 匯率
     */
    @Schema(description = "匯率", example = "62", required = true)
    @JsonProperty("RATE")
    String rate;
}
