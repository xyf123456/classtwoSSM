package com.bdqn.controller;

import com.bdqn.exception.BusinessException;
import com.bdqn.exception.EnumBusinessError;
import com.bdqn.pojo.Role;
import com.bdqn.pojo.User;
import com.bdqn.pojo.viewOBject.UserVO;
import com.bdqn.response.CommonReturnType;
import com.bdqn.service.RoleService;
import com.bdqn.service.UserService;
import com.bdqn.utils.constant.Constants;
import com.bdqn.utils.page.PageResultBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: {@link UserController}
 * Description: 用户控制器
 * Author: xyf
 * Date 2019/9/3 15:01
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * Description:
     * param: []
     * return: com.bdqn.response.CommonReturnType
     * Date: 2019/9/6 16:38
     */
    @GetMapping("/userListJson")
    @ResponseBody
    public CommonReturnType getUserList() throws BusinessException {
        List<User> userList =null;
        userList = userService.findUsers();
        if (userList.size()==0){
            throw new BusinessException(EnumBusinessError.USER_NOT_FOUND);
        }
        //        将业务层的用户数据User转换成转化成控制器层的UserVO(需要通过java8lambda表达式)
        List<UserVO> userVOList = userList.stream().map(user -> {
            UserVO userVO=this.convertUserToVO(user);
            return userVO;
        }).collect(Collectors.toList());
        return CommonReturnType.create(userVOList);
    }

    @GetMapping("/userList")
    public String userList(@RequestParam(value = "pageNum", required = false) String pageNum,
                           @RequestParam(value = "queryUserRole", required = false) Integer queryUserRole,
                           @RequestParam(value = "queryname", required = false) String queryname,
                           Model model) {

        if (pageNum == null) {
            pageNum = "1";
        }
        PageHelper.startPage(Integer.valueOf(pageNum), 5, "`u`.creationDate desc");
//        PageResultBean<User> userPageResultBean = new PageResultBean<>(userService.findUsers());
        PageResultBean<User> userPageResultBean = new PageResultBean<>(userService.findUsersByRoleAndName(queryUserRole,queryname));
        List<User> userList = userPageResultBean.getRows();
        List<Role> roleList = roleService.findRoles();
        model.addAttribute("userList", userList);//分页实体信息
        model.addAttribute("roleList", roleList);//角色的实体信息
        model.addAttribute("page", userPageResultBean);//分页信息
        model.addAttribute("pageNum", pageNum);//当前页
        return "user/userlist";
    }

    @GetMapping("/useraddView")
    public String useraddView(){
        return "user/useradd";
    }

    /**
     * Description: 通过userCode查询到用户
     * param: [userCode]
     * return: com.bdqn.response.CommonReturnType
     * Date: 2019/9/6 16:55
     */
    @PostMapping("/getUserByCode")
    @ResponseBody
    public CommonReturnType getUserByCode(@RequestParam("userCode") String userCode) {
        User user = null;
        user = userService.findUserByCode(userCode);
        //将业务中的模型数据转化为视图领域中的模型数据
        UserVO userVO=this.convertUserToVO(user);
        if (userVO == null) {
            return CommonReturnType.create("notExist","fail");
        }
        return CommonReturnType.create(userVO);
    }

    /**
     * Description: 将业务领域中的模型转换为视图领域中的模型
     * param: [user]
     * return: com.bdqn.pojo.viewOBject.UserVO
     * Date: 2019/9/9 12:14
     */
    public UserVO convertUserToVO(User user){
        if (user==null){
            return null;
        }
        UserVO userVO = new UserVO();
        // 通过反射将一个对象的值赋值个另外一个对象（前提是对象中属性的名字相同）
        BeanUtils.copyProperties(user,userVO);
        return userVO;
    }


    /**
     * description: TODO   新增用户的处理（包含用户图片的上传）
     *  用户编码、姓名。。。。
     * create time: 2019/9/8 20:32
     * [session, request, user, attachs]
     *
     * @return java.lang.String
     */
    @PostMapping(value = "doUseraddMulti")
    public String doUseraddMulti(HttpSession session,
                                 HttpServletRequest request,
                                 User user,
                                 @RequestParam(value = "attachs", required = false) MultipartFile[] attachs) throws IOException, BusinessException {
        String idPicPath = null;
        String workPicPath = null;
        String errorInfo = null;
        boolean flag1 = true;//是否上传
        user.setCreatedBy(((User)session.getAttribute(Constants.USERSESSION)).getId());
        user.setCreationDate(new Date());
        //        获取上传文件到指定目录的路径
        String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
        System.out.println(path);
//        String path = "D:\\classtwoworkspace\\springMyBatis\\src\\main\\webapp\\uploadfiles";
        for (int i = 0; i <attachs.length ; i++) {
            MultipartFile attach = attachs[i];
            if (!attach.isEmpty()){
                if (i==0){
                    errorInfo = "uploadFileError";//生活照错误提示
                }else if (i==1){
                    errorInfo = "uploadWpError";//工作照上传错误提示
                }
            }
            //原文件名
            String oldFileName=attach.getOriginalFilename();
            //原文件后缀
//            String prefix= FilenameUtils.getPrefix(oldFileName);
            String prefix= FilenameUtils.getExtension(oldFileName);
            int filesize = 5000000;//上传文件大小
            if (attach.getSize()>filesize){
                request.setAttribute(errorInfo, " * 上传大小不得超过 500k");
                flag1 = false;
            }else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                    || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){
                //重新命名
                String fileName=System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.jpg";
                //目标目录
                File targetFile = new File(path, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
//                FileUtils.copyInputStreamToFile(attach.getInputStream(),targetFile);
                attach.transferTo(targetFile);
                if (i==0){ //证件照
                    idPicPath  = path+File.separator+fileName;
                }else if (i==1){//工作照
                    workPicPath  = path+File.separator+fileName;
                }
            }
        }
        if (flag1){
            user.setIdPicPath(idPicPath);
            user.setWorkPicPath(workPicPath);
            //调用保存用户的业务
            if (userService.addUser1(user)) {
                return Constants.REDIRECT+"user/userList";//列表页
            }else {
                return Constants.REDIRECT + "user/useradd";//重新添加页面
            }
        }

        return Constants.REDIRECT+"user/userList";//列表页
    }

    /**
     * Description: TODO 跳转到用户视图页面
     * param: [userid, model]
     * return: java.lang.String
     * Date: 2019/9/10 12:27
     */
    @GetMapping(value = "/viewUser/{userid}")
    public String viewUser(@PathVariable Integer userid,Model model) throws BusinessException {

        //调取相应Model 业务逻辑数据
        User user = userService.findUserById(userid);
        //        需要将UserModel转换成UserVO（供用户来查看的信息）
        UserVO userVO = this.convertUserToVO(user);
        model.addAttribute("user", userVO);
        return "user/userView";
    }

    /**
     * description: TODO  跳转到用户修改页面（同时传入相关用户信息）
     * create time: 2019/9/8 20:32
     * [userId, model]
     *
     * @return java.lang.String
     */
    @GetMapping(value = "/modifyUser/{userid}")
    public String modifyUser(@PathVariable(value = "userid") Integer userId, Model model) throws BusinessException {
        //调取相应Model 业务逻辑数据
        User user = userService.findUserById(userId);

        //        需要将UserModel转换成UserVO（供用户来查看的信息）
        UserVO userVO = this.convertUserToVO(user);
        model.addAttribute("user", userVO);
        return "user/usermodify";
    }


    /**
     * Description: TODO 修改用户
     * param: [user, session]
     * return: java.lang.String
     * Date: 2019/9/11 8:50
     */
    @RequestMapping(value = "/usermodifysave", method = RequestMethod.POST)
    public String usermodifySave(User user, HttpSession session) throws Exception {
        //设置谁修改了数据
        user.setModifyBy(((User) session.getAttribute(Constants.USERSESSION)).getId());
        user.setModifyDate(new Date());
//        调用修改用户业务
        Integer result = userService.modifyUser(user);
        if (result > 0) {
            return Constants.REDIRECT + "user/userList";
        } else {
            return "user/usermodify";
        }
    }

    /**
     * Description: TODO 删除用户
     * param: [userid]
     * return: com.bdqn.response.CommonReturnType
     * Date: 2019/9/11 9:31
     */
    @GetMapping(value = "/delUser/{userId}")
    @ResponseBody
    public CommonReturnType delUser(@PathVariable(value = "userId") Integer userid) throws Exception {
        if (userid==null){
            throw new BusinessException(EnumBusinessError.ILLEGAL_ARGS);
        }
        int result=userService.delUserById(userid);
        return CommonReturnType.create(result);
    }

}
