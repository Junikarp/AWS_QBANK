package com.junikarp.qbank.user.controller.port;

import com.junikarp.qbank.user.domain.User;

public interface UserService {
    User getById(Long id);
}
