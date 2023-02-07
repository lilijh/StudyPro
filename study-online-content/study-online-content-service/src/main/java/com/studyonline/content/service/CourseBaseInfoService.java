package com.studyonline.content.service;

import com.studyonline.base.model.PageParams;
import com.studyonline.base.model.PageResult;
import com.studyonline.content.model.dto.AddCourseDto;
import com.studyonline.content.model.dto.CourseBaseInfoDto;
import com.studyonline.content.model.dto.QueryCourseParamsDto;
import com.studyonline.content.model.po.CourseBase;


public interface CourseBaseInfoService {
    /**
     * @description 课程查询接口
     * @param pageParams 分页参数
     * @param queryCourseParamsDto 条件条件
     * @return
     * @author lili
     * @date 2022/9/6 21:44
     */
    PageResult<CourseBase> queryCourseBaseList(PageParams pageParams,
                                               QueryCourseParamsDto queryCourseParamsDto);

    CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto);
}
