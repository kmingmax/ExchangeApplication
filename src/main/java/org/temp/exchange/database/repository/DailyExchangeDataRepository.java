package org.temp.exchange.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.temp.exchange.database.entity.DailyExchangeData;
import org.temp.exchange.database.entity.idClass.DailyExchangeDataId;

import java.util.List;

@Repository
public interface DailyExchangeDataRepository extends JpaRepository<DailyExchangeData, DailyExchangeDataId> {

    DailyExchangeData findAllByDateAndType(String date, String type);

    List<DailyExchangeData> findAllByType(String type);

    @Override
    List<DailyExchangeData> findAll();
}
