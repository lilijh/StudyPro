package com.studyonline.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.studyonline.content.model.po.CourseBase;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程基本信息 Mapper 接口
 * </p>
 *
 * @author lili
 */
@Mapper
public interface CourseBaseMapper extends BaseMapper<CourseBase> {
    public CourseBase selectO(Long id);
}
