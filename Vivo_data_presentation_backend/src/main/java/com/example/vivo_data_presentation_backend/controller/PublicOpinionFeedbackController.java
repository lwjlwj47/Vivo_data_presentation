package com.example.vivo_data_presentation_backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.vivo_data_presentation_backend.common.R;
import com.example.vivo_data_presentation_backend.domain.PublicOpinionFeedback;
import com.example.vivo_data_presentation_backend.mapper.PublicOpinionFeedbackMapper;
import com.example.vivo_data_presentation_backend.service.PublicOpinionFeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
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
@RequestMapping(value = "/publicopinionfeedbacks")
public class PublicOpinionFeedbackController {

    @Autowired
    private PublicOpinionFeedbackService publicopinionfeedbackService;



    @Autowired
    private PublicOpinionFeedbackMapper lowpublicopinionFeedbackMapper;


    /*
     *查询所有手机型号的舆情反馈数据
     *
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public R<List<PublicOpinionFeedback>> getAll(){
        log.info("查询");
        List<PublicOpinionFeedback> publicOpinionFeedbackList = publicopinionfeedbackService.list();
        return R.success(publicOpinionFeedbackList);
    }
    /*
     *查询所有的舆情反馈数据数量
     *0.2版本新增
     */
    @RequestMapping(value = "/length",method = RequestMethod.GET)
    @ResponseBody
    public R getAlllength(){
        log.info("获取所有数据");
        Long publicOpinionFeedbackList = publicopinionfeedbackService.count();
        return R.success(publicOpinionFeedbackList);
    }

    /*
     *查询相应手机型号的舆情反馈数据的数量
     * 0.2版本新增
     */
    @RequestMapping(value = "/length/{machineType}",method = RequestMethod.GET)
    @ResponseBody
    public R getlength(@PathVariable String machineType){
        log.info("查询手机型号数量");
        LambdaQueryWrapper<PublicOpinionFeedback> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(PublicOpinionFeedback::getMachineType,machineType);
        List<PublicOpinionFeedback> publicopinionfeedbacks = publicopinionfeedbackService.list(queryWrapper);
        return R.success(publicopinionfeedbacks.size());
    }

    /*
     *查询相应低端的舆情反馈数据的数量
     * 0.2版本新增
     */
    @RequestMapping(value = "/low/length",method = RequestMethod.GET)
    @ResponseBody
    public R getAllLow(){
        log.info("查询低端数量");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("series","Low");
        Long machineTypes = lowpublicopinionFeedbackMapper.selectCount(queryWrapper);
        return R.success(machineTypes);
    }

    /*
     *查询相应低端的舆情反馈数据的数量
     * 0.2版本新增
     */
    @RequestMapping(value = "/mid/length",method = RequestMethod.GET)
    @ResponseBody
    public R getAllMid(){
        log.info("查询中端数量");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("series","Mid");
        Long machineTypes = lowpublicopinionFeedbackMapper.selectCount(queryWrapper);
        return R.success(machineTypes);
    }

    /*
     *查询相应低端的舆情反馈数据的数量
     * 0.2版本新增
     */
    @RequestMapping(value = "/high/length",method = RequestMethod.GET)
    @ResponseBody
    public R getAllHigh(){
        log.info("查询手机型号数量");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("series","High");
        Long machineTypes = lowpublicopinionFeedbackMapper.selectCount(queryWrapper);
        return R.success(machineTypes);
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
        List<PublicOpinionFeedback> machineTypes = lowpublicopinionFeedbackMapper.selectList(queryWrapper);
        Map<String,Long> Result = new HashMap<>();
        for(int i=0;i<machineTypes.size();i++)
        {
            LambdaQueryWrapper<PublicOpinionFeedback> queryWrapperall = new LambdaQueryWrapper();
            queryWrapperall.like(PublicOpinionFeedback::getMachineType,machineTypes.get(i).getMachineType());
            long length = lowpublicopinionFeedbackMapper.selectCount(queryWrapperall);
            Result.put(machineTypes.get(i).getMachineType(),length);
        }
        return R.success(Result);
    }

