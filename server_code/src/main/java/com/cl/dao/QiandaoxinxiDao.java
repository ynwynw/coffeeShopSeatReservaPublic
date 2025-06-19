package com.cl.dao;

import com.cl.entity.QiandaoxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.QiandaoxinxiView;


/**
 * 签到信息
 * 
 * @author 
 * @email 
 * @date 2025-04-28 10:49:26
 */
public interface QiandaoxinxiDao extends BaseMapper<QiandaoxinxiEntity> {
	
	List<QiandaoxinxiView> selectListView(@Param("ew") Wrapper<QiandaoxinxiEntity> wrapper);

	List<QiandaoxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<QiandaoxinxiEntity> wrapper);
	
	QiandaoxinxiView selectView(@Param("ew") Wrapper<QiandaoxinxiEntity> wrapper);


}
