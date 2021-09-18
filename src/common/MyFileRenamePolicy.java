package common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy{

	@Override
	public File rename(File originFile) {
		
		
		//파일명을 변경하더라도 확장자 유지를 위해 확장자명 추출
		String ext = ""; 		//확장자 명을 담을 변수
		String name = originFile.getName();	//원본 파일명 ex. user.png
		int dot = name.lastIndexOf(".");	//"."의 인덱스 위치 리턴
		if(dot != -1) 						// "."이라는 문자가 name 안에 존재하
			ext = name.substring(dot); 		// "."부터 마지막까지 전체 추출
		
		// 년월일시분초 포맷
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		//최종적으로 수정할 파일명 (년월일시분초 + 최대 5자리값  + 파일확장자)
		String fileName = sdf.format(new Date()) + (int)(Math.random()* 100000) + ext;
		
		//파일을 변경 된 파일로 생성
		File newFile = new File(originFile.getParent(), fileName);
		
		//리네임 된 새로운 파일을 리턴
		return newFile;
	}

}