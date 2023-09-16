package com.DAO;

import com.model.token.ConfirmationToken;

public interface ConfirmationTokenDAO {
    ConfirmationToken findByToken(String token);

    void save(ConfirmationToken confirmationToken);
    boolean checkThereIsValidTokenForUser(String userName);
}
