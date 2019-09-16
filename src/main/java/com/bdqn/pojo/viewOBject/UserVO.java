package com.bdqn.pojo.viewOBject;

import com.alibaba.fastjson.annotation.JSONField;
import com.bdqn.pojo.Address;
import com.bdqn.pojo.Role;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ClassName: {@link UserVO}
 * Description: 订单管理系统的用户类(视图领域模型)
 * Author: xyf
 * Date 2019/8/19 17:11
 */
public class UserVO extends Object implements Serializable{
    private static final long serialVersionUID = 2735267539799921165L;

    private Integer id; //id
    private String userCode; //用户编码
    private String userName; //用户名称
    private Integer gender;  //性别
    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JSONField(format = "yyyy-MM-dd")
    private Date birthday;  //出生日期
    private String phone;   //电话
    private String address; //地址
    private Integer userRole;    //用户角色
    private Role role;//用户角色信息
    private List<Address> addressList;//用户地址

    public UserVO() {
    }


    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserCode() {
        return userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Integer getGender() {
        return gender;
    }
    public void setGender(Integer gender) {
        this.gender = gender;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Integer getUserRole() {
        return userRole;
    }
    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", userRole=" + userRole +
                ", role=" + role +
                ", addressList=" + addressList +
                '}';
    }
}
