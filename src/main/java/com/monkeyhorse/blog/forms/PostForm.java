package com.monkeyhorse.blog.forms;

import com.monkeyhorse.blog.models.Tag;
import com.monkeyhorse.blog.models.support.PostFormat;
import com.monkeyhorse.blog.models.support.PostStatus;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 *  @author uoor<i@monkeyhorse.cn thx raysmood>
 */
@Data
public class PostForm {
    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotNull
    private PostFormat postFormat;

    @NotNull
    private PostStatus postStatus;

    @NotNull
    private String permalink;

    @NotNull
    private String postTags;

}
