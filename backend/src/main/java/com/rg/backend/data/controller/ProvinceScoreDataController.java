package com.rg.backend.data.controller;

import com.alibaba.fastjson.JSON;
import com.rg.backend.data.entity.ProvinceScoreData;
import com.rg.backend.data.service.ProvinceScoreDataService;
import com.rg.backend.user.entity.User;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rg.backend.util.Constant.DEVELOP_ORIGIN;
import static com.rg.backend.util.Constant.PRODUCE_ORIGIN;

@Api(value = "查询数据接口",tags = "查询数据接口说明")
@CrossOrigin(origins={DEVELOP_ORIGIN, PRODUCE_ORIGIN}, allowCredentials="true")
@RestController
public class ProvinceScoreDataController {

    @GetMapping("/query/{province}/{type}")
    public String getData(@PathVariable("province") String province, @PathVariable("type") String type){
        ProvinceScoreDataService provinceScoreDataService = new ProvinceScoreDataService();
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            List<ProvinceScoreData> psdList = provinceScoreDataService.queryByProvinceAndType(province,type);
            if(psdList!=null){
                map.put("status","200");
                map.put("data",psdList);
            }
            else {
                map.put("status","500");
                map.put("errorMsg","Fail");
            }
        }
        catch(Exception ex){
            map.put("status","500");
            map.put("errorMsg","Error:"+ex.getMessage());
        }
        return JSON.toJSONString(map);
    }
}