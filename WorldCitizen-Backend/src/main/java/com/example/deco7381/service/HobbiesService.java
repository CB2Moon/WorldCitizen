package com.example.deco7381.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.deco7381.pojo.Course;
import com.example.deco7381.pojo.Hobbies;
import com.example.deco7381.pojo.Student;
import com.example.deco7381.pojo.vo.GetStudentVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface HobbiesService extends IService<Hobbies> {
    List<String> getHobbies(String studentId);

    void addHobbies(String studentId,String hobby);

    void deleteHobby(String studentId, String hobby);

    HashMap<String, Set<GetStudentVo>> getRandomFriends(String studentId);
}
