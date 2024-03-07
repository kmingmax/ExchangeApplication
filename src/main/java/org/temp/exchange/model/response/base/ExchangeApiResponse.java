package org.temp.exchange.model.response.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "回應代碼", example = "0000")
    @JsonProperty("CODE")
    private String code;

    /**
     * 錯誤訊息
     */
    @Schema(description = "回應訊息", example = "(0000)成功")
    @JsonProperty("MESSAGE")
    private String message;

    /**
     * 資料
     */
    @Schema(description = "回傳資料")
    @JsonProperty("DATA")
    private T data;


    public ExchangeApiResponse(RtnCode rtnCode) {
        this.code = rtnCode.getCode();
        this.message = rtnCode.getMessage();
    }

    public ExchangeApiResponse(SystemException se) {
        this.code = se.getRtnCode();
        this.message = se.getRtnMsg();
    }

    public ExchangeApiResponse( T data) {
        this.code = RtnCode.R0000.getCode();
        this.message = RtnCode.R0000.getMessage();
        this.data = data;
    }

}
