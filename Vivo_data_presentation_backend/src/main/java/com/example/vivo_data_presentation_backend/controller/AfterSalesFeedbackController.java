package com.example.vivo_data_presentation_backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.vivo_data_presentation_backend.common.R;
import com.example.vivo_data_presentation_backend.domain.AfterSalesFeedback;
import com.example.vivo_data_presentation_backend.domain.PublicOpinionFeedback;
import com.example.vivo_data_presentation_backend.mapper.AfterSalesFeedbackMapper;
import com.example.vivo_data_presentation_backend.service.AfterSalesFeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *反馈意见接口
 *
 * */
@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/aftersalesfeedbacks")
public class AfterSalesFeedbackController {

    @Autowired
    private AfterSalesFeedbackService aftersalesfeedbackService;

    @Autowired
    private AfterSalesFeedbackMapper afterSalesFeedbackMapper;

    /*
     *查询所有手机型号的舆情反馈数据
     *
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<AfterSalesFeedback> getAll(){
        log.info("查询");
        List<AfterSalesFeedback> publicOpinionFeedbackList = aftersalesfeedbackService.list();
        return publicOpinionFeedbackList;
    }

    /*
     *查询相应手机型号的舆情反馈数据
     *
     */
    @RequestMapping(value = "/{machineType}",method = RequestMethod.GET)
    @ResponseBody
    public List<AfterSalesFeedback> get(@PathVariable String machineType){
        log.info("查询");
        LambdaQueryWrapper<AfterSalesFeedback> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(AfterSalesFeedback::getMachineType,machineType);
        List<AfterSalesFeedback> publicopinionfeedbacks = aftersalesfeedbackService.list(queryWrapper);
        return publicopinionfeedbacks;
    }

    /*
     *修改相应手机型号的舆情反馈数据
     *
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public AfterSalesFeedback putOne(@RequestBody AfterSalesFeedback publicopinionfeedback){
        log.info("修改单个数据");
        AfterSalesFeedback publicOpinionFeedback = new AfterSalesFeedback();
        LambdaUpdateWrapper<AfterSalesFeedback> lambdaUpdateWrapper = new LambdaUpdateWrapper<>(publicOpinionFeedback);
        aftersalesfeedbackService.update(publicopinionfeedback,lambdaUpdateWrapper);
        return publicopinionfeedback;
    }


    /*
     *删除相应手机型号的舆情反馈数据
     *
     */
    @RequestMapping(value = "/{machineType}",method = RequestMethod.DELETE)
    @ResponseBody
    public String DeleteOne(@PathVariable String machineType){
        log.info("删除单个数据");
        LambdaQueryWrapper<AfterSalesFeedback> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.in(AfterSalesFeedback::getMachineType,machineType);
        aftersalesfeedbackService.remove(queryWrapper);
        return "删除成功";
    }


    /*
     *查询相应手机型号的舆情反馈数据的数量--低端
     * 0.2版本新增
     */
    @RequestMapping(value = "/low/length/Type",method = RequestMethod.GET)
    @ResponseBody
    public R getAllLowLength(){
        log.info("查询手机型号数量");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("DISTINCT machine_type");
        queryWrapper.like("series","Low");
        List<AfterSalesFeedback> machineTypes = afterSalesFeedbackMapper.selectList(queryWrapper);
        Map<String,Long> Result = new HashMap<>();
        for(int i=0;i<machineTypes.size();i++)
        {
            LambdaQueryWrapper<AfterSalesFeedback> queryWrapperall = new LambdaQueryWrapper();
            queryWrapperall.like(AfterSalesFeedback::getMachineType,machineTypes.get(i).getMachineType());
            long length = afterSalesFeedbackMapper.selectCount(queryWrapperall);
            Result.put(machineTypes.get(i).getMachineType(),length);
        }
        return R.success(Result);
    }

    /*
     *查询相应类别的售后反馈数据的数量--低端
     * 0.2版本新增
     */
    @RequestMapping(value = "/low/length/category",method = RequestMethod.GET)
    @ResponseBody
    public R getLowLength(){
        log.info("查询手机型号数量");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("DISTINCT category");
        queryWrapper.like("series","Low");
        List<AfterSalesFeedback> machineTypes = afterSalesFeedbackMapper.selectList(queryWrapper);
        Map<String,Long> Result = new HashMap<>();
        for(int i=0;i<machineTypes.size();i++)
        {
            LambdaQueryWrapper<AfterSalesFeedback> queryWrapperall = new LambdaQueryWrapper();
            queryWrapperall.like(AfterSalesFeedback::getCategory,machineTypes.get(i).getCategory());
            long length = afterSalesFeedbackMapper.selectCount(queryWrapperall);
            Result.put(machineTypes.get(i).getCategory(),length);
        }
        return R.success(Result);
    }


