package com.pzhu.bysj.service.impl;

import com.pzhu.bysj.dao.NewsDao;
import com.pzhu.bysj.pojo.News;
import com.pzhu.bysj.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsDao newsDao;

    @Override
    public long totalCount() {
        return newsDao.totalCount();
    }

    @Override
    public List<News> findNews(HashMap<String, Object> map) {
        return newsDao.findNews(map);
    }

    @Override
    public int deleteNew(Integer id) {
        return newsDao.deleteNew(id);
    }

    @Override
    public int deleteNews(String[] split) {
        return newsDao.deleteNews(split);
    }

    @Override
    public News NewsFindById(Integer id) {
        return newsDao.NewsFindById(id);
    }

    @Override
    public int news_add(News news) {
        return newsDao.news_add(news);
    }

    @Override
    public int update(News news) {
        return newsDao.updateNews(news);
    }

    @Override
    public List<News> findsLatestNews() {
        return newsDao.findsLatestNews();
    }

    @Override
    public News NewsFindById2(Integer id) {
        return newsDao.NewsFindById2(id);
    }

    @Override
    public int add_hit(Integer id) {
        return newsDao.add_hit(id);
    }
}
