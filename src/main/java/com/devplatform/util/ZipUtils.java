package com.devplatform.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {
    private static final int BUFFER_SIZE = 2 * 1024;

    public static void toZip(String srcDir, OutputStream out){
        ZipOutputStream zos = null;
        try{
            zos = new ZipOutputStream(out);
            File souceFile = new File(srcDir);
            compress(souceFile,zos,souceFile.getName());
            System.out.println("压缩完成");

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (zos != null){
                try{
                    zos.close();
                }catch (IOException e){
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        }
    }


    //对文件夹进行压缩 需要保留文件夹的目录结构
    private static void compress(File sourceFile,ZipOutputStream zos,String name) throws IOException {
        byte[] buf = new byte[BUFFER_SIZE];
        if (sourceFile.isFile()){
            zos.putNextEntry(new ZipEntry(name));
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1){
                zos.write(buf,0,len);
            }
            zos.closeEntry();
            in.close();
        }else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0){
                zos.putNextEntry(new ZipEntry(name + File.separator));
                System.out.println("没有文件");
            }else {
                for (File file:listFiles){
                    compress(file,zos,name+File.separator+file.getName());
                }
            }
        }
    }
}
