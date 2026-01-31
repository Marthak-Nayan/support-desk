package com.minisupportdesk.Repository;

import com.minisupportdesk.entities.Status;
import com.minisupportdesk.entities.Ticket;
import com.minisupportdesk.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TicketRepositary extends JpaRepository<Ticket, Long> {

    Page<Ticket> findByCreatedById(Long userId, Pageable pageable);

    //Page<Ticket> findByStatusInAndPriorityIn(List<String> statuses,List<String> priorities, Pageable pageable);

    Page<Ticket> findByCreatedAt(Pageable pageable, LocalDate date);
    Page<Ticket> findByCreatedAtAndId(Pageable pageable,Long userId,LocalDate date);

    long countByCreatedAtBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
    long countByStatus(Status status);

    long countByCreatedByAndCreatedAtBetween(User user,LocalDateTime startOfDay, LocalDateTime endOfDay);
    long countByCreatedByAndStatus(User user, Status status);
}
