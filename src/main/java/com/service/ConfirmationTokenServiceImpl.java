package com.service;

import com.DAO.ConfirmationTokenDAO;
import com.model.User;
import com.model.token.ConfirmationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService{

    @Autowired
    ConfirmationTokenDAO confirmationTokenDAO;
    @Autowired
    UserService userService;
    @Autowired
    EmailService emailService;

    @Override
    public ConfirmationToken findByToken(String token) {
        return confirmationTokenDAO.findByToken(token);
    }

    @Override
    public boolean checkThereIsValidTokenForUser(String userName) {
        return confirmationTokenDAO.checkThereIsValidTokenForUser(userName);
    }

    @Transactional
    @Override
    public void save(String userName) {
        if (checkThereIsValidTokenForUser(userName))
            return;
        User user=userService.getUserByUserName(userName);

        String token= UUID.randomUUID().toString();
        ConfirmationToken confirmationToken=
                new ConfirmationToken(
                        token,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMinutes(15),
                        user
                );
        confirmationTokenDAO.save(confirmationToken);
        emailService.sendSMS(user.getEmail(),userName,token);

    }

    @Transactional
    @Override
    public void confirm(String token) {
        ConfirmationToken confirmationToken=this.findByToken(token);
        if(confirmationToken==null)
            throw new IllegalArgumentException("Token not found");

        if(confirmationToken.getConfirmedAt()!=null)
            throw new IllegalArgumentException("email already confirmed");

        if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("Token expired");

        User user=confirmationToken.getUser();
        user.setEnabled(true);
        userService.updateUser(user);

        confirmationToken.setConfirmedAt(LocalDateTime.now());
        confirmationTokenDAO.save(confirmationToken);

    }
}
