package org.temp.exchange.database.entity.idClass;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *  DailyExchangeData Id Class
 */
@Getter
@Setter
public class DailyExchangeDataId implements Serializable {

    private String date;
    private String type;
}
