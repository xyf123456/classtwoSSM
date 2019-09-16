package com.bdqn.pojo.test;
/**
 * @ClassName: User1
 * @Description: 用户测试实体
 * @Author: xyf
 * @Date 2019/7/17 11:25
 */
public class User1 {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
