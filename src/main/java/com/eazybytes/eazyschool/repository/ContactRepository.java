package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    List<Contact> findByStatus(String status);

    //@Query("select c from Contact c where c.status = :status")
    @Query(value = "SELECT * FROM contact_msg  WHERE status = ?1",nativeQuery = true)
    Page<Contact> findByStatusWithQuery(@Param("status") String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Contact c set c.status = ?1 where c.contactId= ?2 ")
    int updateStatusById(String status,int id);

    Page<Contact> findOpenMsgs(@Param("status")String status,Pageable pageable);

    @Transactional
    @Modifying
    int updateMsgStatus(String status, int id);



}
