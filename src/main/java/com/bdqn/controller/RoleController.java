package com.bdqn.controller;

import com.bdqn.exception.BusinessException;
import com.bdqn.exception.EnumBusinessError;
import com.bdqn.pojo.Role;
import com.bdqn.response.CommonReturnType;
import com.bdqn.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ClassName: {@link com.bdqn.pojo.Role}
 * Description: 角色控制器
 * Author: xyf
 * Date 2019/9/9 11:03
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * Description: 获取角色数据
     * param: []
     * return: java.lang.Object
     * Date: 2019/9/9 11:13
     */
    @GetMapping("/roleList")
    @ResponseBody
    public CommonReturnType getRolesList() throws BusinessException {
        List<Role> roleList = null;
        roleList = roleService.findRoles();
        if (roleList.size() == 0) {
            throw new BusinessException(EnumBusinessError.ROLES_NOT_FOUND);
        }
        return CommonReturnType.create(roleList);
    }
}
