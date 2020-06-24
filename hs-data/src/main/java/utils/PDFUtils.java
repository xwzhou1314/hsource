package utils;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.io.ByteStreams;
import com.google.common.io.Closer;
import com.google.common.io.Files;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class PDFUtils {
    public static String split(String url){
        File file = null;
        File freeFile = null;
        PdfCopy copy = null;
        Document dd = new Document();

        try {
            file = download(url);
            String fileName = file.getName();
            String str[] = fileName.split("\\.");
            String suffix = str[1];
            fileName = Joiner.on(".").join(str[0]+"_free", suffix);
            freeFile = new File(file.getPath()+fileName);
            FileOutputStream out = new FileOutputStream(freeFile);
            PdfReader reader = new PdfReader(new FileInputStream(file));
            PdfReader.unethicalreading = true;
            int freePage = reader.getNumberOfPages()/10;

            if (freePage<=0){
                freePage = 1;
            }

            copy = new PdfCopy(dd,out);

            dd.open();
            for (int i = 0; i < freePage; i++) {
                dd.newPage();
                PdfImportedPage pdfImportedPage= copy.getImportedPage(reader, i+1);
                copy.addPage(pdfImportedPage);
            }
            dd.close();
            copy.close();
            OssService osService = SpringBeanUtil.getBean(OssService.class);
            return osService.getFileUrlRes(freeFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (file != null){
                file.deleteOnExit();
            }
            if (freeFile != null){
                freeFile.deleteOnExit();
            }
            if (dd.isOpen()) {
                dd.close();
            }
            if (copy!=null) {
                copy.close();
            }
        }

        return null;
    }

    public static File download(String url){
        return download("./download",url);
    }

    public static void main(String[] args) {
        split("https://www.apple.com/environment/pdf/products/iphone/iPhone_11_Pro_PER_sept2019.pdf");
    }

    public static File download(String parentPath, String url) {
        if(Strings.isNullOrEmpty(url) || Strings.isNullOrEmpty(parentPath)) {
            return null;
        }
        if(url.length() > 500) {
            return null;
        }
        Closer closer = Closer.create();
        try {
            File imageDir = new File(parentPath);
            if(!imageDir.exists()) {
                imageDir.mkdirs();
            }
            String fileName =  org.apache.commons.lang3.StringUtils.substringBefore(url, "?");
            fileName = org.apache.commons.lang3.StringUtils.substringAfterLast(fileName, "/");
            File rtnFile = new File(imageDir, fileName);
            InputStream in = closer.register(new URL(url).openStream());
            Files.write(ByteStreams.toByteArray(in), rtnFile);
            return rtnFile;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                closer.close();
            } catch (IOException e) {
                closer = null;
            }
        }
    }
    /**
     * 下载图片到指定目录
     *
     * @param parentPath 指定目录
     * @param fileName 图片名称
     * @param in 输入流
     * @return 下载文件地址
     */
    public static String download(String parentPath, String fileName, InputStream in) {
        Closer closer = Closer.create();
        try {
            File imageDir = new File(parentPath);
            if(!imageDir.exists()) {
                imageDir.mkdirs();
            }
            File imageFile = new File(imageDir, fileName);
            Files.write(ByteStreams.toByteArray(in), imageFile);
            return imageFile.getAbsolutePath();
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                closer.close();
            } catch (IOException e) {
                closer = null;
            }
        }
    }
}
