package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.KafeitingguanzuoweiDao;
import com.cl.entity.KafeitingguanzuoweiEntity;
import com.cl.service.KafeitingguanzuoweiService;
import com.cl.entity.view.KafeitingguanzuoweiView;

@Service("kafeitingguanzuoweiService")
public class KafeitingguanzuoweiServiceImpl extends ServiceImpl<KafeitingguanzuoweiDao, KafeitingguanzuoweiEntity> implements KafeitingguanzuoweiService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KafeitingguanzuoweiEntity> page = this.selectPage(
                new Query<KafeitingguanzuoweiEntity>(params).getPage(),
                new EntityWrapper<KafeitingguanzuoweiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<KafeitingguanzuoweiEntity> wrapper) {
		  Page<KafeitingguanzuoweiView> page =new Query<KafeitingguanzuoweiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<KafeitingguanzuoweiView> selectListView(Wrapper<KafeitingguanzuoweiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public KafeitingguanzuoweiView selectView(Wrapper<KafeitingguanzuoweiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
