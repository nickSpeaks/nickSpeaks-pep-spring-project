package com.example.repository;

import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Message;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

    @Transactional
    int deleteByMessageId(Integer id);

    List<Message> findAllByPostedBy(Integer postedBy);
}
