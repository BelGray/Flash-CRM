package com.belgray.flashcrm.repository;

import com.belgray.flashcrm.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByLastNameStartsWithIgnoreCase(String lastName);

}
