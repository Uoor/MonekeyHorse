package com.monkeyhorse.blog.controllers;

import com.monkeyhorse.blog.error.NotFoundException;
import com.monkeyhorse.blog.models.Post;
import com.monkeyhorse.blog.services.PostService;
import com.monkeyhorse.blog.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.*;


/**
 *  @author uoor<i@monkeyhorse.cn thx raysmood>
 */
@Controller
@RequestMapping("posts")
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping(value = "archive", method = GET)
    public String archive(Model model){
        model.addAttribute("posts", postService.getArchivePosts());

        return "posts/archive";
    }

    @RequestMapping(value = "{permalink}", method = GET)
    public String show(@PathVariable String permalink, Model model){
        Post post = null;

        try{
            post = postService.getPublishedPostByPermalink(permalink);
        } catch (NotFoundException ex){
            if (permalink.matches("\\d+"))
                post = postService.getPost(Long.valueOf(permalink));
        }

        if (post == null)
            throw new NotFoundException("Post with permalink " + permalink + " is not found");

        model.addAttribute("post", post);
        model.addAttribute("tags", postService.getPostTags(post));

        return "posts/show";
    }

}
