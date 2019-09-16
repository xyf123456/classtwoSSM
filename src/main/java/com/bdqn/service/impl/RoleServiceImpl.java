package com.bdqn.service.impl;

import com.bdqn.dao.RoleMapper;
import com.bdqn.pojo.Role;
import com.bdqn.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: {@link RoleServiceImpl}
 * Description: 角色业务接口实现类
 * Author: xyf
 * Date 2019/8/28 12:01
 */
//@Service("roleService")
@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;


    /**
     * Description: 查找角色数量
     * param: []
     * return: int
     * Date: 2019/8/28 12:02
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public int findRoleCount() {
        return roleMapper.selectCount();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Role> findRoles() {
        List<Role> roleList=roleMapper.selectRoles();
        if (roleList==null){
            return null;
        }
        return roleList;
    }
}
