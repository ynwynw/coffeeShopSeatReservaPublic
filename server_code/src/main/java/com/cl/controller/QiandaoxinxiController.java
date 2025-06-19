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

import com.cl.entity.QiandaoxinxiEntity;
import com.cl.entity.view.QiandaoxinxiView;

import com.cl.service.QiandaoxinxiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 签到信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-28 10:49:26
 */
@RestController
@RequestMapping("/qiandaoxinxi")
public class QiandaoxinxiController {
    @Autowired
    private QiandaoxinxiService qiandaoxinxiService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,QiandaoxinxiEntity qiandaoxinxi,
                                                                                                                        @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdateStart,
                    @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdateEnd,
                                                                                                    HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                                                        if(tableName.equals("yonghu")) {
                    qiandaoxinxi.setYonghuming((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                        EntityWrapper<QiandaoxinxiEntity> ew = new EntityWrapper<QiandaoxinxiEntity>();
                                                                                                                                                                            if(reservationdateStart!=null) ew.ge("reservationdate", reservationdateStart);
                    if(reservationdateEnd!=null) ew.le("reservationdate", reservationdateEnd);
                                                                                                                                                
        
        
        PageUtils page = qiandaoxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qiandaoxinxi), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,QiandaoxinxiEntity qiandaoxinxi,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdateStart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdateEnd,
		HttpServletRequest request){
        EntityWrapper<QiandaoxinxiEntity> ew = new EntityWrapper<QiandaoxinxiEntity>();
                if(reservationdateStart!=null) ew.ge("reservationdate", reservationdateStart);
                if(reservationdateEnd!=null) ew.le("reservationdate", reservationdateEnd);

		PageUtils page = qiandaoxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qiandaoxinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( QiandaoxinxiEntity qiandaoxinxi){
       	EntityWrapper<QiandaoxinxiEntity> ew = new EntityWrapper<QiandaoxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( qiandaoxinxi, "qiandaoxinxi")); 
        return R.ok().put("data", qiandaoxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(QiandaoxinxiEntity qiandaoxinxi){
        EntityWrapper< QiandaoxinxiEntity> ew = new EntityWrapper< QiandaoxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( qiandaoxinxi, "qiandaoxinxi")); 
		QiandaoxinxiView qiandaoxinxiView =  qiandaoxinxiService.selectView(ew);
		return R.ok("查询签到信息成功").put("data", qiandaoxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        QiandaoxinxiEntity qiandaoxinxi = qiandaoxinxiService.selectById(id);
		qiandaoxinxi = qiandaoxinxiService.selectView(new EntityWrapper<QiandaoxinxiEntity>().eq("id", id));
        return R.ok().put("data", qiandaoxinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        QiandaoxinxiEntity qiandaoxinxi = qiandaoxinxiService.selectById(id);
		qiandaoxinxi = qiandaoxinxiService.selectView(new EntityWrapper<QiandaoxinxiEntity>().eq("id", id));
        return R.ok().put("data", qiandaoxinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增签到信息")
    public R save(@RequestBody QiandaoxinxiEntity qiandaoxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(qiandaoxinxi);
        qiandaoxinxiService.insert(qiandaoxinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增签到信息")
    @RequestMapping("/add")
    public R add(@RequestBody QiandaoxinxiEntity qiandaoxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(qiandaoxinxi);
        qiandaoxinxiService.insert(qiandaoxinxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改签到信息")
    public R update(@RequestBody QiandaoxinxiEntity qiandaoxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qiandaoxinxi);
        qiandaoxinxiService.updateById(qiandaoxinxi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除签到信息")
    public R delete(@RequestBody Long[] ids){
        qiandaoxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
