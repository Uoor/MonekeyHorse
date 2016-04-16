package com.monkeyhorse.blog.controllers;

import com.monkeyhorse.blog.support.web.MessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

/**
 *  @author uoor<i@monkeyhorse.cn thx raysmood>
 */
@Controller
public class UserController {

    @RequestMapping("signin")
    public String signin(Principal principal, RedirectAttributes ra) {
//        ;
//        principal.getClass();
        return principal == null ? "users/signin" : "redirect:admin/";
    }
}