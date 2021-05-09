package com.pzhu.bysj.service;

import com.pzhu.bysj.pojo.News;

import java.util.HashMap;
import java.util.List;

public interface NewsService {
    long totalCount();

    List<News> findNews(HashMap<String, Object> map);

    int deleteNew(Integer id);

    int deleteNews(String[] split);

    News NewsFindById(Integer id);

    int news_add(News news);

    int update(News news);

    List<News> findsLatestNews();

    News NewsFindById2(Integer id);

    int add_hit(Integer id);
}
