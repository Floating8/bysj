package com.pzhu.bysj.dao;

import com.pzhu.bysj.pojo.News;
import org.mapstruct.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface NewsDao {
    long totalCount();

    List<News> findNews(HashMap<String, Object> map);

    int deleteNew(Integer id);

    int deleteNews(String[] split);

    News NewsFindById(Integer id);

    int news_add(News news);

    int updateNews(News news);

    List<News> findsLatestNews();

    News NewsFindById2(Integer id);

    int add_hit(Integer id);
}
