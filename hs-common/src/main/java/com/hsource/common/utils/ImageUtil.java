package com.hsource.common.utils;


import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ImageUtil {

    private static final String BASE64_REGEX = "data:image.*?;base64,";

    public static final String WEBP = "webp";
    public static final String WEBP_SUFFIX = ".webp";

    private static final int VERIFY_CODE_IMAGE_WIDTH = 80;

    private static final int VERIFY_CODE_IMAGE_HEIGHT = 25;
    /**
     * 读取文件类型
     * @param buf
     * @return
     * @throws IOException
     */
    public static String readType(byte[] buf) throws IOException {
        if(isJPEGHeader(buf))
        {
            return "jpeg";
        }
        if(isPNG(buf))
        {
            return "png";
        }
        if(isGIF(buf)){

            return "gif";
        }
        if(isWEBP(buf))
        {
            return "webp";
        }
        if(isBMP(buf))
        {
            return "bmp";
        }
        if(isICON(buf))
        {
            return "ico";
        }
        throw new IOException("the image's format is unkown!");
    }

    private static boolean isBMP(byte[] buf){
        byte[] markBuf = "BM".getBytes();  //BMP图片文件的前两个字节
        return compare(buf, markBuf);
    }

    private static boolean isICON(byte[] buf) {
        byte[] markBuf = {0, 0, 1, 0, 1, 0, 32, 32};
        return compare(buf, markBuf);
    }
    private static boolean isWEBP(byte[] buf) {
        byte[] markBuf = "RIFF".getBytes(); //WebP图片识别符
        return compare(buf, markBuf);
    }

    private static boolean isGIF(byte[] buf) {

        byte[] markBuf = "GIF89a".getBytes(); //GIF识别符
        if(compare(buf, markBuf))
        {
            return true;
        }
        markBuf = "GIF87a".getBytes(); //GIF识别符
        if(compare(buf, markBuf))
        {
            return true;
        }
        return false;
    }


    private static boolean isPNG(byte[] buf) {

        byte[] markBuf = {(byte) 0x89,0x50,0x4E,0x47,0x0D,0x0A,0x1A,0x0A}; //PNG识别符
        // new String(buf).indexOf("PNG")>0 //也可以使用这种方式
        return compare(buf, markBuf);
    }

    private static boolean isJPEGHeader(byte[] buf) {
        byte[] markBuf = {(byte) 0xff, (byte) 0xd8}; //JPEG开始符

        return compare(buf, markBuf);
    }

    /**
     * 标示一致性比较
     * @param buf  待检测标示
     * @param markBuf 标识符字节数组
     * @return 返回false标示标示不匹配
     */
    private static boolean compare(byte[] buf, byte[] markBuf) {
        for (int i = 0; i < markBuf.length; i++) {
            byte b = markBuf[i];
            byte a = buf[i];

            if(a!=b){
                return false;
            }
        }
        return true;
    }
//    /**
//     * 解码base64图片数据
//     *
//     * @param base64Data
//     * @return
//     */
//    public static byte[] decode(String base64Data) {
//        if (StringUtils.isEmpty(base64Data)) {
//            return null;
//        }
//        base64Data = StringUtils.replaceAll(base64Data.trim(), BASE64_REGEX, "");
//
//        Base64 base64 = new Base64();
//        byte[] imgData = base64.decode(base64Data);
//        for (int i = 0; i < imgData.length; i++) {
//            if (imgData[i] < 0) {
//                imgData[i] += 256;
//            }
//        }
//        return imgData;
//    }
//
//    /**
//     * 图片转webp格式
//     *
//     * @param inputJpgPath
//     * @return
//     */
//    public static String toWebpStream(String inputJpgPath)  {
//        String outputWebpPath=inputJpgPath+WEBP_SUFFIX;
//        BufferedImage image = null;
//        try {
//            image = ImageIO.read(new File(inputJpgPath));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ImageWriter writer = ImageIO.getImageWritersByMIMEType("image/webp").next();
//        WebPWriteParam writeParam = new WebPWriteParam(writer.getLocale());
//        writeParam.setCompressionMode(WebPWriteParam.MODE_DEFAULT);
//        try {
//            writer.setOutput(new FileImageOutputStream(new File(outputWebpPath)));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            writer.write(null, new IIOImage(image, null, null), writeParam);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return outputWebpPath;
//    }
    public static byte[] downloadImage(String imageUrl) throws IOException {
        // 下载图片
        URL url = new URL(imageUrl);
        HttpURLConnection conn = null;
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        conn.setConnectTimeout(5 * 1000);
        InputStream inputStream = conn.getInputStream();

        // 缓存图片
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) > -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        byteArrayOutputStream.flush();
        byte[] imageBytes = byteArrayOutputStream.toByteArray();


        return imageBytes;
    }

    /**
     * 生成验证码图片
     *
     * @param verifyCode 验证码
     * @return
     */
    public static BufferedImage generateVerifyCodeImage(String verifyCode) {
        BufferedImage image = new BufferedImage(VERIFY_CODE_IMAGE_WIDTH, VERIFY_CODE_IMAGE_HEIGHT,
                BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.fillRect(0, 0, VERIFY_CODE_IMAGE_WIDTH, VERIFY_CODE_IMAGE_HEIGHT);

        // 绘制干扰线
        drawLine(g);

        // 绘制随机字符
        drawVerifyCode(g, verifyCode);

        g.dispose();
        return image;
    }


    /**
     * 绘制干扰线
     */
    private static void drawLine(Graphics g) {
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        g.setColor(getRandColor(110, 133));
        for (int i = 0; i <= 10; i++) {
            int x = getRandom(VERIFY_CODE_IMAGE_WIDTH);
            int y = getRandom(VERIFY_CODE_IMAGE_HEIGHT);
            int xl = getRandom(13);
            int yl = getRandom(15);
            g.drawLine(x, y, x + xl, y + yl);
        }
    }

    /**
     * 绘制字符串
     */
    private static void drawVerifyCode(Graphics g, String verifyCode) {
        g.setFont(new Font("黑体", Font.BOLD + Font.ITALIC, 22));
        for (int i = 0; i < verifyCode.length(); i++) {
            String current = String.valueOf(verifyCode.charAt(i));
            g.setColor(new Color(getRandom(101), getRandom(111), getRandom(121)));
            g.translate(getRandom(3), getRandom(3));
            g.drawString(current, 13 * (i + 1), 16);
        }

    }

    private static Color getRandColor(int fc, int bc) {
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int red = fc + getRandom(bc - fc - 16);
        int green = fc + getRandom(bc - fc - 14);
        int blue = fc + getRandom(bc - fc - 18);
        return new Color(red, green, blue);
    }

    private static Integer getRandom(Integer seed) {
        if (seed == null) {
            return 0;
        } else {
            return ThreadLocalRandom.current().nextInt(seed);
        }
    }

    /**
     * 域名正则表达式
     */
    private static final String DOMAIN_REGEX = "^((http://)|(https://))?([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}(/)";

    /**
     * 域名pattern
     */
    private static final Pattern DOMAIN_PATTERN = Pattern.compile(DOMAIN_REGEX);

    private static final String OBLIQUE_LINE = "\\/";
    /**
     * 获取图片绝对路径
     *
     * @param source
     * @return
     */
    public static String queryAbsolutePath(String source,String host) {
        if (StringUtils.isBlank(source) || StringUtils.isBlank(host)) {
            return source;
        }

        Matcher matcher = DOMAIN_PATTERN.matcher(source);
        if (matcher.find()) {
            return source;
        } else if (source.startsWith(OBLIQUE_LINE)) {
            return host + source.substring(1, source.length());
        } else {
            return host + source;
        }
    }
}
