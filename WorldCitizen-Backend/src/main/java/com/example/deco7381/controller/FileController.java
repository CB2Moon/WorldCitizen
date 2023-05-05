package com.example.deco7381.controller;

import com.example.deco7381.common.R;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {
//    @PostMapping("/upload")
//    public R upLoad(MultipartFile photo){
//        String filename = photo.getOriginalFilename();
//        String houzhui = filename.substring(filename.lastIndexOf("."));
//
//    }
}
