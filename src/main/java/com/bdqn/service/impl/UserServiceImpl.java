package com.bdqn.service.impl;

import com.bdqn.dao.UserMapper;
import com.bdqn.exception.BusinessException;
import com.bdqn.pojo.User;
import com.bdqn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: {@link UserServiceImpl}
 * Description:  用户业务接口实现类
 * Author: xyf
 * Date 2019/8/28 10:57
 */
//@Service("userService")
@Service
//@Scope("singleton") //直接采用注解的
//@Scope("prototype") //创建多个实例对象
@Transactional //开启事务注解
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    /**
     * Description: 查找用户的数量
     * param: []
     * return: int
     * Date: 2019/8/28 10:56
     */
    @Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true) //开启事务注解
    public int findUserCount() {
        return userMapper.selectCount();
    }

    @Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true) //开启事务注解
    public List<User> findUserByUserRoleArray(Integer[] userRoles) {
        return userMapper.selectUserByUserRoleArray(userRoles);
    }

    /**
     * Description: 添加用户
     * param: [user]
     * return: int
     * Date: 2019/8/29 9:07
     *
     * @param user
     */

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = {RuntimeException.class}) //开启事务注解
    public int addUser(User user) {
        int result =0;
        try {
            if (user==null){
                return 0;//这里是要抛出业务异常
            }else {
                result =userMapper.insertUser(user);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    /**
     * Description: 删除某个用户
     * param: [id]
     * return: int
     * Date: 2019/8/29 10:16
     *
     * @param id
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT) //开启事务注解
    public int delUserById(Integer id) throws Exception{
        /*int result = 0;
        try {
            if (id!=null){
                result=userMapper.delUser(id);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }*/
        int result = 0;
        result=userMapper.delUser(id);
        return result;
    }

    /**
     * Description: 登录
     * param: [userCode, userPassword]
     * return: com.bdqn.pojo.User
     * Date: 2019/9/3 15:30
     *
     * @param userCode
     * @param userPassword
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public User login(String userCode, String userPassword) {
        User user=userMapper.selectUserByCodeAndPwd(userCode,userPassword);
        if (user!=null){
            return user;
        }
        return null;
    }

    /**
     * Description: 查找所有用户
     * param: []
     * return: java.util.List<com.bdqn.pojo.User>
     * Date: 2019/9/4 16:39
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<User> findUsers() throws RuntimeException {
        return userMapper.selectUsers();
    }

    /**
     * Description:查找所有用户（包含角色名称和姓名过滤）
     * param: [queryUserRole, queryname]
     * return: java.util.List<com.bdqn.pojo.User>
     * Date: 2019/9/6 7:53
     *
     * @param queryUserRole
     * @param queryname
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<User> findUsersByRoleAndName(Integer queryUserRole, String queryname) {
        List<User> userList = userMapper.selectUserByRoleAndName(queryUserRole,queryname);
        if (userList==null){
            throw new RuntimeException("无法获取数据！");
        }
        return userList;
    }

    /**
     * Description: 通过userCode查找到用户
     * param: [userCode]
     * return: com.bdqn.pojo.User
     * Date: 2019/9/6 16:48
     *
     * @param userCode
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public User findUserByCode(String userCode) {
        if (userCode!=null){
            return userMapper.selectUserByCode(userCode);
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public boolean addUser1(User user) throws BusinessException {
        boolean flag = false;
        if (userMapper.insertSelective(user)==1){
            flag = true;
        }
        return flag;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
    public User findUserById(Integer userid) throws BusinessException {
        return userMapper.selectUserById(userid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public Integer modifyUser(User user) throws Exception {
        return userMapper.updateUser(user);
    }


}
