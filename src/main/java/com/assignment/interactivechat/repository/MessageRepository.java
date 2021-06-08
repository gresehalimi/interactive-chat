package com.assignment.interactivechat.repository;

import com.assignment.interactivechat.constants.MessageStatus;
import com.assignment.interactivechat.model.Message;
import com.assignment.interactivechat.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

   // @Query("Select m from Message m where m.user=?1 and m.messageStatus=?2")
    Page<Message> findByUserAndMessageStatus(User user, MessageStatus messageStatus, Pageable pageable);
}
