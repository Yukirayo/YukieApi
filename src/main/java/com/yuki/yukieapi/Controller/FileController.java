package com.yuki.yukieapi.Controller;
import com.yuki.yukieapi.Entity.FileEntity;
import com.yuki.yukieapi.Mapper.FileMapper;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.io.File;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.lang.Math;
import java.util.List;

@RestController
public class FileController {
@Autowired
FileMapper mapper;

@PostMapping("/upload")
@CrossOrigin
public String FileUpload(MultipartFile file){

    if (!file.isEmpty()){
        try{
            FileEntity fileTarget = new FileEntity();
            LocalDateTime currentLocalTime = LocalDateTime.now();
            ZoneId zone = ZoneId.of("Asia/Shanghai");
            ZonedDateTime beijingTime = ZonedDateTime.of(currentLocalTime,zone);
        //随机数生成
            int fileCode = (int)(Math.random() * 1000);
            if (mapper.SelectCode(fileCode)!=null){
                fileCode++;
            }
        //输入输出流对文件操作

//            String uploadPath = "F://Test";
            String uploadPath = "/usr/api/file";
            @Cleanup
            InputStream input = file.getInputStream();
            Path filePath = Paths.get(uploadPath, file.getOriginalFilename());
            Files.copy(input,filePath,StandardCopyOption.REPLACE_EXISTING,StandardCopyOption.COPY_ATTRIBUTES);

            fileTarget.setFileName(file.getOriginalFilename());
            fileTarget.setFileSize(file.getSize());
            fileTarget.setFilePath(filePath.toString());
            fileTarget.setFileDate(beijingTime.toString());
            fileTarget.setFileCode(fileCode);

            if (mapper.SelectName(file.getOriginalFilename())==null){
                mapper.AddFile(fileTarget);
                return "文件上传成功";
            }
            return "文件存在";

        }catch(Exception e){
            return "错误，请重试"+" "+e.getMessage();
        }
    }else{
        return "请选择上传的文件";
    }
}

    @RequestMapping ("/download")
    @CrossOrigin
    public String download(@RequestParam int code){
        return mapper.SelectCode(code);
    }

    @RequestMapping("/name")
    @CrossOrigin
    public List<FileEntity> fileName(){
        List<FileEntity> g = mapper.GetFile();
        return g;
    }

    @RequestMapping("/delete")
    @CrossOrigin
    public int deleteFile(@RequestParam("code") int code){
        String filepath = mapper.SelectPath(code);
        mapper.DeleteFile(code);
        File file = new File(filepath);

        try {
            if (file.exists()) {
                if (file.delete()) {
                    return 1;
                } else {
                    return 2;
                }
            } else {
                return 3;
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            return 4;
        } catch (Exception e) {
            e.printStackTrace();
            return 5;
        }
    }

}

