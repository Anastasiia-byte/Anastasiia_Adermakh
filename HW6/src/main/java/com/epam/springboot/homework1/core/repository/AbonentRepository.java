package com.epam.springboot.homework1.core.repository;

import com.epam.springboot.homework1.core.model.Abonent;
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
public interface AbonentRepository extends JpaRepository<Abonent, Long> {

    Optional<Abonent> findByEmail(String email);

    List<Abonent> findAll();

    Abonent save(Abonent abonent);

    @Transactional
    void deleteByEmail(String email);

    @Transactional
    @Modifying
    @Query("update Abonent a set a.firstName = :firstName, a.lastName = :lastName where a.abonentId = :abonentId")
    void updateById(@Param("firstName") String name, @Param("lastName") String surname,
                   @Param("abonentId") Long id);
}
