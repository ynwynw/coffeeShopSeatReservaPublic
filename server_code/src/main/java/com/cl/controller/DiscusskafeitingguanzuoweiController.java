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

import com.cl.entity.DiscusskafeitingguanzuoweiEntity;
import com.cl.entity.view.DiscusskafeitingguanzuoweiView;

import com.cl.service.DiscusskafeitingguanzuoweiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 咖啡厅馆座位评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-28 10:49:26
 */
@RestController
@RequestMapping("/discusskafeitingguanzuowei")
public class DiscusskafeitingguanzuoweiController {
    @Autowired
    private DiscusskafeitingguanzuoweiService discusskafeitingguanzuoweiService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscusskafeitingguanzuoweiEntity discusskafeitingguanzuowei,
                                                                                                                            HttpServletRequest request){
                                    EntityWrapper<DiscusskafeitingguanzuoweiEntity> ew = new EntityWrapper<DiscusskafeitingguanzuoweiEntity>();
                                                                                                                                                                                        
        
        
        PageUtils page = discusskafeitingguanzuoweiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusskafeitingguanzuowei), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscusskafeitingguanzuoweiEntity discusskafeitingguanzuowei,
		HttpServletRequest request){
        EntityWrapper<DiscusskafeitingguanzuoweiEntity> ew = new EntityWrapper<DiscusskafeitingguanzuoweiEntity>();

		PageUtils page = discusskafeitingguanzuoweiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusskafeitingguanzuowei), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscusskafeitingguanzuoweiEntity discusskafeitingguanzuowei){
       	EntityWrapper<DiscusskafeitingguanzuoweiEntity> ew = new EntityWrapper<DiscusskafeitingguanzuoweiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discusskafeitingguanzuowei, "discusskafeitingguanzuowei")); 
        return R.ok().put("data", discusskafeitingguanzuoweiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscusskafeitingguanzuoweiEntity discusskafeitingguanzuowei){
        EntityWrapper< DiscusskafeitingguanzuoweiEntity> ew = new EntityWrapper< DiscusskafeitingguanzuoweiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discusskafeitingguanzuowei, "discusskafeitingguanzuowei")); 
		DiscusskafeitingguanzuoweiView discusskafeitingguanzuoweiView =  discusskafeitingguanzuoweiService.selectView(ew);
		return R.ok("查询咖啡厅馆座位评论表成功").put("data", discusskafeitingguanzuoweiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscusskafeitingguanzuoweiEntity discusskafeitingguanzuowei = discusskafeitingguanzuoweiService.selectById(id);
		discusskafeitingguanzuowei = discusskafeitingguanzuoweiService.selectView(new EntityWrapper<DiscusskafeitingguanzuoweiEntity>().eq("id", id));
        return R.ok().put("data", discusskafeitingguanzuowei);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscusskafeitingguanzuoweiEntity discusskafeitingguanzuowei = discusskafeitingguanzuoweiService.selectById(id);
		discusskafeitingguanzuowei = discusskafeitingguanzuoweiService.selectView(new EntityWrapper<DiscusskafeitingguanzuoweiEntity>().eq("id", id));
        return R.ok().put("data", discusskafeitingguanzuowei);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增咖啡厅馆座位评论表")
    public R save(@RequestBody DiscusskafeitingguanzuoweiEntity discusskafeitingguanzuowei, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discusskafeitingguanzuowei);
        discusskafeitingguanzuoweiService.insert(discusskafeitingguanzuowei);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增咖啡厅馆座位评论表")
    @RequestMapping("/add")
    public R add(@RequestBody DiscusskafeitingguanzuoweiEntity discusskafeitingguanzuowei, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discusskafeitingguanzuowei);
        discusskafeitingguanzuoweiService.insert(discusskafeitingguanzuowei);
        return R.ok();
    }

     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscusskafeitingguanzuoweiEntity discusskafeitingguanzuowei = discusskafeitingguanzuoweiService.selectOne(new EntityWrapper<DiscusskafeitingguanzuoweiEntity>().eq("", username));
        return R.ok().put("data", discusskafeitingguanzuowei);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscusskafeitingguanzuoweiEntity discusskafeitingguanzuowei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discusskafeitingguanzuowei);
        discusskafeitingguanzuoweiService.updateById(discusskafeitingguanzuowei);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除咖啡厅馆座位评论表")
    public R delete(@RequestBody Long[] ids){
        discusskafeitingguanzuoweiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscusskafeitingguanzuoweiEntity discusskafeitingguanzuowei, HttpServletRequest request,String pre){
        EntityWrapper<DiscusskafeitingguanzuoweiEntity> ew = new EntityWrapper<DiscusskafeitingguanzuoweiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = discusskafeitingguanzuoweiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusskafeitingguanzuowei), params), params));
        return R.ok().put("data", page);
    }









}
