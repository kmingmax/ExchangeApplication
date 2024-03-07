package org.temp.exchange.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("TYPE")
    String type;

    /**
     * 日期
     */
    @JsonProperty("DATE")
    String date;
}
