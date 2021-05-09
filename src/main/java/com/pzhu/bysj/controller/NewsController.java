package com.pzhu.bysj.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pzhu.bysj.common.ResultCommon;
import com.pzhu.bysj.common.ResultEnum;
import com.pzhu.bysj.pojo.Admin;
import com.pzhu.bysj.pojo.News;
import com.pzhu.bysj.service.NewsService;
import com.pzhu.bysj.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class NewsController {
    @Autowired
    NewsService newsService;

    @GetMapping("/news_list")
        public PageUtils news_list(@RequestParam(defaultValue = "1") Long pageIndex, @RequestParam(defaultValue = "8") Long pageSize) {
        long totalCount = newsService.totalCount();
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageStart", (pageIndex - 1) * pageSize);
        map.put("pageSize", pageSize);

        List<News> news = newsService.findNews(map);
        for (News news1 : news) {
            news1.setCreatetime((news1.getCreatetime().split("\\."))[0]);
            news1.setUpdatetime((news1.getUpdatetime().split("\\."))[0]);
        }
        //Long pageIndex, Long pageSize, Long totalCount, Object datas
        PageUtils pageUtils = new PageUtils(pageIndex, pageSize, totalCount, news);
        return pageUtils;
    }

    @GetMapping("/news_delete")
    public ResultCommon news_delete(Integer id) {
        ResultCommon result = null;
        try {
            int count = newsService.deleteNew(id);
            if (count > 0) {
                result = ResultCommon.success(ResultEnum.SUCCESS);
            }
        } catch (Exception e) {
            result = ResultCommon.fail(ResultEnum.FAIL);
        }
        return result;
    }

    @PostMapping("/news_deletes")
    public ResultCommon news_deletes(String ids) {
        String[] split = ids.split(",");
        ResultCommon result = null;
        int count = newsService.deleteNews(split);
        if (count > 0) {
            result = ResultCommon.success(ResultEnum.SUCCESS);
        } else {
            result = ResultCommon.fail(ResultEnum.FAIL);
        }
        return result;
    }

    @GetMapping("/news_findById")
    public ResultCommon news_findById(Integer id) {
        ResultCommon result = null;
        try {
            News news = newsService.NewsFindById2(id);
            result = ResultCommon.success(ResultEnum.SUCCESS, news);
        } catch (Exception e) {
            result = ResultCommon.fail(ResultEnum.FAIL);
        }
        return result;
    }

    @PostMapping("/news_add")
    public ResultCommon news_add(News news, HttpSession session) {
        ResultCommon resultCommon = null;
        Admin admin = (Admin) session.getAttribute("admin");
        try {
            news.setAdmin(admin);
            int count = newsService.news_add(news);
            if (count > 0) {
                resultCommon = ResultCommon.success(ResultEnum.SUCCESS);
            }
        } catch (Exception e) {
            resultCommon = ResultCommon.fail(ResultEnum.FAIL);
        }
        return resultCommon;
    }

    @PostMapping("/news_update")
    public ResultCommon news_update(News news) {
        ResultCommon resultCommon = null;
        try {
            int count = newsService.update(news);
            if (count > 0) {
                resultCommon = ResultCommon.success(ResultEnum.SUCCESS);
            }
        } catch (Exception e) {
            resultCommon = ResultCommon.fail(ResultEnum.FAIL);
        }
        return resultCommon;
    }

    @GetMapping("/index_findsLatestNews")
    public List<News> index_findsLatestNews() {
        List<News> news = null;
        try {
            news = newsService.findsLatestNews();
        } catch (Exception e) {
            news = null;
        }
        return news;
    }

    @PostMapping("/add_hit")
    public ResultCommon add_hit(Integer id){
        ResultCommon resultCommon = null;
        try {
            int count = newsService.add_hit(id);
            if (count>0){
                resultCommon = ResultCommon.success(ResultEnum.SUCCESS);
            }
        }catch (Exception e){
            resultCommon = ResultCommon.fail(ResultEnum.FAIL);
        }
        return resultCommon;
    }
}
