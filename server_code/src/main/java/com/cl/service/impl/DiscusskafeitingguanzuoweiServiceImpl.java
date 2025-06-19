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


import com.cl.dao.DiscusskafeitingguanzuoweiDao;
import com.cl.entity.DiscusskafeitingguanzuoweiEntity;
import com.cl.service.DiscusskafeitingguanzuoweiService;
import com.cl.entity.view.DiscusskafeitingguanzuoweiView;

@Service("discusskafeitingguanzuoweiService")
public class DiscusskafeitingguanzuoweiServiceImpl extends ServiceImpl<DiscusskafeitingguanzuoweiDao, DiscusskafeitingguanzuoweiEntity> implements DiscusskafeitingguanzuoweiService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscusskafeitingguanzuoweiEntity> page = this.selectPage(
                new Query<DiscusskafeitingguanzuoweiEntity>(params).getPage(),
                new EntityWrapper<DiscusskafeitingguanzuoweiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscusskafeitingguanzuoweiEntity> wrapper) {
		  Page<DiscusskafeitingguanzuoweiView> page =new Query<DiscusskafeitingguanzuoweiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<DiscusskafeitingguanzuoweiView> selectListView(Wrapper<DiscusskafeitingguanzuoweiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscusskafeitingguanzuoweiView selectView(Wrapper<DiscusskafeitingguanzuoweiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
