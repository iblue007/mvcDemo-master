package com.xjt.util;

import java.io.File;

public class FileUtil {

    public static void DirExist(String fileStr){
        File file = new File(fileStr);
        if(!file.exists()){
            System.out.print("create dir");
            file.mkdir();
        }
    }
}
