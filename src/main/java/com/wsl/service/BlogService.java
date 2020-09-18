package com.wsl.service;

import com.wsl.po.Blog;
import com.wsl.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Long countBlog();
    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(Pageable pageable);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);

    List<Blog> listRecommendBlogTop(Integer size);

    Map<String,List<Blog>> archiveBlog();

    Page<Blog> listBlog(String query,Pageable pageable);

    Page<Blog> listBlog(Pageable pageable,Long tagId);

    Blog getAndConvert(Long id);
}
