package com.studyonline.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.studyonline.content.model.dto.CourseCategoryTreeDto;
import com.studyonline.content.model.po.CourseCategory;

import java.util.List;

public interface CourseCategoryService extends IService<CourseCategory> {
    public List<CourseCategoryTreeDto> queryTreeNodes(String id);
}
