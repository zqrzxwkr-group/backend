package com.rg.backend.college.controller;


import com.alibaba.fastjson.JSON;
import com.rg.backend.college.entity.College;
import com.rg.backend.college.service.ICollegeService;
import com.rg.backend.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rg.backend.util.Constant.DEVELOP_ORIGIN;
import static com.rg.backend.util.Constant.PRODUCE_ORIGIN;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Byron Ji
 * @since 2021-11-24
 */
@Api(value = "学校信息接口",tags = "学校信息接口说明")
@CrossOrigin(origins={DEVELOP_ORIGIN, PRODUCE_ORIGIN}, allowCredentials="true")
@RestController
@RequestMapping("/college")
public class CollegeController {
    @Autowired
    private ICollegeService collegeService;

    @ApiOperation(value = "查询所有学校")
    @GetMapping("/all")
    public String loginCheck(){
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            List<College> colleges =collegeService.getAllCollege();
            if(colleges!=null){
                map.put("status","200");
                map.put("data",colleges);
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

    @ApiOperation(value = "根据省份查询学校")
    @GetMapping("/{province}")
    public String loginCheck(@PathVariable("province") String city){
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            List<College> colleges =collegeService.getCollegeByCity(city);
            if(colleges!=null){
                map.put("status","200");
                map.put("data",colleges);
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