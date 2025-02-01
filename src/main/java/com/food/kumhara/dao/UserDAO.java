package com.food.kumhara.dao;

import com.food.kumhara.dto.User;

public interface UserDAO extends GenericDAO<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);
    boolean isUsernameAvailable(String username);
    boolean isEmailAvailable(String email);
}
