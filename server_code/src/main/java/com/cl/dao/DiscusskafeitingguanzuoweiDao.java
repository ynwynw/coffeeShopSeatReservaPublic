package com.cl.dao;

import com.cl.entity.DiscusskafeitingguanzuoweiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscusskafeitingguanzuoweiView;


/**
 * 咖啡厅馆座位评论表
 * 
 * @author 
 * @email 
 * @date 2025-04-28 10:49:26
 */
public interface DiscusskafeitingguanzuoweiDao extends BaseMapper<DiscusskafeitingguanzuoweiEntity> {
	
	List<DiscusskafeitingguanzuoweiView> selectListView(@Param("ew") Wrapper<DiscusskafeitingguanzuoweiEntity> wrapper);

	List<DiscusskafeitingguanzuoweiView> selectListView(Pagination page,@Param("ew") Wrapper<DiscusskafeitingguanzuoweiEntity> wrapper);
	
	DiscusskafeitingguanzuoweiView selectView(@Param("ew") Wrapper<DiscusskafeitingguanzuoweiEntity> wrapper);


}
