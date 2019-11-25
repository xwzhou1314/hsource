package com.hsource.page.service.Impl;

import com.hsource.page.service.PageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PageServiceImplTest {

    @Autowired
    private PageService pageService;

    @Test
    public void createHtml() {
        pageService.createHtml("123");
    }
}