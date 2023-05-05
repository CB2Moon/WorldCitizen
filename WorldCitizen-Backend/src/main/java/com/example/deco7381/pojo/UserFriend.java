package com.example.deco7381.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "user_friends")
public class UserFriend {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @TableField(value = "user")
    private String user;
    private String friend;
}
