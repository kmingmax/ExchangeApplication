package org.temp.exchange.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @NonNull
    @JsonProperty("TYPE")
    String type;

    /**
     * 日期
     */
    @NonNull
    @JsonProperty("DATE")
    String date;

    /**
     * 匯率
     */
    @NonNull
    @JsonProperty("RATE")
    String rate;
}
