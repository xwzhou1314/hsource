package com.hsource.page.controller;

import com.hsource.page.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
public class PageController {

    @Autowired
    private PageService pageService;

    @GetMapping("item/{id}.html")
    public String toItemPage(@PathVariable("id") String id, Model model){

        Map<String, Object> map = pageService.getMap();
        model.addAttribute("", map);

        // 返回视图
        return "item";
    }

}
