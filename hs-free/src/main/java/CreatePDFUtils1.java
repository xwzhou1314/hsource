/**
 * @author xwzhou
 * @version 1.0
 * @date 2020/6/18 10:32
 */

import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class CreatePDFUtils1 {

    /**
     *新建以下两个方法,创建表格内的字体和样式的方法
     * @param str 内容
     * @param font 字体对象
     * @param high 表格高度
     * @Param alignCenter 是否水平居中
     * @Param alignMidde  是否垂直居中
     * @return
     */
    private static PdfPCell  mircoSoftFont(String str,Font font,int high,boolean alignCenter,boolean alignMidde){
        PdfPCell pdfPCell  = new PdfPCell(new Phrase(str,font));
        pdfPCell.setMinimumHeight(high);
        pdfPCell.setUseAscender(true); // 设置可以居中
        if (alignCenter){
            pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER); // 设置水平居中
        }
        if (alignMidde){
            pdfPCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE); // 设置垂直居中
        }
        return pdfPCell;
    }

    /**
     *
     * @param str 字符串
     * @param font 字体
     * @param high 表格高度
     * @Param alignCenter 是否水平居中
     * @Param alignMidde  是否垂直居中
     * @Param haveColor 是否有背景色(灰色)
     * @return
     */
    private static PdfPCell  mircoSoftFont(String str,Font font,int high,boolean alignCenter,boolean alignMidde,boolean haveColor){
        PdfPCell pdfPCell  = new PdfPCell(new Phrase(str,font));
        pdfPCell.setMinimumHeight(high);
        pdfPCell.setUseAscender(true); // 设置可以居中
        if (alignCenter){
            pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER); // 设置水平居中
        }
        if (alignMidde){
            pdfPCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE); // 设置垂直居中
        }
        if (haveColor){
            //颜色代码 RGB
            pdfPCell.setBackgroundColor(new BaseColor(217,217,217));
        }
        return pdfPCell;
    }



    public static void createHardwarePDF( String outputPath)throws Exception{
        //新建文档对象，页大小为A4纸，然后设置4个边距
        Document document = new Document(PageSize.A4,20,20,30,30);
        PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(outputPath));
        document.open();
        //创建字体
        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        //字体对象
        Font size14font = new Font(baseFont,14,Font.NORMAL);  //大小为14的正常字体
        Font size10font = new Font(baseFont,10,Font.BOLD); //大小为10的粗体


        //添加标题
        PdfPTable tableName = new PdfPTable(1);
        tableName.setWidthPercentage(90);  //设置标题长度占纸张比例
        tableName.addCell(mircoSoftFont("个人信息",size14font,50,true,true));
        document.add(tableName);
        //添加第二行的数据
        PdfPTable secondRowTable = new PdfPTable(3); //三列的意思
        secondRowTable.setWidthPercentage(90);
        //这里的数组长度是上面创建的列数,数组的总和为1,就是按比例划分的意思
        secondRowTable.setTotalWidth(new float[]{0.18f,0.32f,0.5f});
        secondRowTable.addCell(mircoSoftFont(" 姓名: ",size10font,50,false,true));
        secondRowTable.addCell(mircoSoftFont("李晓明",size10font,50,false,true));
        secondRowTable.addCell(mircoSoftFont(" 出生日期: 1994年3月14日",size10font,50,false,true));
        document.add(secondRowTable);
        //第三行数据
        PdfPTable thirdRowTable = new PdfPTable(3);
        thirdRowTable.setWidthPercentage(90);
        thirdRowTable.setTotalWidth(new float[]{0.18f,0.32f,0.5f});
        thirdRowTable.addCell(mircoSoftFont(" 名族:",size10font,50,false,true));
        thirdRowTable.addCell(mircoSoftFont("汉族",size10font,50,false,true));
        thirdRowTable.addCell(mircoSoftFont(" 联系电话: 13888880000",size10font,50,false,true));
        document.add(thirdRowTable);
        //第四行数据
        PdfPTable fourthRowTable = new PdfPTable(2);
        fourthRowTable.setWidthPercentage(90);
        fourthRowTable.setTotalWidth(new float[]{0.66f,0.34f});
        fourthRowTable.addCell(mircoSoftFont(" 个人描述 :",size10font,175,false,false));
        fourthRowTable.addCell(mircoSoftFont("个人特长 :",size10font,175,false,false));
        document.add(fourthRowTable);
        //第五行
        PdfPTable fifthDetailName = new PdfPTable(1);
        fifthDetailName.setWidthPercentage(90);
        fifthDetailName.addCell(mircoSoftFont("获奖记录 :",size14font,50,true,true));
        document.add(fifthDetailName);
        //第六行
        PdfPTable sisthRowTalbe= new PdfPTable(1);
        sisthRowTalbe.setWidthPercentage(90);
        sisthRowTalbe.addCell(mircoSoftFont(" 联系地址: "+"广东省广州市天河区XXXXXXXXXXXXXXXXXX",size10font,50,false,true));
        document.add(sisthRowTalbe);
        PdfPTable seventhRowTalbe = new PdfPTable(1);
        seventhRowTalbe.setWidthPercentage(90);
        seventhRowTalbe.addCell(mircoSoftFont(" 毕业院校 ",size14font,60,true,true));document.add(seventhRowTalbe);
        //第八行
        PdfPTable eiththRowTalbe = new PdfPTable(3);
        eiththRowTalbe.setWidthPercentage(90);
        eiththRowTalbe.setTotalWidth(new float[]{0.3f,0.5f,0.2f});
        eiththRowTalbe.addCell(mircoSoftFont(" 毕业学校",size10font,50,true,true,true));
        eiththRowTalbe.addCell(mircoSoftFont(" 就读日期",size10font,50,true,true,true));
        eiththRowTalbe.addCell(mircoSoftFont(" 联系人",size10font,50,true,true,true));
        document.add(eiththRowTalbe);
        //接下来加List
        String school = "XXX学校";
        String time = "201909  -  2022-06";
        String name = "陈某";
        for (int i = 0 ;i<4 ;i++){
            PdfPTable tempTable = new PdfPTable(3);
            tempTable.setWidthPercentage(90);
            tempTable.setTotalWidth(new float[]{0.3f,0.5f,0.2f});
            tempTable.addCell(mircoSoftFont(school,size10font,50,true,true));
            tempTable.addCell(mircoSoftFont(time,size10font,50,true,true));
            tempTable.addCell(mircoSoftFont(name,size10font,50,true,true));
            document.add(tempTable);
        }
        document.close();
        writer.close();
    }


    //使用例子
    public static void main(String[] args)throws Exception{
        createHardwarePDF("test.pdf");
    }

}