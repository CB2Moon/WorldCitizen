package com.example.deco7381.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.deco7381.pojo.Course;
import com.example.deco7381.pojo.Student;
import com.example.deco7381.pojo.vo.GetStudentVo;

import java.util.List;

public interface CourseService extends IService<Course> {
    List<String> getStudentList(String courseId);

    List<GetStudentVo> getStudentInfo(List<String> studentList);

    Course getCourse(String course);
}
