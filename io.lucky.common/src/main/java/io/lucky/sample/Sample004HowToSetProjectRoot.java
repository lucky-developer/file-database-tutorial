package io.lucky.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 서버는 아래와 같은 방식으로 배포가 됩니다.
 * java -cp {jarFileAbsolutePath} -D{systemProperty} {mainClass}
 *
 * 아래와 같이 파일의 경로를 지정하는 경우, jar 파일이 실행되는 위치에 따라서 파일의 경로가 달라집니다.
 * File file = new File("sample.txt");
 *
 * 어떤 경로에서 jar가 실행되더라도 항상 같은 디렉토리에 파일들이 저장될 수 있도록 default 값을 지정해주어야 합니다.
 *
 * PROCESS_ROOT : 프로세스 전체에서 사용할 루트 경로 (Required)
 * LUCKY_DB_ROOT : 프로세스에서 생성되는 파일을 저장할 루트 경로 (Optional, default {PROCESS_ROOT}/luckybase)
 */
public class Sample004HowToSetProjectRoot {

    private static final String PROCESS_ROOT;
    private static final String LUCKY_DB_ROOT;

    static {
        PROCESS_ROOT = System.getProperty("lucky.server.home", "/Users/hwanseok/lucky-developer");
        LUCKY_DB_ROOT = System.getProperty("luckybase", PROCESS_ROOT + File.separator + "luckybase");

        try {
            File processRoot = new File(PROCESS_ROOT);
            File luckyRoot = new File(LUCKY_DB_ROOT);
            if (processRoot.exists() == false) {
                processRoot.mkdirs();
            }
            if (luckyRoot.exists() == false) {
                luckyRoot.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File file = new File(LUCKY_DB_ROOT, Sample004HowToSetProjectRoot.class.getName() + ".txt");
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
