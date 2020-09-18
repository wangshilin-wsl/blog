package com.wsl.web;

import com.wsl.service.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArchiveShowController {
    @Autowired
    private BlogServiceImpl blogService;
    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archiveMap",blogService.archiveBlog());
        System.out.println(blogService.archiveBlog().keySet());
        model.addAttribute("BlogCount",blogService.countBlog());
        return "archives";
    }
}
