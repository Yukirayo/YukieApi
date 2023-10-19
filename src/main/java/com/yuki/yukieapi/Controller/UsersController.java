package com.yuki.yukieapi.Controller;

import com.yuki.yukieapi.Mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    @Autowired
    UsersMapper mapper;

    @RequestMapping("/log")
    @CrossOrigin
    public int Login(@RequestParam String name,String password){
        if (mapper.SelectName(name)!=null){
            if (mapper.SelectPassword(name).equalsIgnoreCase(password)){
                return 4;
            }else {
                return 5;
            }
        }else{
            return 3;
        }
    }

    @RequestMapping("/reg")
    @CrossOrigin
    public int Register(){
        return 1;
    }
}
