package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.DiscusskafeitingguanzuoweiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscusskafeitingguanzuoweiView;


/**
 * 咖啡厅馆座位评论表
 *
 * @author 
 * @email 
 * @date 2025-04-28 10:49:26
 */
public interface DiscusskafeitingguanzuoweiService extends IService<DiscusskafeitingguanzuoweiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscusskafeitingguanzuoweiView> selectListView(Wrapper<DiscusskafeitingguanzuoweiEntity> wrapper);
   	
   	DiscusskafeitingguanzuoweiView selectView(@Param("ew") Wrapper<DiscusskafeitingguanzuoweiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscusskafeitingguanzuoweiEntity> wrapper);
   	
   
}

