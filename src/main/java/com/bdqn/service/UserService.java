package com.bdqn.service;

import com.bdqn.exception.BusinessException;
import com.bdqn.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: {@link UserService}
 * Description: 用户业务接口
 * Author: xyf
 * Date 2019/8/26 10:34
 */

public interface UserService {

    /**
     * Description: 查找用户的数量
     * param: []
     * return: int
     * Date: 2019/8/28 10:56
     */
    int findUserCount();

    List<User> findUserByUserRoleArray(Integer[] userRoles);

    /**
     * Description: 添加用户
     * param: [user]
     * return: int
     * Date: 2019/8/29 9:07
     */
    int addUser(User user);

    /**
     * Description: 删除某个用户
     * param: [id]
     * return: int
     * Date: 2019/8/29 10:16
     */
    int delUserById(Integer id) throws Exception;

    /**
     * Description: 登录
     * param: [userCode, userPassword]
     * return: com.bdqn.pojo.User
     * Date: 2019/9/3 15:30
     */
    User login(String userCode, String userPassword);
    /**
     * Description: 查找所有用户
     * param: []
     * return: java.util.List<com.bdqn.pojo.User>
     * Date: 2019/9/4 16:39
     */
    List<User> findUsers() throws RuntimeException;

    /**
     * Description:查找所有用户（包含角色名称和姓名过滤）
     * param: [queryUserRole, queryname]
     * return: java.util.List<com.bdqn.pojo.User>
     * Date: 2019/9/6 7:53
     */
    List<User> findUsersByRoleAndName(Integer queryUserRole, String queryname);

    /**
     * Description: 通过userCode查找到用户
     * param: [userCode]
     * return: com.bdqn.pojo.User
     * Date: 2019/9/6 16:48
     */
    User findUserByCode(String userCode);

    boolean addUser1(User user) throws BusinessException;

    User findUserById(Integer userid) throws BusinessException;

    Integer modifyUser(User user) throws Exception;
}
