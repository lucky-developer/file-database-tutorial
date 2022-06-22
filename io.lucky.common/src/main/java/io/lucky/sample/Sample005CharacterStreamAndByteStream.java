package io.lucky.sample;

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
 * FileOutputStream는 raw bytes를 쓰기 위한 스트림입니다.
 * FileWriter는 문자열을 쓰기 위한 스트림입니다. 그래서 char와 String만 지원합니다.
 * 파일 데이터 베이스는 숫자, boolean 등 다양한 데이터를 저장해야하기 때문에 바이트 스트림을 사용합니다.
 * 문자열만 저장해야할 때는 FileWriter를 사용할 수도 있지만, 바이트로 변환 과정을 직접하지 못하는 단점이 있습니다.
 *
 * 바이트 스트림을 사용하면 2가지 장점이 있습니다.
 * 1. DISK IO 성능을 최대한으로 끌어올리기 위해서 바이트 배열을 최적화할 수 있습니다.
 * 2. 바이트 레벨에서의 암호화를 수행할 수 있습니다.
 *
 * // FileOutputStream
 * private native void write(int b, boolean append) throws IOException;
 *
 * // FileWriter
 * public void write(int c) throws IOException {
 *         char cbuf[] = new char[1];
 *         cbuf[0] = (char) c;
 *         write(cbuf, 0, 1);
 *     }
 */
public class Sample005CharacterStreamAndByteStream {

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
        char c = 'i';
        String s = "Hello World";

        File file = new File(luckyRoot, Sample005CharacterStreamAndByteStream.class.getName() + ".txt");
        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());

        System.out.println("\nwriteAndReadWithCharacterStream");
        FileWriter fileWriter = new FileWriter(file, false);
        FileReader fileReader = new FileReader(file);
        writeAndReadWithCharacterStream(c, s, fileWriter, fileReader);
        System.out.println("file.length() = " + file.length());

        System.out.println("\nwriteAndReadWithByteStream");
        FileOutputStream fos = new FileOutputStream(file, false);
        FileInputStream fis = new FileInputStream(file);
        writeAndReadWithByteStream(c, s, fos, fis);
        System.out.println("file.length() = " + file.length());
        file.delete();
    }

    private static void writeAndReadWithByteStream(char c, String s, FileOutputStream fos, FileInputStream fis) throws IOException {
        fos.write(c);
        fos.write(s.getBytes(StandardCharsets.UTF_8));
        fos.flush();
        System.out.println("write c = " + c);
        System.out.println("write s = " + s);
        char read = (char) fis.read();
        byte[] readStr = new byte[s.length()];
        fis.read(readStr);
        System.out.println("write c = " + read);
        System.out.println("write s = " + new String(readStr));

    }

    private static void writeAndReadWithCharacterStream(char c, String s, FileWriter fileWriter, FileReader fileReader) throws IOException {
        fileWriter.write(c);
        fileWriter.write(s);
        fileWriter.flush();
        System.out.println("write c = " + c);
        System.out.println("write s = " + s);
        char read = (char) fileReader.read();
        char[] cArr = new char[s.length()];
        fileReader.read(cArr);
        String readStr = new String(cArr);
        System.out.println("read c = " + read);
        System.out.println("read s = " + readStr);
    }
}
