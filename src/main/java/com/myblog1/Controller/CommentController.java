package com.myblog1.Controller;

import com.myblog1.Payload.CommentDto;
import com.myblog1.Service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //http://localhost:8080/api/posts/2/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<?> createComment( @PathVariable("postId") long postId,@Valid @RequestBody CommentDto commentDto,
                                                    BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        CommentDto comment = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/posts/2/comments
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getAllCommentsByPostId(@PathVariable("postId") long postId){
        return commentService.getAllCommentsByPostId(postId);
    }

    //http://localhost:8080/api/posts/2/comments/1
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("postId") long postId,@PathVariable("id") long id){
        return new ResponseEntity<>(commentService.getCommentById(postId,id),HttpStatus.OK);
    }

    //http://localhost:8080/api/posts/2/comments/1
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<?> updateComment(@PathVariable("postId") long postId,@PathVariable("id") long id,
                                                    @Valid@RequestBody CommentDto commentDto,BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(commentService.updatepost(postId,id,commentDto),HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("postId") long postId,@PathVariable("id") long id){
        commentService.deleteComment(postId,id);
        return new ResponseEntity<>("Comment is Deleted",HttpStatus.OK);
    }
}
