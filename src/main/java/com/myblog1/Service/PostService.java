package com.myblog1.Service;

import com.myblog1.Payload.PostDto;

import java.util.List;

public interface PostService {
   PostDto createPost(PostDto postDto);

   List<PostDto> listAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(long id, PostDto postDto);

    void deletePost(long id);
}