    /*
     *查询相应的舆情反馈数据类型的数量--低端
     * 0.2版本新增
     */
    @RequestMapping(value = "/low/length/category",method = RequestMethod.GET)
    @ResponseBody
    public R getLowLength(){
        log.info("查询类别数量");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("DISTINCT category");
        queryWrapper.like("series","Low");
        List<PublicOpinionFeedback> machineTypes = lowpublicopinionFeedbackMapper.selectList(queryWrapper);
        Map<String,Long> Result = new HashMap<>();
        for(int i=0;i<machineTypes.size();i++)
        {
            LambdaQueryWrapper<PublicOpinionFeedback> queryWrapperall = new LambdaQueryWrapper();
            queryWrapperall.like(PublicOpinionFeedback::getCategory,machineTypes.get(i).getCategory());
            long length = lowpublicopinionFeedbackMapper.selectCount(queryWrapperall);
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
        List<PublicOpinionFeedback> machineTypes = lowpublicopinionFeedbackMapper.selectList(queryWrapper);
        Map<String,Long> Result = new HashMap<>();
        for(int i=0;i<machineTypes.size();i++)
        {
            LambdaQueryWrapper<PublicOpinionFeedback> queryWrapperall = new LambdaQueryWrapper();
            queryWrapperall.like(PublicOpinionFeedback::getMachineType,machineTypes.get(i).getMachineType());
            long length = lowpublicopinionFeedbackMapper.selectCount(queryWrapperall);
            Result.put(machineTypes.get(i).getMachineType(),length);
        }
        return R.success(Result);
    }

    /*
     *查询相应的舆情反馈数据类型的数量--中端
     * 0.2版本新增
     */
    @RequestMapping(value = "/mid/length/category",method = RequestMethod.GET)
    @ResponseBody
    public R getMidLength(){
        log.info("查询手机型号数量");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("DISTINCT category");
        queryWrapper.like("series","Mid");
        List<PublicOpinionFeedback> machineTypes = lowpublicopinionFeedbackMapper.selectList(queryWrapper);
        Map<String,Long> Result = new HashMap<>();
        for(int i=0;i<machineTypes.size();i++)
        {
            LambdaQueryWrapper<PublicOpinionFeedback> queryWrapperall = new LambdaQueryWrapper();
            queryWrapperall.like(PublicOpinionFeedback::getCategory,machineTypes.get(i).getCategory());
            long length = lowpublicopinionFeedbackMapper.selectCount(queryWrapperall);
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
        List<PublicOpinionFeedback> machineTypes = lowpublicopinionFeedbackMapper.selectList(queryWrapper);
        Map<String,Long> Result = new HashMap<>();
        for(int i=0;i<machineTypes.size();i++)
        {
            LambdaQueryWrapper<PublicOpinionFeedback> queryWrapperall = new LambdaQueryWrapper();
            queryWrapperall.like(PublicOpinionFeedback::getMachineType,machineTypes.get(i).getMachineType());
            long length = lowpublicopinionFeedbackMapper.selectCount(queryWrapperall);
            Result.put(machineTypes.get(i).getMachineType(),length);
        }
        return R.success(Result);
    }



    /*
     *查询相应的舆情反馈数据类型的数量--高端
     * 0.2版本新增
     */
    @RequestMapping(value = "/high/length/category",method = RequestMethod.GET)
    @ResponseBody
    public R getHighLength(){
        log.info("查询手机型号数量");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("DISTINCT category");
        queryWrapper.like("series","High");
        List<PublicOpinionFeedback> machineTypes = lowpublicopinionFeedbackMapper.selectList(queryWrapper);
        Map<String,Long> Result = new HashMap<>();
        for(int i=0;i<machineTypes.size();i++)
        {
            LambdaQueryWrapper<PublicOpinionFeedback> queryWrapperall = new LambdaQueryWrapper();
            queryWrapperall.like(PublicOpinionFeedback::getCategory,machineTypes.get(i).getCategory());
            long length = lowpublicopinionFeedbackMapper.selectCount(queryWrapperall);
            Result.put(machineTypes.get(i).getCategory(),length);
        }
        return R.success(Result);
    }
    /*
     *查询相应手机型号的舆情反馈数据的数量
     * 0.2版本新增
     */
    @RequestMapping(value = "/length/category",method = RequestMethod.GET)
    @ResponseBody
    public R getAlllength_category(){
        log.info("查询手机型号数量");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("DISTINCT category");
        List<PublicOpinionFeedback> machineTypes = lowpublicopinionFeedbackMapper.selectList(queryWrapper);
        Map<String,Long> Result = new HashMap<>();
        for(int i=0;i<machineTypes.size();i++)
        {
            LambdaQueryWrapper<PublicOpinionFeedback> queryWrapperall = new LambdaQueryWrapper();
            queryWrapperall.like(PublicOpinionFeedback::getCategory,machineTypes.get(i).getCategory());
            long length = lowpublicopinionFeedbackMapper.selectCount(queryWrapperall);
            Result.put(machineTypes.get(i).getCategory(),length);
        }
        return R.success(Result);
    }


    /*
     *查询相应手机型号的舆情反馈数据
     *
     */
    @RequestMapping(value = "/{machineType}",method = RequestMethod.GET)
    @ResponseBody
    public R<List<PublicOpinionFeedback>> get(@PathVariable String machineType){
        log.info("查询");
        LambdaQueryWrapper<PublicOpinionFeedback> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(PublicOpinionFeedback::getMachineType,machineType);
        List<PublicOpinionFeedback> publicopinionfeedbacks = publicopinionfeedbackService.list(queryWrapper);
        return R.success(publicopinionfeedbacks);
    }

    /*
     *修改相应手机型号的舆情反馈数据
     *
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public R<PublicOpinionFeedback> putOne(@RequestBody PublicOpinionFeedback publicopinionfeedback){
        log.info("修改单个数据");
        PublicOpinionFeedback publicOpinionFeedback = new PublicOpinionFeedback();
        LambdaUpdateWrapper<PublicOpinionFeedback> lambdaUpdateWrapper = new LambdaUpdateWrapper<>(publicOpinionFeedback);
        publicopinionfeedbackService.update(publicopinionfeedback,lambdaUpdateWrapper);
        return R.success(publicopinionfeedback);
    }


    /*
     *删除相应手机型号的舆情反馈数据
     *
     */
    @RequestMapping(value = "/{machineType}",method = RequestMethod.DELETE)
    @ResponseBody
    public R DeleteOne(@PathVariable String machineType){
        log.info("删除单个数据");
        LambdaQueryWrapper<PublicOpinionFeedback> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.in(PublicOpinionFeedback::getMachineType,machineType);
        publicopinionfeedbackService.remove(queryWrapper);
        return R.success("删除成功");
    }


    /*
    * 低端-low产品相关*/
    /*
    *新增舆情反馈数据
    *
    */
    @RequestMapping(value = "/low",method = RequestMethod.POST)
    @ResponseBody
    public R add_low(@RequestBody PublicOpinionFeedback publicopinionfeedback){
        log.info("新增单条数据");
        publicopinionfeedback.setSeries("Low");
        publicopinionfeedbackService.save(publicopinionfeedback);
        return R.success("成功");
    }

    /*
     *新增多条舆情反馈数据
     *
     */
    @RequestMapping(value = "/low/batch",method = RequestMethod.POST)
    @ResponseBody
    public R adds_low(@RequestBody List<PublicOpinionFeedback> publicopinionfeedbacks){
        log.info("新增多条数据");
        for(int i=0;i<publicopinionfeedbacks.size();i++)
            publicopinionfeedbacks.get(i).setSeries("Low");
        Instant start = Instant.now();
        publicopinionfeedbackService.saveBatch(publicopinionfeedbacks,1000);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Time elapsed: " + duration.toMillis() + " milliseconds");
        return R.success("成功");
    }

    /*
     *获取舆情反馈所有数据
     *
     */
    @RequestMapping(value = "/low",method = RequestMethod.GET)
    @ResponseBody
    public R<List<PublicOpinionFeedback>> getAll_low(){
        log.info("查询");
        LambdaQueryWrapper<PublicOpinionFeedback> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(PublicOpinionFeedback::getSeries,"Low");
        List<PublicOpinionFeedback> publicOpinionFeedbackList = publicopinionfeedbackService.list(queryWrapper);
        return R.success(publicOpinionFeedbackList);
    }

    /*
     * 中端端-high产品相关*/
    /*
     *新增舆情反馈数据
     *
     */
    @RequestMapping(value = "/mid",method = RequestMethod.POST)
    @ResponseBody
    public R add_mid(@RequestBody PublicOpinionFeedback publicopinionfeedback){
        log.info("新增单条数据");
        publicopinionfeedback.setSeries("Mid");
        publicopinionfeedbackService.save(publicopinionfeedback);
        return R.success("成功");
    }

    /*
     *新增多条舆情反馈数据
     *
     */
    @RequestMapping(value = "/mid/batch",method = RequestMethod.POST)
    @ResponseBody
    public R adds_mid(@RequestBody List<PublicOpinionFeedback> publicopinionfeedbacks){
        log.info("新增多条数据");
        for(int i=0;i<publicopinionfeedbacks.size();i++)
            publicopinionfeedbacks.get(i).setSeries("Mid");
        Instant start = Instant.now();
        publicopinionfeedbackService.saveBatch(publicopinionfeedbacks,1000);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Time elapsed: " + duration.toMillis() + " milliseconds");
        return R.success("成功");
    }

    /*
     *获取舆情反馈所有数据
     *
     */
    @RequestMapping(value = "/mid",method = RequestMethod.GET)
    @ResponseBody
    public R<List<PublicOpinionFeedback>> getAll_mid(){
        log.info("查询");
        LambdaQueryWrapper<PublicOpinionFeedback> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(PublicOpinionFeedback::getSeries,"Mid");
        List<PublicOpinionFeedback> publicOpinionFeedbackList = publicopinionfeedbackService.list(queryWrapper);
        return R.success(publicOpinionFeedbackList);
    }


    /*
     * 高端-high产品相关*/
    /*
     *新增舆情反馈数据
     *
     */
    @RequestMapping(value = "/high",method = RequestMethod.POST)
    @ResponseBody
    public R add_high(@RequestBody PublicOpinionFeedback publicopinionfeedback){
        log.info("新增单条数据");
        publicopinionfeedback.setSeries("High");
        publicopinionfeedbackService.save(publicopinionfeedback);
        return R.success("成功");
    }

    /*
     *新增多条舆情反馈数据
     *
     */
    @RequestMapping(value = "/high/batch",method = RequestMethod.POST)
    @ResponseBody
    public R adds_high(@RequestBody List<PublicOpinionFeedback> publicopinionfeedbacks){
        log.info("新增多条数据");
        for(int i=0;i<publicopinionfeedbacks.size();i++)
            publicopinionfeedbacks.get(i).setSeries("High");
        Instant start = Instant.now();
        publicopinionfeedbackService.saveBatch(publicopinionfeedbacks,1000);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Time elapsed: " + duration.toMillis() + " milliseconds");
        return R.success("成功");
    }

    /*
     *获取舆情反馈所有数据
     *
     */
    @RequestMapping(value = "/high",method = RequestMethod.GET)
    @ResponseBody
    public R<List<PublicOpinionFeedback>> getAll_high(){
        log.info("查询");
        LambdaQueryWrapper<PublicOpinionFeedback> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(PublicOpinionFeedback::getSeries,"High");
        List<PublicOpinionFeedback> publicOpinionFeedbackList = publicopinionfeedbackService.list(queryWrapper);
        return R.success(publicOpinionFeedbackList);
    }


}
