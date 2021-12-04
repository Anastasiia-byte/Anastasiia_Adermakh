package com.epam.springboot.homework1.core.repository;

import com.epam.springboot.homework1.core.model.Service;
import com.epam.springboot.homework1.core.model.Tariff;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Long> {

    Optional<Tariff> findByService_TariffList_TariffId (Long tariffId);

    List<Tariff> findAllByService(Service service);

    Tariff save(Tariff tariff);

    @Transactional
    @Modifying
    @Query("update Tariff t set t.name = :name, t.price = :price where t.tariffId = :tariffId")
    void updateById(@Param("name") String name, @Param("price") int price, @Param("tariffId") Long id);

    @Transactional
    void deleteByTariffId(Long id);

    List<Tariff> findAllByService(Service service, Sort sort);
}
