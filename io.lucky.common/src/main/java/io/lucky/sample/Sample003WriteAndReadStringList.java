package io.lucky.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Sample003WriteAndReadStringList {

    public static void main(String[] args) {
        File file = new File(Sample003WriteAndReadStringList.class.getName() + ".txt");
        try (
                FileOutputStream fos = new FileOutputStream(file);
                FileInputStream fis = new FileInputStream(file);
        ) {
            printFilePath(file);
            ArrayList<String> writeList = new ArrayList<>();
            writeList.add("Hello");
            writeList.add("File");
            writeList.add("Database");
            writeString(fos, writeList);
            List<String> readList = readStringList(fis);
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

    private static void writeString(FileOutputStream fos, List<String> contents) throws IOException {
        if (contents != null) {
            fos.write(contents.size());
            for (int i = 0; i < contents.size(); i++) {
                System.out.println(i + "th write content = " + contents.get(i));
                byte[] bytes = contents.get(i).getBytes(StandardCharsets.UTF_8);
                fos.write(bytes.length);
                fos.write(bytes);
            }
        }
    }

    private static List<String> readStringList(FileInputStream fis) throws IOException {
        int contentSize = fis.read();
        ArrayList<String> ret = new ArrayList<>(contentSize);
        for(int i=0; i< contentSize; i++){
            int byteLen = fis.read();
            byte[] bytes = new byte[byteLen];
            int readLen = fis.read(bytes);
            if (readLen != -1){
                String content = new String(bytes);
                ret.add(content);
                System.out.println(i + "th read content = " + content);
            }else{
                System.out.println("ReadError");
            }
        }
        return ret;
    }

    private static void printFilePath(File file) {
        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
    }
}
