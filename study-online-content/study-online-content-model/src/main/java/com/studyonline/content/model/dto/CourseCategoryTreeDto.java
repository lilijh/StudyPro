package com.studyonline.content.model.dto;

import com.studyonline.content.model.po.CourseCategory;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * <p>
 * 课程分类
 * </p>
 *
 * @author lili
 */
@Data
public class CourseCategoryTreeDto extends CourseCategory {
    List childrenTreeNodes;

}