    /*
     *查询相应手机型号的舆情反馈数据的数量--中端
     * 0.2版本新增
     */
    @RequestMapping(value = "/mid/length/Type",method = RequestMethod.GET)
    @ResponseBody
    public R getAllMidLength(){
        log.info("查询手机型号数量");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("DISTINCT machine_type");
        queryWrapper.like("series","Mid");
        List<AfterSalesFeedback> machineTypes = afterSalesFeedbackMapper.selectList(queryWrapper);
        Map<String,Long> Result = new HashMap<>();
        for(int i=0;i<machineTypes.size();i++)
        {
            LambdaQueryWrapper<AfterSalesFeedback> queryWrapperall = new LambdaQueryWrapper();
            queryWrapperall.like(AfterSalesFeedback::getMachineType,machineTypes.get(i).getMachineType());
            long length = afterSalesFeedbackMapper.selectCount(queryWrapperall);
            Result.put(machineTypes.get(i).getMachineType(),length);
        }
        return R.success(Result);
    }

    /*
     *查询相应类型的售后反馈数据的数量--中端
     * 0.2版本新增
     */
    @RequestMapping(value = "/mid/length/category",method = RequestMethod.GET)
    @ResponseBody
    public R getMidLength(){
        log.info("查询手机型号数量");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("DISTINCT category");
        queryWrapper.like("series","Mid");
        List<AfterSalesFeedback> machineTypes = afterSalesFeedbackMapper.selectList(queryWrapper);
        Map<String,Long> Result = new HashMap<>();
        for(int i=0;i<machineTypes.size();i++)
        {
            LambdaQueryWrapper<AfterSalesFeedback> queryWrapperall = new LambdaQueryWrapper();
            queryWrapperall.like(AfterSalesFeedback::getCategory,machineTypes.get(i).getCategory());
            long length = afterSalesFeedbackMapper.selectCount(queryWrapperall);
            Result.put(machineTypes.get(i).getCategory(),length);
        }
        return R.success(Result);
    }

    /*
     *查询相应手机型号的舆情反馈数据的数量--高端
     * 0.2版本新增
     */
    @RequestMapping(value = "/high/length/Type",method = RequestMethod.GET)
    @ResponseBody
    public R getAllHighLength(){
        log.info("查询手机型号数量");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("DISTINCT machine_type");
        queryWrapper.like("series","High");
        List<AfterSalesFeedback> machineTypes = afterSalesFeedbackMapper.selectList(queryWrapper);
        Map<String,Long> Result = new HashMap<>();
        for(int i=0;i<machineTypes.size();i++)
        {
            LambdaQueryWrapper<AfterSalesFeedback> queryWrapperall = new LambdaQueryWrapper();
            queryWrapperall.like(AfterSalesFeedback::getMachineType,machineTypes.get(i).getMachineType());
            long length = afterSalesFeedbackMapper.selectCount(queryWrapperall);
            Result.put(machineTypes.get(i).getMachineType(),length);
        }
        return R.success(Result);
    }

    /*
     *查询相应类别的售后反馈数据的数量--高端
     * 0.2版本新增
     */
    @RequestMapping(value = "/high/length/category",method = RequestMethod.GET)
    @ResponseBody
    public R getHighLength(){
        log.info("查询手机型号数量");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("DISTINCT category");
        queryWrapper.like("series","High");
        List<AfterSalesFeedback> machineTypes = afterSalesFeedbackMapper.selectList(queryWrapper);
        Map<String,Long> Result = new HashMap<>();
        for(int i=0;i<machineTypes.size();i++)
        {
            LambdaQueryWrapper<AfterSalesFeedback> queryWrapperall = new LambdaQueryWrapper();
            queryWrapperall.like(AfterSalesFeedback::getCategory,machineTypes.get(i).getCategory());
            long length = afterSalesFeedbackMapper.selectCount(queryWrapperall);
            Result.put(machineTypes.get(i).getCategory(),length);
        }
        return R.success(Result);
    }

    /*
     * 低端-low产品相关*/
    /*
     *新增舆情反馈数据
     *
     */
    @RequestMapping(value = "/low",method = RequestMethod.POST)
    @ResponseBody
    public String add_low(@RequestBody AfterSalesFeedback publicopinionfeedback){
        log.info("新增单条数据");
        publicopinionfeedback.setSeries("Low");
        aftersalesfeedbackService.save(publicopinionfeedback);
        return "成功";
    }

