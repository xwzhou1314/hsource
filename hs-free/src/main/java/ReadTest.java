import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * @author xwzhou
 * @version 1.0
 * @date 2020/6/2 17:23
 */
public class ReadTest {

    public static void main(String[] args) {
        try {
            InputStream fis = new FileInputStream(new File("G:\\test.txt"));
            InputStreamReader reader = new InputStreamReader(fis, "GBK");
            OutputStream outputStream = new FileOutputStream(new File("G:\\test2.txt"));
            try {
                char[] b = new char[12];
                int len = 0;
                while ((len = reader.read(b)) != -1) {
                    String ss = new String(b, 0, len);
                    outputStream.write(ss.getBytes());
                    outputStream.flush();
                    System.out.println(ss);
                }


            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                fis.close();
                reader.close();
                outputStream.close();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
