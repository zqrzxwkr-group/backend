package com.rg.backend.data.dao;

import com.rg.backend.data.entity.ProvinceScoreData;
import com.rg.backend.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProvinceScoreDataDao {
    //传省份和文理科查询
    public Map<String, Object> queryByProvinceAndType(String province, String type, int fromPage) throws SQLException {
        int fromIndex = (fromPage-1)*20;
        int pageLen = 20;
        Connection conn = DBUtil.getConnection();
        String table = province + "_" + type;
        String sql = "select * from " + table + " order by `2020均分` desc limit " + fromIndex + "," + pageLen;
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        List<ProvinceScoreData> provinceScoreDataList = new ArrayList<>();
        ProvinceScoreData provinceScoreData = null;
        while (rs.next()){
            provinceScoreData = new ProvinceScoreData();
            provinceScoreData.setProvince(rs.getString("省份"));
            provinceScoreData.setCity(rs.getString("城市"));
            provinceScoreData.setF985(rs.getString("F985"));
            provinceScoreData.setF211(rs.getString("F211"));
            provinceScoreData.setDoubleFirstClass(rs.getString("双一流"));
            provinceScoreData.setSchool(rs.getString("学校"));
            provinceScoreData.setAvg2016(rs.getInt("2016均分"));
            provinceScoreData.setRank2016(rs.getInt("2016排名"));
            provinceScoreData.setAvg2017(rs.getInt("2017均分"));
            provinceScoreData.setRank2017(rs.getInt("2017排名"));
            provinceScoreData.setAvg2018(rs.getInt("2018均分"));
            provinceScoreData.setRank2018(rs.getInt("2018排名"));
            provinceScoreData.setAvg2019(rs.getInt("2019均分"));
            provinceScoreData.setRank2019(rs.getInt("2019排名"));
            provinceScoreData.setAvg2020(rs.getInt("2020均分"));
            provinceScoreData.setRank2020(rs.getInt("2020排名"));
            provinceScoreDataList.add(provinceScoreData);
        }
        sql = "select count(*) as total from " + table;
        ptmt = conn.prepareStatement(sql);
        rs = ptmt.executeQuery();
        int total = 0;
        while (rs.next()){
            total = rs.getInt("total");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("provinceScoreDataList",provinceScoreDataList);
        conn.close();
        return map;
    }


}
