package com.wsl.web;

import com.wsl.po.Tag;
import com.wsl.service.BlogServiceImpl;
import com.wsl.service.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagShowController {
    @Autowired
    private BlogServiceImpl blogService;
    @Autowired
    private TagServiceImpl tagService;
    @GetMapping("/tags/{id}")
    public String tags(@PathVariable Long id, @PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC)
            Pageable pageable, Model model){
        List<Tag> tags = tagService.listTagTop(10000);
        if (id == -1) {
            id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        model.addAttribute("page", blogService.listBlog(pageable,id));
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
