package com.school;

import com.school.config.constants.ROLES;
import com.school.entity.RoleEntity;
import com.school.entity.StudentEntity;
import com.school.entity.UserEntity;
import com.school.repo.RoleRepository;
import com.school.repo.StudentRepository;
import com.school.repo.UserRepository;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Transactional
@SpringBootTest
class InitialDataLoadingTests {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    

    @Autowired
    private StudentRepository studentRepository;
    
    @Test
    void userTest() {
        UserEntity user = userRepository.getById(1L);
        Assertions.assertEquals("admin", user.getUsername());
        Assertions.assertEquals("admin@admin.com", user.getEmail());
    }
    @Test
    void roleTest() {
        RoleEntity role = roleRepository.getById(1L);
        Assertions.assertEquals(ROLES.ROLE_ADMIN, role.getName());
    }
    @Test
    void userRoleTest() {
        StudentEntity sdtuEntity = studentRepository.getById(1L);
        Assertions.assertEquals("Bernardo Conn", sdtuEntity.getName());
    }

}
