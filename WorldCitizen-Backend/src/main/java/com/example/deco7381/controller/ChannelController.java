package com.example.deco7381.controller;

import com.example.deco7381.common.R;
import com.example.deco7381.common.ResultEnum;
import com.example.deco7381.pojo.ChannelPosts;
import com.example.deco7381.pojo.ChannelTopics;
import com.example.deco7381.pojo.Student;
import com.example.deco7381.pojo.vo.GetStudentVo;
import com.example.deco7381.service.ChannelPostsService;
import com.example.deco7381.service.ChannelTopicsService;
import com.example.deco7381.service.HobbiesService;
import com.example.deco7381.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private ChannelTopicsService channelTopicsService;
    @Autowired
    private ChannelPostsService channelPostsService;
    @Autowired
    private HobbiesService hobbiesService;
    @Autowired
    private StudentService studentService;

    //读取所有话题板块属性
    /**
     * Get all the topics
     * @return
     */
    @GetMapping("/gettopics")
    public List<ChannelTopics> getAllTopics(){
        List<ChannelTopics> channelTopics = channelTopicsService.getAllTopics();
        return channelTopics;
    }

    //读取该话题板块下的帖子
    /**
     * Get all posts by topic
     * @param topicName
     * @return
     */
    @GetMapping("/getposts")
    public R getAllPostsByTopic(String topicName){
        List<ChannelPosts> channelPosts = channelPostsService.getAllPosts(topicName);
        return R.ok().resultEnum(ResultEnum.SUCCESS).data("list",channelPosts);
    }

    //生成随机推荐好友
    //通过相似或相等爱好（hobbies表里）
    /**
     * Get some random friends by recommendation algorithm
     * @return
     */
    @GetMapping("/getfriends")
    public R getRandomFriends(String studentId){
        HashMap<String, Set<GetStudentVo>> randomFriends = hobbiesService.getRandomFriends(studentId);
        return R.ok().resultEnum(ResultEnum.SUCCESS).data("randomFriend",randomFriends);
    }

    @PostMapping("/addpost")
    public R addPost(@RequestBody Map<String,Object> map){
        String userId = (String) map.get("userId");
        String topic = (String) map.get("topic");
        String post = (String) map.get("post");
        channelPostsService.addPost(userId,topic,post);
        return R.ok().resultEnum(ResultEnum.SUCCESS);
    }

    @GetMapping("/getmypost")
    public R getMyPost(String studentId){
        List<ChannelPosts> myPost = channelPostsService.getMyPost(studentId);
        return R.ok().resultEnum(ResultEnum.SUCCESS).data("myPost",myPost);

    }




}
