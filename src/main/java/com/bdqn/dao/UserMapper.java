package com.bdqn.dao;

import com.bdqn.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * ClassName: {@link UserMapper}
 * Description: 用户数据访问层接口
 * Author: xyf
 * Date 2019/8/28 10:59
 */
public interface UserMapper {
    /**
     * Description: 查询用户数量
     * param: []
     * return: int
     * Date: 2019/8/28 11:03
     */
    int selectCount();

    List<User> selectUserByUserRoleArray(Integer[] userRoles);

    /**
     * Description: 添加用户
     * param: [user]
     * return: int
     * Date: 2019/8/29 9:11
     */
    int insertUser(@Param("user") User user);

    int delUser(@Param("uId") Integer id);

    User selectUserByCodeAndPwd(@Param("userCode")String userCode, @Param("userPassword")String userPassword);

    /**
     * Description:查询用户数据
     * param: []
     * return: java.util.List<com.bdqn.pojo.User>
     * Date: 2019/8/20 16:29
     */
    List<User> selectUsers();
    /**
     * Description: 查找所有用户（包含角色名称和姓名过滤）
     * param: [queryUserRole, queryname]
     * return: java.util.List<com.bdqn.pojo.User>
     * Date: 2019/9/6 8:01
     */
    List<User> selectUserByRoleAndName(@Param("queryUserRole") Integer queryUserRole, @Param("queryname")String queryname);


    User selectUserByCode(@Param("userCode") String userCode);

    int insertSelective(User user);

    User selectUserById(@Param("userId") Integer userId);

    Integer updateUser(User user);
}
