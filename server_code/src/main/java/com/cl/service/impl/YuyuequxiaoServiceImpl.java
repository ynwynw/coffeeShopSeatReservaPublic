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


import com.cl.dao.YuyuequxiaoDao;
import com.cl.entity.YuyuequxiaoEntity;
import com.cl.service.YuyuequxiaoService;
import com.cl.entity.view.YuyuequxiaoView;

@Service("yuyuequxiaoService")
public class YuyuequxiaoServiceImpl extends ServiceImpl<YuyuequxiaoDao, YuyuequxiaoEntity> implements YuyuequxiaoService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YuyuequxiaoEntity> page = this.selectPage(
                new Query<YuyuequxiaoEntity>(params).getPage(),
                new EntityWrapper<YuyuequxiaoEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YuyuequxiaoEntity> wrapper) {
		  Page<YuyuequxiaoView> page =new Query<YuyuequxiaoView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<YuyuequxiaoView> selectListView(Wrapper<YuyuequxiaoEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YuyuequxiaoView selectView(Wrapper<YuyuequxiaoEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
