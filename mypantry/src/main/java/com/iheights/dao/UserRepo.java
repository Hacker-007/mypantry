package com.iheights.dao;

import com.iheights.model.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserRepo {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    public void saveUser(String firstName,
                            String lastName,
                            String email,
                            String phoneNumber) {

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setEmail(email);
        userEntity.setPhoneNumber(phoneNumber);

        //try {
        getSession().save(userEntity);
//        } catch(ConstraintViolationException e) {
//            throw new DuplicateAddressException();
//        }
        //return addressEntity;

    }

    @SuppressWarnings("unchecked")
    public List<UserEntity> getAllUsers(){
        return (List<UserEntity>) getSession().createCriteria(UserEntity.class).list();
    }

    public UserEntity getUserById(int id){
        return (UserEntity) getSession().createCriteria(UserEntity.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    public void deleteUserById(int id){
        UserEntity userEntity = getSession().byId(UserEntity.class).load(id);
        getSession().delete(userEntity);
    }

    public void changeUserById(int id, String firstName, String lastName, String email,
                               String phoneNumber){
        UserEntity userEntity = getSession().get(UserEntity.class, id);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setEmail(email);
        userEntity.setPhoneNumber(phoneNumber);
    }

    public void authenticate(){

    }

    public boolean exists(Class<?> c, Object idValue){
        return getSession().createCriteria(c)
                .add(Restrictions.idEq(idValue)).setProjection(Projections.id())
                .uniqueResult() != null;
    }
}