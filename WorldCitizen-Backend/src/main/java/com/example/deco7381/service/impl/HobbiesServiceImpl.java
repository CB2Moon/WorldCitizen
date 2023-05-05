package com.example.deco7381.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.deco7381.mapper.HobbiesMapper;
import com.example.deco7381.mapper.StudentMapper;
import com.example.deco7381.pojo.Hobbies;
import com.example.deco7381.pojo.Student;
import com.example.deco7381.pojo.StudentCourse;
import com.example.deco7381.pojo.vo.GetStudentVo;
import com.example.deco7381.service.HobbiesService;
import com.example.deco7381.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HobbiesServiceImpl extends ServiceImpl<HobbiesMapper, Hobbies> implements HobbiesService {

    @Autowired
    private HobbiesMapper hobbiesMapper;
    @Autowired
    private StudentService studentService;

    @Override
    public List<String> getHobbies(String studentId) {
        LambdaQueryWrapper<Hobbies> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Hobbies::getStudentId,studentId);
        List<Hobbies> hobbies = hobbiesMapper.selectList(lambdaQueryWrapper);
        ArrayList<String> hobbiesList = new ArrayList<String>();
        for(Hobbies hobbies1:hobbies){
            hobbiesList.add(hobbies1.getHobby());
        }
        return hobbiesList;
    }

    @Override
    public void addHobbies(String studentId,String hobby){
        Hobbies hobbies = new Hobbies();
        hobbies.setHobby(hobby);
        hobbies.setStudentId(studentId);
        hobbiesMapper.insert(hobbies);
    }

    @Override
    public void deleteHobby(String studentId, String hobby) {
        QueryWrapper<Hobbies> hobbiesQueryWrapper = new QueryWrapper<>();
        hobbiesQueryWrapper.eq("student_id",studentId).eq("hobby",hobby);
        hobbiesMapper.delete(hobbiesQueryWrapper);
    }

    @Override
    public HashMap<String, Set<GetStudentVo>> getRandomFriends(String studentId) {

        LambdaQueryWrapper<Hobbies> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Hobbies::getStudentId,studentId);
        List<Hobbies> myHobbiesList = hobbiesMapper.selectList(lambdaQueryWrapper);
        HashMap<String, Set<GetStudentVo>> stringSetHashMap = new HashMap<>();

//        LambdaQueryWrapper<Hobbies> lambdaQueryWrapper2 = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper2.ne(Hobbies::getStudentId,studentId);
//        List<Hobbies> othersHobbiesList = hobbiesMapper.selectList(lambdaQueryWrapper2);
        for(Hobbies hobbies:myHobbiesList){
            String hobby = hobbies.getHobby();
            LambdaQueryWrapper<Hobbies> hobbiesLambdaQueryWrapper = new LambdaQueryWrapper<>();
            hobbiesLambdaQueryWrapper.eq(Hobbies::getHobby,hobby);
            List<Hobbies> sameHobbyStudent = hobbiesMapper.selectList(hobbiesLambdaQueryWrapper);
            HashSet<GetStudentVo> students = new HashSet<>();
            for(Hobbies hobbies1:sameHobbyStudent){
                if(!hobbies1.getStudentId().equals(studentId)){
                    String studentId1 = hobbies1.getStudentId();
                    GetStudentVo info = studentService.getInfo(studentId1);
                    students.add(info);
                }
            }
            stringSetHashMap.put(hobby,students);
        }
        return stringSetHashMap;

//        Set<Student> RandomFriends = new HashSet<>();
//        for (Hobbies hobbies:myHobbiesList) {
//            for (Hobbies hobbies1:othersHobbiesList) {
//                if (hobbies.getHobby().equals(hobbies1.getHobby())){
//                    String id = hobbies1.getStudentId();
//                    LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
//                    studentLambdaQueryWrapper.eq(Student::getStudentId,id);
//                    Student student = studentService.getOne(studentLambdaQueryWrapper);
//                    if (id!=studentId){
//                        RandomFriends.add(student);
//                    }
//                }
//            }
//
//        }
//        return RandomFriends;
    }

}
