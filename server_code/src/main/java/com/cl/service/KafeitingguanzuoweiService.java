package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.KafeitingguanzuoweiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.KafeitingguanzuoweiView;


/**
 * 咖啡厅馆座位
 *
 * @author 
 * @email 
 * @date 2025-04-28 10:49:25
 */
public interface KafeitingguanzuoweiService extends IService<KafeitingguanzuoweiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KafeitingguanzuoweiView> selectListView(Wrapper<KafeitingguanzuoweiEntity> wrapper);
   	
   	KafeitingguanzuoweiView selectView(@Param("ew") Wrapper<KafeitingguanzuoweiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<KafeitingguanzuoweiEntity> wrapper);
   	
   
}

