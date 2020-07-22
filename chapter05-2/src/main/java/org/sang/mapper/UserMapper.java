package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.Role;
import org.sang.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    User loadUserByUsername(@Param("username")String username);
    List<Role> getUserRolesByUid(Integer id);
}
