package com.yuki.yukieapi.Utils;
import java.math.*;

public class UsersUtils {
    public String token;

    public static String generateToken(){
        int length = 40;
        StringBuilder sb = new StringBuilder(length);
        double ratio = 0.35;
        char stringRandom;
        double judge;
        int stringRandomN = 0;
        for (int i=0;i<length;i++){
            judge =Math.random();
            if (judge < ratio){
                stringRandomN =(int) ((Math.random()*26)+65);
            }else if (judge > ratio+0.20){
                stringRandomN =(int) ((Math.random()*26)+97);
            }else{
                stringRandomN =(int) ((Math.random()*10));
                sb.append(stringRandomN);
                continue;
            }
            stringRandom = (char) stringRandomN;
            sb.append(stringRandom);
        }
        return sb.toString();
    }
}
