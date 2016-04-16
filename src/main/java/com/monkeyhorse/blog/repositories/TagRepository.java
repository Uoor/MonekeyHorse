package com.monkeyhorse.blog.repositories;

import com.monkeyhorse.blog.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *  @author uoor<i@monkeyhorse.cn thx raysmood>.
 */
@Repository
@Transactional
public interface TagRepository extends JpaRepository<Tag, Long>{
    Tag findByName(String name);
}
