package com.test.testspringboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

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
