package com.pzhu.bysj.dao;

import org.mapstruct.Mapper;

@Mapper
public interface BuildingDao {
    Integer findAdminId(Integer buildId);

    String findBuildName(String buildid);
}
