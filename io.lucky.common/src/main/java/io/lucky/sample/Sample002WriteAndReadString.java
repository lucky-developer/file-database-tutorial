package io.lucky.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Sample002WriteAndReadString {

    public static void main(String[] args) {
        File file = new File(Sample002WriteAndReadString.class.getName() + ".txt");
        try (
                FileOutputStream fos = new FileOutputStream(file);
                FileInputStream fis = new FileInputStream(file);
        ) {
            printFilePath(file);
            String content = "Hello World";
            int len = writeString(fos, content);
            readString(fis, len);
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

    private static int writeString(FileOutputStream fos, String content) throws IOException {
        System.out.println("writeString = " + content);
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        fos.write(bytes);
        fos.flush();
        return bytes.length;
    }

    private static void readString(FileInputStream fis, int len) throws IOException {
        byte[] bytes = new byte[len];
        int readLen = fis.read(bytes);
        if (readLen != -1){
            String content = new String(bytes);
            System.out.println("readString = " + content);
        }else{
            System.out.println("readString Error");
        }
    }

    private static void printFilePath(File file) {
        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
    }
}
