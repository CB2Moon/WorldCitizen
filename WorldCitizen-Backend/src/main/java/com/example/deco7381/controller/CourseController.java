package com.example.deco7381.controller;

import com.example.deco7381.common.R;
import com.example.deco7381.common.ResultEnum;
import com.example.deco7381.pojo.CourseFields;
import com.example.deco7381.service.CourseFieldsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RestController
@CrossOrigin
@RequestMapping("/course")
public class CourseController {
    @Resource
    private CourseFieldsService courseFieldsService;
    @PostMapping("/add")
    public R addCourse(@RequestBody Map<String,Object> map){
        String studentId= (String) map.get("studentId");
        String courseId= (String) map.get("courseId");
        courseFieldsService.addCourse(studentId,courseId);
        return R.ok().resultEnum(ResultEnum.SUCCESS);
    }
    @GetMapping("/getfield")
    private R getAllField(){
        List<CourseFields> courseFields = courseFieldsService.getCourseFields();
        return R.ok().resultEnum(ResultEnum.SUCCESS).data("fields",courseFields);
    }
}