    /*
     *新增多条舆情反馈数据
     *
     */
    @RequestMapping(value = "/low/batch",method = RequestMethod.POST)
    @ResponseBody
    public String adds_low(@RequestBody List<AfterSalesFeedback> publicopinionfeedbacks){
        log.info("新增多条数据");
        for(int i=0;i<publicopinionfeedbacks.size();i++)
            publicopinionfeedbacks.get(i).setSeries("Low");
        Instant start = Instant.now();
        aftersalesfeedbackService.saveBatch(publicopinionfeedbacks,1000);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Time elapsed: " + duration.toMillis() + " milliseconds");
        return "成功";
    }

    /*
     *获取舆情反馈所有数据
     *
     */
    @RequestMapping(value = "/low",method = RequestMethod.GET)
    @ResponseBody
    public List<AfterSalesFeedback> getAll_low(){
        log.info("查询");
        LambdaQueryWrapper<AfterSalesFeedback> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(AfterSalesFeedback::getSeries,"Low");
        List<AfterSalesFeedback> publicOpinionFeedbackList = aftersalesfeedbackService.list(queryWrapper);
        return publicOpinionFeedbackList;
    }

    /*
     * 中端端-high产品相关*/
    /*
     *新增舆情反馈数据
     *
     */
    @RequestMapping(value = "/mid",method = RequestMethod.POST)
    @ResponseBody
    public String add_mid(@RequestBody AfterSalesFeedback publicopinionfeedback){
        log.info("新增单条数据");
        publicopinionfeedback.setSeries("Mid");
        aftersalesfeedbackService.save(publicopinionfeedback);
        return "成功";
    }

    /*
     *新增多条舆情反馈数据
     *
     */
    @RequestMapping(value = "/mid/batch",method = RequestMethod.POST)
    @ResponseBody
    public String adds_mid(@RequestBody List<AfterSalesFeedback> publicopinionfeedbacks){
        log.info("新增多条数据");
        for(int i=0;i<publicopinionfeedbacks.size();i++)
            publicopinionfeedbacks.get(i).setSeries("Mid");
        Instant start = Instant.now();
        aftersalesfeedbackService.saveBatch(publicopinionfeedbacks,1000);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Time elapsed: " + duration.toMillis() + " milliseconds");
        return "成功";
    }

    /*
     *获取舆情反馈所有数据
     *
     */
    @RequestMapping(value = "/mid",method = RequestMethod.GET)
    @ResponseBody
    public List<AfterSalesFeedback> getAll_mid(){
        log.info("查询");
        LambdaQueryWrapper<AfterSalesFeedback> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(AfterSalesFeedback::getSeries,"Mid");
        List<AfterSalesFeedback> publicOpinionFeedbackList = aftersalesfeedbackService.list(queryWrapper);
        return publicOpinionFeedbackList;
    }


    /*
     * 高端-high产品相关*/
    /*
     *新增舆情反馈数据
     *
     */
    @RequestMapping(value = "/high",method = RequestMethod.POST)
    @ResponseBody
    public String add_high(@RequestBody AfterSalesFeedback publicopinionfeedback){
        log.info("新增单条数据");
        publicopinionfeedback.setSeries("High");
        aftersalesfeedbackService.save(publicopinionfeedback);
        return "成功";
    }

    /*
     *新增多条舆情反馈数据
     *
     */
    @RequestMapping(value = "/high/batch",method = RequestMethod.POST)
    @ResponseBody
    public String adds_high(@RequestBody List<AfterSalesFeedback> publicopinionfeedbacks){
        log.info("新增多条数据");
        for(int i=0;i<publicopinionfeedbacks.size();i++)
            publicopinionfeedbacks.get(i).setSeries("High");
        Instant start = Instant.now();
        aftersalesfeedbackService.saveBatch(publicopinionfeedbacks,1000);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Time elapsed: " + duration.toMillis() + " milliseconds");
        return "成功";
    }

    /*
     *获取舆情反馈所有数据
     *
     */
    @RequestMapping(value = "/high",method = RequestMethod.GET)
    @ResponseBody
    public List<AfterSalesFeedback> getAll_high(){
        log.info("查询");
        LambdaQueryWrapper<AfterSalesFeedback> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(AfterSalesFeedback::getSeries,"High");
        List<AfterSalesFeedback> publicOpinionFeedbackList = aftersalesfeedbackService.list(queryWrapper);
        return publicOpinionFeedbackList;
    }


}
