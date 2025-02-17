package com.technical.qimatech_test.security;

import com.technical.qimatech_test.entity.UserEntity;
import com.technical.qimatech_test.repository.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SuperUserLoader implements CommandLineRunner {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SuperUserLoader(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.findByUsername("admin").isPresent()) {
            UserEntity superUser = new UserEntity();
            superUser.setUsername("admin");
            superUser.setPassword(passwordEncoder.encode("adminpassword"));
            superUser.setRoles(Set.of("ROLE_ADMIN"));
            userRepository.save(superUser);
        }
    }
}
