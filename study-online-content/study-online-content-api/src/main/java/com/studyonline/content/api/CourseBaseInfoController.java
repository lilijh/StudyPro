package com.studyonline.content.api;

import com.studyonline.base.model.PageParams;
import com.studyonline.base.model.PageResult;
import com.studyonline.content.model.dto.QueryCourseParamsDto;
import com.studyonline.content.model.po.CourseBase;
import com.studyonline.content.service.CourseBaseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(value = "课程信息编辑接口",tags = "课程信息编辑接口")
public class CourseBaseInfoController {

    @Resource
    CourseBaseInfoService courseBaseInfoService;

    @ApiOperation("课程查询接口")
    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageParams pageParams, @RequestBody QueryCourseParamsDto queryCourseParamsDto){
       return courseBaseInfoService.queryCourseBaseList(pageParams,queryCourseParamsDto);
    }
}
