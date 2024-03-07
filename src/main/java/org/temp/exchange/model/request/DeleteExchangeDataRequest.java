package org.temp.exchange.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * 刪除匯率Request
 */
@Getter
@Setter
public class DeleteExchangeDataRequest {
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