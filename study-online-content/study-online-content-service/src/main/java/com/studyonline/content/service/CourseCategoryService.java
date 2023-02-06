package com.studyonline.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.studyonline.content.model.dto.CourseCategoryTreeDto;
import com.studyonline.content.model.po.CourseCategory;

import java.util.List;


/**
 * <p>
 * 课程分类 服务类
 * </p>
 *
 * @author lili
 * @since 2023-02-05
 */
public interface CourseCategoryService extends IService<CourseCategory> {
    public List<CourseCategoryTreeDto> queryTreeNodes(String id);
}
