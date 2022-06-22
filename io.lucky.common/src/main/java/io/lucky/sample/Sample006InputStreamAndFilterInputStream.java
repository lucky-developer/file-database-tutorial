package io.lucky.sample;

import io.lucky.common.util.StopWatch;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 입출력 장치는 매우 다양합니다. 입출력 장치과 무관하게 개발을 할 수 있도록 가상 통로인 스트림이 존재합니다.
 *
 * 스트림의 구분 1
 * - 입력 스트림 : *InputStream, *Reader
 * - 출력 스트림 : *OutputStream, *Writer
 *
 * 스트림의 구분 2
 * - 바이트 스트림 : *InputStream, *OutputStream
 * - 문자 스트림 : *Reader, *Writer
 *
 * 스트림의 구분 3
 * - 기반 스트림 : InputStream의 구현체 @See
 * - 보조 스트림 : FilterInputStream의 구현체
 *
 *
 */
public class Sample006InputStreamAndFilterInputStream {

    private static final String PROCESS_ROOT;
    private static final String LUCKY_DB_ROOT;
    private static final File processRoot;
    private static final File luckyRoot;

    static {
        PROCESS_ROOT = System.getProperty("lucky.server.home", "/Users/hwanseok/lucky-developer");
        LUCKY_DB_ROOT = System.getProperty("luckybase", PROCESS_ROOT + File.separator + "luckybase");
        processRoot = new File(PROCESS_ROOT);
        luckyRoot = new File(LUCKY_DB_ROOT);
        if (processRoot.exists() == false) {
            processRoot.mkdirs();
        }
        if (luckyRoot.exists() == false) {
            luckyRoot.mkdirs();
        }
    }

    public static void main(String[] args) throws IOException {
        String s = "Hello World";
        int time = 100_000;
        File file = new File(luckyRoot, Sample006InputStreamAndFilterInputStream.class.getName() + ".txt");
        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());

        StopWatch sw = new StopWatch();

        sw.start();
        FileOutputStream fos = new FileOutputStream(file, false);
        System.out.println("\nwriteWithByteStream");
        writeWithByteStream(s, fos, time);
        System.out.println("elapsed time = " + sw.stop() + "ms");

        sw.start();
        FileInputStream fis = new FileInputStream(file);
        System.out.println("\nreadWithByteStream");
        readWithByteStream(s, fis, time);
        System.out.println("elapsed time = " + sw.stop() + "ms");

        sw.start();
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        System.out.println("\nwriteWithByteStream");
        writeWithBufferedByteStream(s, bos, time);
        System.out.println("elapsed time = " + sw.stop() + "ms");

        sw.start();
        BufferedInputStream bis = new BufferedInputStream(fis);
        System.out.println("\nreadWithByteStream");
        readWithBufferedByteStream(s, bis, time);
        System.out.println("elapsed time = " + sw.stop() + "ms");

        file.delete();
    }

    private static void writeWithByteStream(String s, FileOutputStream fos, int times) throws IOException {
        for(int i=0; i< times; i++){
//            System.out.println("write s = " + s);
            byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
            fos.write(bytes.length);
            fos.write(bytes);
            fos.flush();
        }
    }

    private static void readWithByteStream(String s, FileInputStream fis, int times) throws IOException {
        for(int i=0; i< times; i++){
            int readLen = fis.read();
            byte[] readStr = new byte[readLen];
            fis.read(readStr);
//            System.out.println("read s = " + new String(readStr));
        }
    }

    private static void writeWithBufferedByteStream(String s, BufferedOutputStream bos, int times) throws IOException {
        for(int i=0; i< times; i++){
//            System.out.println("write s = " + s);
            byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
            bos.write(bytes.length);
            bos.write(bytes);
            bos.flush();
        }
    }

    private static void readWithBufferedByteStream(String s, BufferedInputStream bis, int times) throws IOException {
        for(int i=0; i< times; i++){
            int readLen = bis.read();
            byte[] readStr = new byte[readLen];
            bis.read(readStr);
//            System.out.println("read s = " + new String(readStr));
        }
    }

}
