package com.devplatform.mapper;
import com.devplatform.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserMapper{
    User getUserByEmail(String email);

    User getUserByUsername(String username);

    int save(User user);

    User getByUsernameAndPassword(String username,String password);

    String getUserNameByEmial(String emial);

    int updateAvatar(@Param("avatar") String avatar,@Param("username") String username);

    int updatePassword(@Param("username") String username,@Param("password") String password);

    String getAvatarByUsername(String username);

    int updateEmail(@Param("username") String username,@Param("email") String email);

    int IfEmailisExist(@Param("email") String email);

}
