package com.sss.mapper;

import com.sss.bean.User;

import java.util.List;

public interface UserMapper {
    public int add(User user);

    public void delete(int id);

    public User get(int id);

    public int update(User user);

    public List<User> list();

    public int count();
}
