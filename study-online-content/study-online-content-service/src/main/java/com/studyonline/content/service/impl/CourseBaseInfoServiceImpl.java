package com.studyonline.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.studyonline.base.exception.StudyOnlineException;
import com.studyonline.base.model.PageParams;
import com.studyonline.base.model.PageResult;
import com.studyonline.content.mapper.CourseBaseMapper;
import com.studyonline.content.mapper.CourseCategoryMapper;
import com.studyonline.content.mapper.CourseMarketMapper;
import com.studyonline.content.model.dto.AddCourseDto;
import com.studyonline.content.model.dto.CourseBaseInfoDto;
import com.studyonline.content.model.dto.QueryCourseParamsDto;
import com.studyonline.content.model.po.CourseBase;
import com.studyonline.content.model.po.CourseCategory;
import com.studyonline.content.model.po.CourseMarket;
import com.studyonline.content.service.CourseBaseInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseBaseInfoServiceImpl implements CourseBaseInfoService {

    @Resource
    CourseBaseMapper courseBaseMapper;
    @Resource
    CourseMarketMapper courseMarketMapper;
    @Resource
    CourseCategoryMapper courseCategoryMapper;

    @Override
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto) {
        //构建查询条件对象
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        //构建查询条件，根据课程名称查询
        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParamsDto.getCourseName()),CourseBase::getName,queryCourseParamsDto.getCourseName());
        //构建查询条件，根据课程审核状态查询
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getAuditStatus()),CourseBase::getAuditStatus,queryCourseParamsDto.getAuditStatus());
        //构建查询条件，根据课程发布状态查询
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getPublishStatus()), CourseBase::getAuditStatus,queryCourseParamsDto.getPublishStatus());
        //分页对象
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        // 查询数据内容获得结果
        Page<CourseBase> pageResult = courseBaseMapper.selectPage(page, queryWrapper);
        // 获取数据列表
        List<CourseBase> list = pageResult.getRecords();
        // 获取数据总数
        long total = pageResult.getTotal();
        // 构建结果集
        PageResult<CourseBase> courseBasePageResult = new PageResult<>(list, total, pageParams.getPageNo(), pageParams.getPageSize());
        return courseBasePageResult;
    }

    @Override
    @Transactional
    public CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto) {
        CourseBase course = new CourseBase();
        BeanUtils.copyProperties(addCourseDto,course);
        course.setAuditStatus("202002");
        course.setStatus("203001");
        course.setCompanyId(companyId);
        int insert = courseBaseMapper.insert(course);
        Long courseId = course.getId();
        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(addCourseDto,courseMarket);
        courseMarket.setId(courseId);
        String charge = addCourseDto.getCharge();
        if (charge.equals("201001")){
            Float price = addCourseDto.getPrice();
            if (price == null || price.floatValue() <= 0){
                throw new StudyOnlineException("课程设置了收费价格不能为空且必须大于0");
            }
        }
        //插入课程营销信息
        int insert1 = courseMarketMapper.insert(courseMarket);
        if(insert<=0 || insert1<=0){
            throw new RuntimeException("新增课程基本信息失败");
        }
        //添加成功
        //返回添加的课程信息
        return getCourseBaseInfo(courseId);
    }

    /**
     * 构造响应对象
     * @param courseId
     * @return CourseBaseInfoDto
     */
    public CourseBaseInfoDto getCourseBaseInfo(long courseId){
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        if(courseBase == null){
            return null;
        }
        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
        BeanUtils.copyProperties(courseBase,courseBaseInfoDto);
        if(courseMarket != null){
            BeanUtils.copyProperties(courseMarket,courseBaseInfoDto);
        }
        //查询分类名称
        CourseCategory courseCategoryBySt =
                courseCategoryMapper.selectById(courseBase.getSt());
        courseBaseInfoDto.setStName(courseCategoryBySt.getName());
        CourseCategory courseCategoryByMt =
                courseCategoryMapper.selectById(courseBase.getMt());
        courseBaseInfoDto.setMtName(courseCategoryByMt.getName());
        return courseBaseInfoDto;
    }

}
