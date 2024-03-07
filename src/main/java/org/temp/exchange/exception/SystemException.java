package org.temp.exchange.exception;

import lombok.Getter;
import lombok.Setter;
import org.temp.exchange.enums.RtnCode;

/**
 * 系統ExceptionHandler
 */

@Getter
@Setter
public class SystemException extends  Exception {
    private static final long serialVersionUID = 1L;
    private String rtnCode;
    private String rtnMsg;

    public SystemException(RtnCode rtnCode) {
        this.rtnCode = rtnCode.getCode();
        this.rtnMsg = rtnCode.getMessage();
    }
}
