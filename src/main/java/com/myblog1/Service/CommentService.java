package com.myblog1.Service;

import com.myblog1.Payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getAllCommentsByPostId(long postId);

    CommentDto getCommentById(long postId,long id);

    CommentDto updatepost(long postId, long id, CommentDto commentDto);

    void deleteComment(long postId, long id);
}
