package com.pyomin.hood.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pyomin.hood.guestbook.entity.Guestbook;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long> {

    @Modifying
    @Query("UPDATE Guestbook g SET g.author = :#{#guestbook.author}, g.message = :#{#guestbook.message} WHERE g.id = :#{#guestbook.id} AND g.password = :#{#guestbook.password}")
    int updateGuestbook(@Param("guestbook") Guestbook guestbook);
}
