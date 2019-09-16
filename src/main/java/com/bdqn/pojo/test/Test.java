package com.bdqn.pojo.test;

import java.util.List;

/**
 * @ClassName: Test
 * @Description: 测试实体
 * @Author: xyf
 * @Date 2019/7/17 8:53
 */
public class Test {

    private List<User1> users; // List类型

    public List<User1> getUsers() {
        return users;
    }

    public void setUsers(List<User1> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Test{" +
                "users=" + users +
                '}';
    }
}
