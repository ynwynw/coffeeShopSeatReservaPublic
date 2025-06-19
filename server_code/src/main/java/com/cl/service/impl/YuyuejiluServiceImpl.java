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


import com.cl.dao.YuyuejiluDao;
import com.cl.entity.YuyuejiluEntity;
import com.cl.service.YuyuejiluService;
import com.cl.entity.view.YuyuejiluView;

@Service("yuyuejiluService")
public class YuyuejiluServiceImpl extends ServiceImpl<YuyuejiluDao, YuyuejiluEntity> implements YuyuejiluService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YuyuejiluEntity> page = this.selectPage(
                new Query<YuyuejiluEntity>(params).getPage(),
                new EntityWrapper<YuyuejiluEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YuyuejiluEntity> wrapper) {
		  Page<YuyuejiluView> page =new Query<YuyuejiluView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<YuyuejiluView> selectListView(Wrapper<YuyuejiluEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YuyuejiluView selectView(Wrapper<YuyuejiluEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<YuyuejiluEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<YuyuejiluEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<YuyuejiluEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
