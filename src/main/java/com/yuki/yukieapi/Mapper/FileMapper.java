package com.yuki.yukieapi.Mapper;

import com.yuki.yukieapi.Entity.FileEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Objects;

@Mapper
public interface FileMapper {
    @Select("select fileName from file_table where fileCode=#{code}")
    String SelectCode(int code);

    @Select("select fileName,filePath from file_table where fileCode=#{code}")
    List<String> SelectFileList(int code);

    @Select("select fileName from file_table where fileName=#{name}")
    String SelectName(String name);

    @Select("select filePath from file_table where fileCode=#{code}")
    String SelectPath(int code);

    @Select("select fileName,fileCode from file_table where id > 0")
    List<FileEntity> GetFile();

    @Insert("insert into file_table values(null,#{fileName},#{fileSize},#{fileDate},#{filePath},#{fileCode})")
    void AddFile(FileEntity file);

    @Delete("delete from file_table where fileCode=#{code}")
    void DeleteFile(int code);
}
