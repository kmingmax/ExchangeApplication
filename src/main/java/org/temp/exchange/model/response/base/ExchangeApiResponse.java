package org.temp.exchange.model.response.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.temp.exchange.enums.RtnCode;
import org.temp.exchange.exception.SystemException;

/**
 * 回傳統一格式
 * @param <T> Data
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExchangeApiResponse<T> {
    /**
     * 代碼
     */
    @JsonProperty("CODE")
    private String code;

    /**
     * 錯誤訊息
     */
    @JsonProperty("MESSAGE")
    private String message;

    /**
     * 資料
     */
    @JsonProperty("DATA")
    private T data;


    public ExchangeApiResponse(RtnCode rtnCode) {
        this.code = rtnCode.getCode();
        this.message = rtnCode.getMessage();
    }

    public ExchangeApiResponse(SystemException se) {
        this.code = se.getRtnCode();
        this.message = se.getMessage();
    }

    public ExchangeApiResponse( T data) {
        this.code = RtnCode.R0000.getCode();
        this.message = RtnCode.R0000.getMessage();
        this.data = data;
    }

}
