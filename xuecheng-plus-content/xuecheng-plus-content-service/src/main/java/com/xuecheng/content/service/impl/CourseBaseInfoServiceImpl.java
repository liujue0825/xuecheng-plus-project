package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import org.apache.commons.lang.StringUtils;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseBaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liujue
 * @version 1.0
 * @description: 课程基本信息查询管理业务实现
 * @date 2023/11/30 9:58
 */
@Service
@Slf4j
public class CourseBaseInfoServiceImpl implements CourseBaseInfoService {
    @Resource
    private CourseBaseMapper courseBaseMapper;

    /**
     *
     * @param pageParams
     * @param queryCourseParams
     * @return
     */
    @Override
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParams) {
        // 拼装查询条件
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        // 按照课程名称模糊查询
        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParams.getCourseName()),CourseBase::getName, queryCourseParams.getCourseName());
        // 按照课程审核状态查询
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParams.getAuditStatus()),CourseBase::getAuditStatus, queryCourseParams.getAuditStatus());
        // 按照课程发布状态查询
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParams.getPublishStatus()), CourseBase::getStatus, queryCourseParams.getPublishStatus());

        // 调用mapper
        Long pageNo = pageParams.getPageNo();
        Long pageSize = pageParams.getPageSize();
        Page<CourseBase> page = new Page<>(pageNo, pageSize);
        Page<CourseBase> pageResult = courseBaseMapper.selectPage(page, queryWrapper);

        // 封装成PageResult
        // List<T> items, long counts, long page, long pageSize
        List<CourseBase> items = pageResult.getRecords();
        long counts = pageResult.getTotal();
        PageResult<CourseBase> result = new PageResult<>(items, counts, pageNo, pageSize);
        return result;
    }
}
