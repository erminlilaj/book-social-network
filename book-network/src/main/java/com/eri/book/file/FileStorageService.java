package com.eri.book.file;


import com.eri.book.book.Book;
import jakarta.annotation.Nonnull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.io.File.separator;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileStorageService {
    @Value("${application.file.uploads.photos-output-path}")
    private String fileUploadPath;
    public String saveFile(
            @NonNull MultipartFile sourceFile,
            @Nonnull Integer bookId,
            @NonNull Integer userId) {
        final String fileUploadSubPath= "users"+ separator + userId;
        return uploadFile(sourceFile, fileUploadSubPath);
    }

    private String uploadFile(@NonNull MultipartFile sourceFile,
                               @NonNull String fileUploadSubPath) {
        final String finalUploadPath=fileUploadPath+ separator + fileUploadSubPath;
        File targetFolder= new File(finalUploadPath);
        if(!targetFolder.exists()){
            boolean folderCreated= targetFolder.mkdirs();//make directories, it will create folder and all subfolders
            if(!folderCreated){
                log.warn("Failed to create the target folder");
                return null;
            }
        }
        final String fileExtension= getFileExtension(sourceFile.getOriginalFilename());
        // ./uploads/users/1/21221221221223.jpg
        String targetFilePath= finalUploadPath+ separator+System.currentTimeMillis()+"."+fileExtension;//to avoid spaces and uploading the same file
        Path targetPath= Paths.get(targetFilePath);
        try{
            Files.write(targetPath,sourceFile.getBytes());
            log.info("file was saved to "+ targetFilePath);
            return targetFilePath;
        }catch (IOException e) {
            log.error("File was not saved", e);
        }
        return null;
    }

    private String getFileExtension(String filename) {
        if(filename==null|| filename.isEmpty()){
            return "";
        }
        //sth .jpg
        int lastDotIndex = filename.lastIndexOf(".");
        if(lastDotIndex==-1){// the file does not have an extension
            return "";
        }
        return filename.substring(lastDotIndex+1).toLowerCase();
    }
}
