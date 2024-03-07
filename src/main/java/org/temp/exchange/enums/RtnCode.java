package org.temp.exchange.enums;

import lombok.Getter;

/**
 * RtnCode格式
 */
@Getter
public enum RtnCode {

    R0000("0000","成功"),
    R2222("2222", "格式錯誤"),
    R3333("3333", "資料不存在"),
    R6666("6666", "TaiFex Api 異常"),
    R9999("9999", "系統異常");


    private final String code;

    private final String message;


    RtnCode(String code, String message){
        this.code = code;
        this.message = String.format("(%s)%s",code,message);
    }
}
