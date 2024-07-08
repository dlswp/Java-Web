package com.myspring.pro28.ex01;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// @Controller
public class FileDownloadController {
	private static String CURR_IMAGE_REPO_PATH = "C:\\spring\\image_repo";
	// 파일 저장 위치를 지정한다.
	
	@RequestMapping("/download")
	protected void download(@RequestParam("imageFileName") String imageFileName, HttpServletResponse response) throws Exception {
		// 다운로드할 이미지 파일 이름을 전달한다.
		
		OutputStream out = response.getOutputStream();
		
		String downFile = CURR_IMAGE_REPO_PATH + "\\" + imageFileName;
		
		File file = new File(downFile);
		// 다운로드한 파일 객체를 생성합니다.
		
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + imageFileName);
		// 헤더에 파일 이름을 설정한다.
		
		FileInputStream in = new FileInputStream(file);
		byte[] buffer = new byte[1024 * 8];
		
		while(true) {
			int count = in.read(buffer);
			if(count == -1) break;
			out.write(buffer, 0, count);
		} // 버퍼를 이용해 한 번에 8Kbyte씩 브라우저로 전송한다. 
		
		in.close();
		out.close();
		
	}
}
