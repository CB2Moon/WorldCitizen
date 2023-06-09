package com.example.deco7381.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.deco7381.common.Assert;
import com.example.deco7381.common.ResultEnum;
import com.example.deco7381.mapper.StudentCourseMapper;
import com.example.deco7381.mapper.StudentFriendsMapper;
import com.example.deco7381.pojo.Student;
import com.example.deco7381.mapper.StudentMapper;

import com.example.deco7381.pojo.StudentCourse;
import com.example.deco7381.pojo.UserFriend;
import com.example.deco7381.pojo.vo.GetStudentVo;
import com.example.deco7381.pojo.vo.LoginVO;
import com.example.deco7381.pojo.vo.RegisterRequestVo;
import com.example.deco7381.pojo.vo.StudentInfoVo;
import com.example.deco7381.service.StudentService;
import com.example.deco7381.utils.JwtUtils;
import com.example.deco7381.websocket.WebSocket;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private StudentCourseMapper studentCourseMapper;
    @Resource
    private StudentFriendsMapper studentFriendsMapper;
    @Override
    public List<String> getCourse(String studentId){
        HashMap<String, Object> studntIdMap = new HashMap<>();
        studntIdMap.put("student_id",studentId);
        List<StudentCourse> studentCourses = studentCourseMapper.selectByMap(studntIdMap);
        ArrayList<String> courseList = new ArrayList<String>();
        for(StudentCourse studentCourse:studentCourses){
            courseList.add(studentCourse.getCourseId());
        }
        return courseList;

    }
    @Override
    public Map<String, Object> register(RegisterRequestVo rVo) {
        int code;
        String msg=null;
        Map<String, Object> map = new HashMap<>();
        Map<String,Object> idMap=new HashMap<>();
        idMap.put("student_id",rVo.getStudentId());
        List<Student> students = studentMapper.selectByMap(idMap);
        if (students.size()!=0){
            msg= ResultEnum.REGISTER_FAILED.getMessage();
            code=ResultEnum.REGISTER_FAILED.getCode();
        } else{
            Student user = new Student();
            user.setStudentId(rVo.getStudentId());
            user.setPassword(rVo.getPassword());
            user.setCountry(rVo.getCountry());
            user.setEMail(rVo.getEmail());
            user.setName(rVo.getName());
            studentMapper.insert(user);
            msg=ResultEnum.REGISTER_SUCCESS.getMessage();
            code=ResultEnum.REGISTER_SUCCESS.getCode();
        }

        map.put("message",msg);
        map.put("code",code);
        return map;
    }

    @Override
    public StudentInfoVo login(LoginVO loginVO) {
        String studentId = loginVO.getStudentId();
        String password = loginVO.getPassword();
        QueryWrapper<Student> studentWrapper = new QueryWrapper<>();
        studentWrapper.eq("student_id",studentId);
        Student student = studentMapper.selectOne(studentWrapper);
        //判断用户是否存在
        Assert.notNull(student,ResultEnum.LOGIN_ERROR);
        //判断密码是否正确
        Assert.equals(student.getPassword(),password,ResultEnum.LOGIN_ERROR);
        //创建token
        String token = JwtUtils.createToken(student.getStudentId());
        //组装studentInfoVo
        StudentInfoVo studentInfoVo = new StudentInfoVo();
        studentInfoVo.setName(student.getName());
        studentInfoVo.setCountry(student.getCountry());
        studentInfoVo.setToken(token);
        studentInfoVo.setStudentId(student.getStudentId());
        studentInfoVo.setImgSrc("http://localhost:8080/img/"+student.getStudentId()+".jpg");
        return studentInfoVo;


    }

    @Override
    public List<String> getFriends(String studentId) {
        LambdaQueryWrapper<UserFriend> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserFriend::getUser,studentId);
        List<UserFriend> userFriends = studentFriendsMapper.selectList(lambdaQueryWrapper);
        List<String> friends=new ArrayList<>();
        for(UserFriend userFriend:userFriends){
            friends.add(userFriend.getFriend());
        }
        return friends;
    }
    @Override
    public GetStudentVo getInfo(String studentId){
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("student_id",studentId);
        Student student = studentMapper.selectOne(studentQueryWrapper);
        GetStudentVo getStudentVo = new GetStudentVo();
        getStudentVo.setEmail(student.getEMail());
        getStudentVo.setName(student.getName());
        getStudentVo.setStudentId(student.getStudentId());
        getStudentVo.setCountry(student.getCountry());
        getStudentVo.setImgSrc("http://localhost:8080/img/"+student.getStudentId()+".jpg");
        return getStudentVo;

    }

    @Override
    public void addFriend(String myId, String otherId) {
        UserFriend userFriend1 = new UserFriend();
        UserFriend userFriend2 = new UserFriend();
        userFriend1.setUser(myId);
        userFriend1.setFriend(otherId);
        userFriend2.setFriend(myId);
        userFriend2.setUser(otherId);
        studentFriendsMapper.insert(userFriend1);
        studentFriendsMapper.insert(userFriend2);
    }


}
