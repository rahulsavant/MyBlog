package com.myblog1.Service.Impl;

import com.myblog1.Entity.Comment;
import com.myblog1.Entity.Post;
import com.myblog1.Exception.ResourceNotFoundException;
import com.myblog1.Payload.CommentDto;
import com.myblog1.Repository.CommentRepository;
import com.myblog1.Repository.PostRepository;
import com.myblog1.Service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with id:" + postId)
        );
        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with id:" + postId)
        );
        List<Comment> comments = commentRepository.findByPostId(postId);
       return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId,long id) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with id:" + postId)
        );
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment not found with id:" + id)
        );
        if (comment.getPost().getId() != postId) {
            throw new ResourceNotFoundException("Comment does not belong to the specified post");
        }
       return mapToDto(comment);
    }

    @Override
    public CommentDto updatepost(long postId, long id, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with id:" + postId)
        );
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment not found with id:" + id)
        );
        if (comment.getPost().getId()!=postId){
            throw new ResourceNotFoundException("Comment does not belong to the specified post");
        }
       comment.setName(commentDto.getName());
       comment.setEmail(commentDto.getEmail());
       comment.setBody(commentDto.getBody());
        Comment updatedComment = commentRepository.save(comment);
        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(long postId, long id) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with id:" + postId)
        );
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment not found with id:" + id)
        );
        if (comment.getPost().getId()!=postId){
            throw new ResourceNotFoundException("Comment does not belong to specified post");
        }
        commentRepository.deleteById(id);
    }

    CommentDto mapToDto (Comment comment){
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
//        CommentDto commentDto=new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    Comment mapToEntity (CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto, Comment.class);
//        Comment comment=new Comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        return comment;
    }
}
