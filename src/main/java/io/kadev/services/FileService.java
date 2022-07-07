package io.kadev.services;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
public class FileService {
	 	@Value("${files.path}")
	    private String filesPath;

	    public Resource download(String filename) {
	    	System.out.println(filesPath);
	        try {
	            Path file = Paths.get(filesPath)
	                             .resolve(filename);
	            Resource resource = new UrlResource(file.toUri());
	            System.out.println(file.toUri().toString());

	            if (resource.exists() || resource.isReadable()) {
	                return resource;
	            } else {
	                throw new RuntimeException("Could not read the file!");
	            }
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("Error: " + e.getMessage());
	        }
	    }
}
