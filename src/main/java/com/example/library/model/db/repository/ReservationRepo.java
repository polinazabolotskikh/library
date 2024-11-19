package com.example.library.model.db.repository;

import com.example.library.model.db.entity.Reservation;
import com.example.library.model.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation,Long> {
    @Query("SELECT u.book FROM Reservation u WHERE u.libraryCard = ?1 and u.status!='DELETED'")
    List<Long> findBook(String libraryCard);

    @Query("SELECT u.dateBorrow FROM Reservation u WHERE u.libraryCard = ?1 and u.book=?2 and u.status!='DELETED'")
    String getDateBorrow(String libraryCard, Long book);

    @Query("SELECT u.dateReturn FROM Reservation u WHERE u.libraryCard = ?1 and u.book=?2 and u.status!='DELETED'")
    String getReturn(String libraryCard, Long book);

    @Query("SELECT u.book FROM Reservation u WHERE u.libraryCard = ?1 and u.book=?2 and u.status!='DELETED'")
    List<Long> findId(String libraryCard, Long book);

    @Query("SELECT u.id FROM Reservation u WHERE u.libraryCard = ?1 and u.book=?2 and u.status!='DELETED'")
    Long findReservation(String libraryCard, Long book);
    @Query("SELECT COUNT(*) FROM Reservation u WHERE u.libraryCard = ?1 and u.status!='DELETED'")
    Integer countReservation(String libraryCard);
    @Query("SELECT u FROM Reservation u WHERE u.libraryCard = ?1 and u.status!='DELETED'")
    List <Reservation> findByLibraryCard(String libraryCard);
}
