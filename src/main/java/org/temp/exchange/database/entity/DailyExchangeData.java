package org.temp.exchange.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.temp.exchange.database.entity.idClass.DailyExchangeDataId;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 匯率資料 Database
 */
@Getter
@Setter
@Entity
@IdClass(DailyExchangeDataId.class)
@Table(name = "daily_exchange_data")
public class DailyExchangeData implements Serializable {
    // 日期
    @Id
    private String date;

    //匯率類型
    @Id
    private String type;

    // 匯率
    private String rate;

}
