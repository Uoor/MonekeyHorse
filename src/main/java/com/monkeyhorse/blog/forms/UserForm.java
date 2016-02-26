package com.monkeyhorse.blog.forms;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *  @author uoor<i@monkeyhorse.cn thx raysmood>.
 */
@Data
public class UserForm {
    @NotNull
    private String password;

    @NotNull
    private String newPassword;
}
