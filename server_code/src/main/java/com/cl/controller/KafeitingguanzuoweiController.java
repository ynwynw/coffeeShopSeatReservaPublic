package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;
import com.cl.annotation.SysLog;

import com.cl.entity.KafeitingguanzuoweiEntity;
import com.cl.entity.view.KafeitingguanzuoweiView;

import com.cl.service.KafeitingguanzuoweiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 咖啡厅馆座位
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-28 10:49:25
 */
@RestController
@RequestMapping("/kafeitingguanzuowei")
public class KafeitingguanzuoweiController {
    @Autowired
    private KafeitingguanzuoweiService kafeitingguanzuoweiService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KafeitingguanzuoweiEntity kafeitingguanzuowei,
                                                                                                                                                                            HttpServletRequest request){
                                    EntityWrapper<KafeitingguanzuoweiEntity> ew = new EntityWrapper<KafeitingguanzuoweiEntity>();
                                                                                                                                                                                                                                                                        
        
        
        PageUtils page = kafeitingguanzuoweiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kafeitingguanzuowei), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,KafeitingguanzuoweiEntity kafeitingguanzuowei,
		HttpServletRequest request){
        EntityWrapper<KafeitingguanzuoweiEntity> ew = new EntityWrapper<KafeitingguanzuoweiEntity>();

		PageUtils page = kafeitingguanzuoweiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kafeitingguanzuowei), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KafeitingguanzuoweiEntity kafeitingguanzuowei){
       	EntityWrapper<KafeitingguanzuoweiEntity> ew = new EntityWrapper<KafeitingguanzuoweiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kafeitingguanzuowei, "kafeitingguanzuowei")); 
        return R.ok().put("data", kafeitingguanzuoweiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KafeitingguanzuoweiEntity kafeitingguanzuowei){
        EntityWrapper< KafeitingguanzuoweiEntity> ew = new EntityWrapper< KafeitingguanzuoweiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kafeitingguanzuowei, "kafeitingguanzuowei")); 
		KafeitingguanzuoweiView kafeitingguanzuoweiView =  kafeitingguanzuoweiService.selectView(ew);
		return R.ok("查询咖啡厅馆座位成功").put("data", kafeitingguanzuoweiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KafeitingguanzuoweiEntity kafeitingguanzuowei = kafeitingguanzuoweiService.selectById(id);
		kafeitingguanzuowei = kafeitingguanzuoweiService.selectView(new EntityWrapper<KafeitingguanzuoweiEntity>().eq("id", id));
        return R.ok().put("data", kafeitingguanzuowei);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KafeitingguanzuoweiEntity kafeitingguanzuowei = kafeitingguanzuoweiService.selectById(id);
		kafeitingguanzuowei = kafeitingguanzuoweiService.selectView(new EntityWrapper<KafeitingguanzuoweiEntity>().eq("id", id));
        return R.ok().put("data", kafeitingguanzuowei);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增咖啡厅馆座位")
    public R save(@RequestBody KafeitingguanzuoweiEntity kafeitingguanzuowei, HttpServletRequest request){
        if(kafeitingguanzuoweiService.selectCount(new EntityWrapper<KafeitingguanzuoweiEntity>().eq("refno", kafeitingguanzuowei.getRefno()))>0) {
            return R.error("编号已存在");
        }
    	//ValidatorUtils.validateEntity(kafeitingguanzuowei);
        kafeitingguanzuoweiService.insert(kafeitingguanzuowei);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增咖啡厅馆座位")
    @RequestMapping("/add")
    public R add(@RequestBody KafeitingguanzuoweiEntity kafeitingguanzuowei, HttpServletRequest request){
        if(kafeitingguanzuoweiService.selectCount(new EntityWrapper<KafeitingguanzuoweiEntity>().eq("refno", kafeitingguanzuowei.getRefno()))>0) {
            return R.error("编号已存在");
        }
    	//ValidatorUtils.validateEntity(kafeitingguanzuowei);
        kafeitingguanzuoweiService.insert(kafeitingguanzuowei);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改咖啡厅馆座位")
    public R update(@RequestBody KafeitingguanzuoweiEntity kafeitingguanzuowei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kafeitingguanzuowei);
        kafeitingguanzuoweiService.updateById(kafeitingguanzuowei);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除咖啡厅馆座位")
    public R delete(@RequestBody Long[] ids){
        kafeitingguanzuoweiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
