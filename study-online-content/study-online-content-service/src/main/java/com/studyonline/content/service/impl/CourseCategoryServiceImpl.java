package com.studyonline.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.studyonline.content.mapper.CourseCategoryMapper;
import com.studyonline.content.model.dto.CourseCategoryTreeDto;
import com.studyonline.content.model.po.CourseCategory;
import com.studyonline.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程分类 服务实现类
 * </p>
 *
 * @author lili
 */
@Slf4j
@Service
public class CourseCategoryServiceImpl extends ServiceImpl<CourseCategoryMapper, CourseCategory> implements CourseCategoryService {
    @Resource
    CourseCategoryMapper courseCategoryMapper;

    @Override
    public List<CourseCategoryTreeDto> queryTreeNodes(String id) {
        /*获取所有的数据*/
        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryMapper.selectTreeNodes(id);
        List<CourseCategoryTreeDto> treeDtos = courseCategoryTreeDtos
                .stream()
                /*一级菜单只要父节点是"1"的数据*/
                .filter(menu -> menu.getParentid().equals(id))
                /*映射为一个新的树形集合*/
                .peek((item)-> item.setChildrenTreeNodes(getChildren(item,courseCategoryTreeDtos)))
                .collect(Collectors.toList());

        return treeDtos;
    }

    public List<CourseCategoryTreeDto> getChildren(CourseCategoryTreeDto menu,List<CourseCategoryTreeDto> all){
        List<CourseCategoryTreeDto> result = all.stream()
                .filter(item -> item.getParentid().equals(menu.getId()))
                .peek(item-> item.setChildrenTreeNodes(getChildren(item,all)))
                .collect(Collectors.toList());
        if (result.size() == 0){
            return  null;
        }
        return result;
    }
}
