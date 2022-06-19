package io.lucky.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Sample002WriteAndReadString {

    public static void main(String[] args) {
        File file = new File(Sample002WriteAndReadString.class.getName() + ".txt");
        try (
                FileOutputStream fos = new FileOutputStream(file);
                FileInputStream fis = new FileInputStream(file);
        ) {
            printFilePath(file);
            writeInteger(fos, 0, 3);
            readInteger(fis, 3);
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

    private static void writeInteger(FileOutputStream fos, int start, int size) throws IOException {
        for(int i=0; i< size; i++){
            int a = start + i;
            System.out.println("fos.write() = " + a);
            fos.write(a);
        }
        fos.flush();
    }

    private static void readInteger(FileInputStream fis, int size) throws IOException {
        for(int i=0; i< size; i++){
            System.out.println("fis.read() = " + fis.read());
        }
    }

    private static void printFilePath(File file) {
        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
    }
}
