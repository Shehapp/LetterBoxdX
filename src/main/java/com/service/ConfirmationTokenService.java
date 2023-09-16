package com.service;

import com.model.User;
import com.model.token.ConfirmationToken;

public interface ConfirmationTokenService {
    ConfirmationToken findByToken(String token);
    boolean checkThereIsValidTokenForUser(String userName);
    void save(String userName);
    void confirm(String token);
}
