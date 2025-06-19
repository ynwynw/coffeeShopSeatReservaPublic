package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.YuyuejiluEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YuyuejiluView;


/**
 * 预约记录
 *
 * @author 
 * @email 
 * @date 2025-04-28 10:49:26
 */
public interface YuyuejiluService extends IService<YuyuejiluEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YuyuejiluView> selectListView(Wrapper<YuyuejiluEntity> wrapper);
   	
   	YuyuejiluView selectView(@Param("ew") Wrapper<YuyuejiluEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YuyuejiluEntity> wrapper);
   	
   
    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<YuyuejiluEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<YuyuejiluEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<YuyuejiluEntity> wrapper);



}

