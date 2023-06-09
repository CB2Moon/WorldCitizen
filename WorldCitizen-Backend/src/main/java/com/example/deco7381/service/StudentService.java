package com.example.deco7381.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.deco7381.pojo.Student;
import com.example.deco7381.pojo.StudentCourse;
import com.example.deco7381.pojo.UserFriend;
import com.example.deco7381.pojo.vo.GetStudentVo;
import com.example.deco7381.pojo.vo.LoginVO;
import com.example.deco7381.pojo.vo.RegisterRequestVo;
import com.example.deco7381.pojo.vo.StudentInfoVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
    public interface StudentService extends IService<Student>{
    List<String> getCourse(String studentId);
    Map<String, Object> register(RegisterRequestVo rVo);
    StudentInfoVo login(LoginVO loginVO);
    List<String> getFriends(String studentId);

    GetStudentVo getInfo(String studentId);

    void addFriend(String myId, String otherId);
//    StudentInfoVo getStudent(String studentId);
}
