package com.bonc.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class User {
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    private Long id;
    private String username; // 用户名
    private String password; // 密码
    private String gender;  // 性别

    @Temporal(TemporalType.DATE)
    private Date birthday;  // 生日
    private Double salary ; // 工资
    private String station ; // 单位
    private String telephone ; //电话

    private String info; // 备注
}
