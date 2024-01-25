package com.blog.app.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.app.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
//		filename
		String name = file.getOriginalFilename();
		
//		Full path
		
		String filePath = path + File.separator + name;
		
//		Create folder if not created
		
		File f = new File(filePath);
		if(!f.exists()) {
			f.mkdirs();
		}
		
//		file copy
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return name;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);
		
//		DB logic to return input stream
		
		return is;
	}

}
