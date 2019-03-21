package com.douzone.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileuploadService {
	//저장위치
	private static final String SAVE_PATH="/uploads";
	private static final String URL ="/uploads/images";
	
	public String restore(MultipartFile multipartFile) {
		String url = "";
		try {
			
			if (multipartFile.isEmpty()) {
				return url;
			} // 비어있는지 확인

			String originalFileName = multipartFile.getOriginalFilename();
			String extName = originalFileName.substring(originalFileName.lastIndexOf(',') + 1);// 뒤에서 부터 찾아야 확장자 +1?
			String saveFileName = generateSaveFileName(extName);
			long fileSize = multipartFile.getSize();

			System.out.println("################" + originalFileName);
			System.out.println("################" + extName);
			System.out.println("################" + saveFileName);
			System.out.println("################" + fileSize);

			byte[] fileData = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH+"/"+saveFileName);
			os.write(fileData);
			os.close();
			
			url = URL + "/"+saveFileName;
			// 컨트롤러가 알아들을수 있는 exception으로
			
		} catch (IOException ex) {
			new RuntimeException("upload fail");
		}
		return url;
	}

	private String generateSaveFileName(String extName) {
		String fileName = "";
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("." + extName);//extName이 선택한 파일 이름

		return fileName;//그때의 시간.파일이름으로나온다.
	}
}
