package com.cl.dao;

import com.cl.entity.YuyuejiluEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YuyuejiluView;


/**
 * 预约记录
 * 
 * @author 
 * @email 
 * @date 2025-04-28 10:49:26
 */
public interface YuyuejiluDao extends BaseMapper<YuyuejiluEntity> {
	
	List<YuyuejiluView> selectListView(@Param("ew") Wrapper<YuyuejiluEntity> wrapper);

	List<YuyuejiluView> selectListView(Pagination page,@Param("ew") Wrapper<YuyuejiluEntity> wrapper);
	
	YuyuejiluView selectView(@Param("ew") Wrapper<YuyuejiluEntity> wrapper);


    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YuyuejiluEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YuyuejiluEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YuyuejiluEntity> wrapper);



}
