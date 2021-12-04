package com.epam.springboot.homework1.core.repository;

import com.epam.springboot.homework1.core.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    Optional<Service> findByServiceId(Long id);

    List<Service> findAll();

    List<Service> findAllByAbonentList_Email(String email);

    Service findByServiceIdAndAbonentList_Email(Long serviceId, String email);

    Service save(Service service);

    @Transactional
    @Modifying
    @Query("update Service s set s.name = :name where s.serviceId = :serviceId")
    void updateById(@Param("name") String name, @Param("serviceId") Long id);

    @Transactional
    void deleteByServiceId(Long id);

}
