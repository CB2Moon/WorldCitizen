package com.example.deco7381.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetStudentVo {
    private String name;
    private String country;
    private String email;
    private String studentId;
    private String imgSrc;
}
