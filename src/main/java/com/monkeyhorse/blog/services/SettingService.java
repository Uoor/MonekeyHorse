package com.monkeyhorse.blog.services;

import java.io.Serializable;

/**
 *  @author uoor<i@monkeyhorse.cn thx raysmood>
 */
public interface SettingService {
    Serializable get(String key);
    Serializable get(String key, Serializable defaultValue);
    void put(String key, Serializable value);
}
