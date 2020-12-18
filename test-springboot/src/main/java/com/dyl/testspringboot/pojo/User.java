package com.dyl.testspringboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

//    @NotNull(message = "用户名不能为空2")
//    @NotBlank(message = "用户名不能为空1")
    @NotEmpty(message = "用户名不能为空")
    private String username;

    private String password;
}
