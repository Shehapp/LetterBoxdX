package com.DAO;

import com.model.token.ConfirmationToken;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Repository
@Transactional
public class ConfirmationTokenDAOImpl implements ConfirmationTokenDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ConfirmationToken findByToken(String token) {
        try {
            return entityManager.createQuery("from ConfirmationToken t where t.token=:token",ConfirmationToken.class)
                    .setParameter("token",token)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void save(ConfirmationToken confirmationToken) {
        entityManager.persist(confirmationToken);
    }

    @Override
    public boolean checkThereIsValidTokenForUser(String userName) {
           return entityManager.createQuery("from ConfirmationToken t where t.user.userName=:userName and t.expiresAt>:curDate", ConfirmationToken.class)
                    .setParameter("userName", userName)
                    .setParameter("curDate", LocalDateTime.now())
                    .getResultList().size() > 0;
    }
}
