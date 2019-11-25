package com.hsource.page.service.Impl;

import com.hsource.page.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class PageServiceImpl implements PageService{

    @Autowired
    private TemplateEngine templateEngine;


    @Override
    public void createHtml(String id){

        // 上下文
        Context context = new Context();
        context.setVariables(getMap());

        // 输出流
        File file = new File("F:\\upload", id + ".html");


        try {
            PrintWriter write = new PrintWriter(file, "UTF-8");
            templateEngine.process("item", context, write);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("123",132);
        return map;
    }
}
