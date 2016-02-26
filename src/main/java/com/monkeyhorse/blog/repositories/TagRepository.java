package com.monkeyhorse.blog.repositories;

import com.monkeyhorse.blog.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  @author uoor<i@monkeyhorse.cn thx raysmood>.
 */
public interface TagRepository extends JpaRepository<Tag, Long>{
    Tag findByName(String name);
}
