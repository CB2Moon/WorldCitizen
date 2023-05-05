package com.example.deco7381.controller;

import com.example.deco7381.common.R;
import com.example.deco7381.common.ResultEnum;
import com.example.deco7381.service.HobbiesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RestController
@CrossOrigin
@RequestMapping("/hobby")
public class HobbyController {
    @Resource
    private HobbiesService hobbiesService;

    @PostMapping("/add")
    public R addHobby(@RequestBody Map<String,Object> map){
        String studentId = (String)map.get("studentId");
        String hobby = (String)map.get("hobby");
        hobbiesService.addHobbies(studentId,hobby);
        return R.ok().resultEnum(ResultEnum.SUCCESS);
    }

    @GetMapping("/get")
    public R getHobby(String studentId){
        List<String> hobbies = hobbiesService.getHobbies(studentId);
        return R.ok().resultEnum(ResultEnum.SUCCESS).data("hobbies",hobbies);
    }

    @PostMapping("/delete")
    public R deleteHobby(@RequestBody Map<String,Object> map ){
        String studentId = (String)map.get("studentId");
        String hobby = (String)map.get("hobby");
        hobbiesService.deleteHobby(studentId,hobby);
        return R.ok().resultEnum(ResultEnum.SUCCESS);
    }
}
