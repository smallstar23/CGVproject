package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.entity.Member;
import com.koreait.cgvproject.entity.Schedule;
import com.koreait.cgvproject.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findAllBySchedule(Schedule schedule);
    List<Ticket> findAllByMember(Member member);

    boolean existsTicketByMemberIdx(Long memberIdx);

    @Query(value = "SELECT * FROM ticket where mem_idx = :memberIdx", nativeQuery = true)
    List<Ticket> findByMemberIdx(Long memberIdx);
}
