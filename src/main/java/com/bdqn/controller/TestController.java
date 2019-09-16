package com.bdqn.controller;

import com.bdqn.pojo.Role;
import com.bdqn.pojo.User;
import com.bdqn.pojo.test.Test;
import com.bdqn.response.CommonReturnType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * ClassName: {@link TestController}
 * Description: 测试控制器（是Servlet分发的前端控制器）
 * Author: xyf
 * Date 2019/8/30 12:09
 */
//@Component
@Controller
@RequestMapping(value = "/test")
public class TestController {

    private Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping(value = "/test.html")
    public ModelAndView test() {
        logger.info("hello test");
        return new ModelAndView("test");
    }

    @RequestMapping(value = "/test1.html")
    public String test2() {
        logger.info("hello test2");
        return "test";
    }

    /**
     * Description: 基本数据类型
     * http://localhost:8080/ssm/test/baseType?userAge=23
     * param: [age]
     * return: java.lang.Object
     * Date: 2019/9/6 17:18
     */
    @PostMapping("/baseType")
    @ResponseBody
    public Object baseType(@RequestParam("userAge") int age) {
        logger.info(age);
        return CommonReturnType.create("age:" + age, "success");
    }

    /**
     * @Description: 包装类的处理(可以传入空值) http://localhost:8080/ssm/test/baseType2?age=23
     * @param: [age]
     * @return: java.lang.Object
     * @Date: 2019/07/16 8:15
     */
    @RequestMapping("/baseType2")
    @ResponseBody
    public Object baseType(@RequestParam("age") Integer age) {
//        return "age:" + age;
        return CommonReturnType.create("age:" + age);
    }

    /**
     * @Description: 数组的绑定 http://localhost:8080/ssm/test/array?games=王者&games=荣耀&games=吃鸡
     * @param: [games]
     * @return: java.lang.Object
     * @Date: 2019/07/16 8:19
     */
    @RequestMapping("/array")
    @ResponseBody
    public Object array(@RequestParam("games") String[] games) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String item :
                games) {
            stringBuilder.append(item).append(" ");
        }
//        return stringBuilder;
        return CommonReturnType.create(stringBuilder);
    }


    /**
     * @Description: http://localhost:8080/ms/test/object?username=飞飞&gender=1&birthday=2000-05-04
     * @Description: http://localhost:8080/ms/test/object?id=2
     * @Description: http://localhost:8080/ms/test/object?user.id=2&role.id=1&user.username=飞飞
     * @Description: http://localhost:8080/ms/test/object?user.id=2&role.id=1&username=飞机&rolename=经理（ @InitBinder如果使用username等属性，还是可以赋值）
     * @param: [user, role]
     * @return: java.lang.Object
     * @Date: 2019/07/17 8:00
     */
    @RequestMapping("/object")
    @ResponseBody
    public Object object(User user, Role role) {
//        return user.toString();
//        return CommonReturnType.create(user.toString());
        return CommonReturnType.create(user.toString() + "——" + role.toString());
    }

    @InitBinder("user")
    public void initUser(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("user.");
    }

    @InitBinder("role")
    public void initRole(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("role.");
    }

    /**
     * @Description: http://localhost:8080/ms/test/list?users[0].name=王者&users[1].name=荣耀
     * @param: [testEntity]
     * @return: java.lang.Object
     * @Date: 2019/07/17 8:50
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(Test test) {
//        return test.toString();
        return CommonReturnType.create(test.toString());
    }
}
