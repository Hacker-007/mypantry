package com.iheights.service;

import com.iheights.dao.UserRepo;
import com.iheights.model.UserEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SessionFactory sessionFactory;

    public void addUser(String firstName,
                        String lastName,
                        String email,
                        String phoneNumber){
        userRepo.saveUser(firstName, lastName, email, phoneNumber);
    }

    public List<UserEntity> getAllUsers(){
        return userRepo.getAllUsers();
    }

    public UserEntity getUserById(int id){
        return userRepo.getUserById(id);
    }

    public void deleteUserById(int id){
        userRepo.deleteUserById(id);
    }

    public void changeUserById(int id, String firstName, String lastName, String email,
                               String phoneNumber){ userRepo.changeUserById(id, firstName, lastName, email, phoneNumber); }

    public boolean exists(Class<?> c, Object idValue){ return userRepo.exists(c, idValue); }
}
