package com.wsl.web;

import com.wsl.service.BlogServiceImpl;
import com.wsl.service.TagServiceImpl;
import com.wsl.service.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired
    private BlogServiceImpl blogService;
    @Autowired
    private TypeServiceImpl typeService;
    @Autowired
    private TagServiceImpl tagService;
    @GetMapping("/")
    public String index(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                    Pageable pageable,  Model model){
      model.addAttribute("page",blogService.listBlog(pageable));
      model.addAttribute("types",typeService.listTypeTop(6));
      model.addAttribute("tags",tagService.listTagTop(10));
      model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));
        return "index";
    }
    @PostMapping("/search")
    public String search(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                     Pageable pageable, Model model, @RequestParam String query){
        model.addAttribute("page",blogService.listBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);

        return "search";
    }
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        model.addAttribute("blog",blogService.getAndConvert(id));
        System.out.println(blogService.getAndConvert(id).getContent());
        return "blog";
    }
    @GetMapping("/footer/newblog")
    public String newblogs(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(3));
        return "_fragment::newblogList";
    }
}
