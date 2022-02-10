package com.gamepc.GamePC.service;

import com.gamepc.GamePC.domain.user.User;
import com.gamepc.GamePC.repository.user.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    // TODO : DI 이딴식으로 하면 안됨.. Config를 만들어서 해야하나?
    private final JpaUserRepository jpaUserRepository;

    public List<User> findAllUser(){
        return jpaUserRepository.findAll();
    }

    public Optional<User> findUserById(Long id){
        return jpaUserRepository.findById(id);
    }

    @Transactional
    public User saveUser(User user){
        jpaUserRepository.save(user);
        return user;
    }
}