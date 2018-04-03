package com.wisely.highlight_springmvc4.web.ch4_5;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传控制器【4.5.1】：
 * 1 使用 MultipartFile file 接受上传文件。
 * 2 使用 FileUtils.writeByteArrayToFile 快速写文件到磁盘。
 */
@Controller
public class UploadController {
	
	@RequestMapping(value = "/upload",method = RequestMethod.POST)
	public @ResponseBody String upload(MultipartFile file) {//1
		
			try {
				FileUtils.writeByteArrayToFile(new File("e:/upload/"+file.getOriginalFilename()),
						file.getBytes()); //2
				return "ok";
			} catch (IOException e) {
				e.printStackTrace();
				return "wrong";
			}
			
		
	}

}
