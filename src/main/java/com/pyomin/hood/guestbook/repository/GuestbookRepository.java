package com.pyomin.hood.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pyomin.hood.guestbook.entity.Guestbook;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long> {

}
