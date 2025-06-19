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

import com.cl.entity.YuyuequxiaoEntity;
import com.cl.entity.view.YuyuequxiaoView;

import com.cl.service.YuyuequxiaoService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 预约取消
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-28 10:49:26
 */
@RestController
@RequestMapping("/yuyuequxiao")
public class YuyuequxiaoController {
    @Autowired
    private YuyuequxiaoService yuyuequxiaoService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YuyuequxiaoEntity yuyuequxiao,
                                                                                                                                    @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdateStart,
                    @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdateEnd,
                                                                                                                            HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                                                                                    if(tableName.equals("yonghu")) {
                    yuyuequxiao.setYonghuming((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                                                EntityWrapper<YuyuequxiaoEntity> ew = new EntityWrapper<YuyuequxiaoEntity>();
                                                                                                                                                                                                if(reservationdateStart!=null) ew.ge("reservationdate", reservationdateStart);
                    if(reservationdateEnd!=null) ew.le("reservationdate", reservationdateEnd);
                                                                                                                                                                                        
        
        
        PageUtils page = yuyuequxiaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuyuequxiao), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YuyuequxiaoEntity yuyuequxiao,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdateStart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdateEnd,
		HttpServletRequest request){
        EntityWrapper<YuyuequxiaoEntity> ew = new EntityWrapper<YuyuequxiaoEntity>();
                if(reservationdateStart!=null) ew.ge("reservationdate", reservationdateStart);
                if(reservationdateEnd!=null) ew.le("reservationdate", reservationdateEnd);

		PageUtils page = yuyuequxiaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuyuequxiao), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YuyuequxiaoEntity yuyuequxiao){
       	EntityWrapper<YuyuequxiaoEntity> ew = new EntityWrapper<YuyuequxiaoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yuyuequxiao, "yuyuequxiao")); 
        return R.ok().put("data", yuyuequxiaoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YuyuequxiaoEntity yuyuequxiao){
        EntityWrapper< YuyuequxiaoEntity> ew = new EntityWrapper< YuyuequxiaoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yuyuequxiao, "yuyuequxiao")); 
		YuyuequxiaoView yuyuequxiaoView =  yuyuequxiaoService.selectView(ew);
		return R.ok("查询预约取消成功").put("data", yuyuequxiaoView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YuyuequxiaoEntity yuyuequxiao = yuyuequxiaoService.selectById(id);
		yuyuequxiao = yuyuequxiaoService.selectView(new EntityWrapper<YuyuequxiaoEntity>().eq("id", id));
        return R.ok().put("data", yuyuequxiao);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YuyuequxiaoEntity yuyuequxiao = yuyuequxiaoService.selectById(id);
		yuyuequxiao = yuyuequxiaoService.selectView(new EntityWrapper<YuyuequxiaoEntity>().eq("id", id));
        return R.ok().put("data", yuyuequxiao);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增预约取消")
    public R save(@RequestBody YuyuequxiaoEntity yuyuequxiao, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(yuyuequxiao);
        yuyuequxiaoService.insert(yuyuequxiao);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增预约取消")
    @RequestMapping("/add")
    public R add(@RequestBody YuyuequxiaoEntity yuyuequxiao, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(yuyuequxiao);
        yuyuequxiaoService.insert(yuyuequxiao);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改预约取消")
    public R update(@RequestBody YuyuequxiaoEntity yuyuequxiao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yuyuequxiao);
        yuyuequxiaoService.updateById(yuyuequxiao);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    @SysLog("审核预约取消")
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<YuyuequxiaoEntity> list = new ArrayList<YuyuequxiaoEntity>();
        for(Long id : ids) {
            YuyuequxiaoEntity yuyuequxiao = yuyuequxiaoService.selectById(id);
            yuyuequxiao.setSfsh(sfsh);
            yuyuequxiao.setShhf(shhf);
            list.add(yuyuequxiao);
        }
        yuyuequxiaoService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除预约取消")
    public R delete(@RequestBody Long[] ids){
        yuyuequxiaoService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
