package com.junikarp.qbank.user.service.port;

import com.junikarp.qbank.user.domain.User;
import com.junikarp.qbank.user.infrastructure.UserEntity;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);
}
