package com.minisupportdesk.Repository;

import com.minisupportdesk.entities.Priority;
import com.minisupportdesk.entities.Status;
import com.minisupportdesk.entities.Ticket;
import com.minisupportdesk.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepositary extends JpaRepository<Ticket, Long> {

    Page<Ticket> findAllByCreatedById(Long userId, Pageable pageable);


    long countByCreatedAtBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
    long countByStatus(Status status);

    long countByCreatedByAndCreatedAtBetween(User user,LocalDateTime startOfDay, LocalDateTime endOfDay);
    long countByCreatedByAndStatus(User user, Status status);

    Page<Ticket> findAllByStatusInAndPriorityIn(List<Status> status, List<Priority> priority, Pageable pageable);
    Page<Ticket> findAllByStatusIn(List<Status> statuses, Pageable pageable);
    Page<Ticket> findAllByPriorityIn(List<Priority> priorities, Pageable pageable);

    Page<Ticket> findAllByCreatedByIdAndStatusInAndPriorityIn(Long userId, List<Status> statuses, List<Priority> priorities, Pageable pageable );
    Page<Ticket> findAllByCreatedByIdAndPriorityIn(Long userId, List<Priority> priorities, Pageable pageable);
    Page<Ticket> findAllByCreatedByIdAndStatusIn(Long userId,List<Status> statuses, Pageable pageable);

}
