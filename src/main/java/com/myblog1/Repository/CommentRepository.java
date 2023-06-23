package com.myblog1.Repository;

import com.myblog1.Entity.Comment;
import com.myblog1.Payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(long postId);
}
