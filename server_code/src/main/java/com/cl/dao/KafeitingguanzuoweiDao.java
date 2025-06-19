package com.cl.dao;

import com.cl.entity.KafeitingguanzuoweiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.KafeitingguanzuoweiView;


/**
 * 咖啡厅馆座位
 * 
 * @author 
 * @email 
 * @date 2025-04-28 10:49:25
 */
public interface KafeitingguanzuoweiDao extends BaseMapper<KafeitingguanzuoweiEntity> {
	
	List<KafeitingguanzuoweiView> selectListView(@Param("ew") Wrapper<KafeitingguanzuoweiEntity> wrapper);

	List<KafeitingguanzuoweiView> selectListView(Pagination page,@Param("ew") Wrapper<KafeitingguanzuoweiEntity> wrapper);
	
	KafeitingguanzuoweiView selectView(@Param("ew") Wrapper<KafeitingguanzuoweiEntity> wrapper);


}
