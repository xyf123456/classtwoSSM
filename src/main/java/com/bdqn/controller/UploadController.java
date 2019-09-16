package com.bdqn.controller;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * ClassName: {@link UploadController}
 * Description: TODO 上传控制器
 * Author: xyf
 * Date 2019/9/10 9:11
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    private static Logger log = Logger.getLogger(UploadController.class);

    /**
     * Description: TODO 跳转到对应的上传页面
     * param: [multi]
     * return: java.lang.String
     * Date: 2019/9/10 9:14
     */
//    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @GetMapping(value = "/index")
    public String showUploadPage(@RequestParam(value = "multi", required = false) Boolean multi) {
        if (multi != null && multi) {
            return "course_admin/multifile";
        }
        return "course_admin/file";
    }

    /**
     * Description: TODO 处理单文件上传
     * param: [file]
     * return: java.lang.String
     * Date: 2019/9/10 9:17
     */
    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String doUploadFile(@RequestParam("file1") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            log.debug(file.getOriginalFilename());
            log.debug(file.getName());
            log.debug(file.getBytes().toString());
            log.debug(file.getSize());
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File("D:\\temp\\test\\", System.currentTimeMillis() + file.getOriginalFilename()));
        }
        return "course_admin/success";
    }


    /**
     * Description: TODO 处理多文件上传
     * param: [multiRequest]
     * return: java.lang.String
     * Date: 2019/9/10 9:59
     */
//    @RequestMapping(value = "/doUpload2", method = RequestMethod.POST)
    @PostMapping(value = "/doUpload2")
    public String doUploadFile2(MultipartHttpServletRequest multiRequest) throws IOException {
        Iterator<String> filesNames = multiRequest.getFileNames();
        while (filesNames.hasNext()) {
            String fileName = filesNames.next();
            MultipartFile file = multiRequest.getFile(fileName);
            if (!file.isEmpty()) {
                log.debug(file.getOriginalFilename());
                log.debug(file.getName());
                log.debug(file.getBytes().toString());
                log.debug(file.getSize());
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File("D:\\temp\\test\\", System.currentTimeMillis() + file.getOriginalFilename()));
            }

        }

        return "course_admin/success";
    }
}
