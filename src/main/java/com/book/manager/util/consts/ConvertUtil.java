package com.book.manager.util.consts;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description 数字类型 和 中文名称互转
 * @Date 2020/7/28 13:48
 * @Author by Tuple
 */
public class ConvertUtil {

    /**
     * 获取身份信息
     *
     * @param type 身份类型
     * @return
     */
    public static String identStr(int type) {
        String result = null;
        if (type == Constants.STUDENT) {
            result = Constants.STU_STR;
        } else if (type == Constants.TEACHER) {
            result = Constants.TEA_STR;
        } else if (type == Constants.ADMIN) {
            result = Constants.ADMIN_STR;
        } else if (type == Constants.OTHER) {
            result = Constants.OTHER_STR;
        } else if (type == Constants.PURCHASE) {
            result = Constants.PURCHASE_STR;
        }

        return result;
    }


    /**
     * 采购状态
     */
    public static String statusPurchase(String status) {
        String result = "";
        if (status.equals("0")) {
            result = "审核通过";
        } else if (status.equals("1")) {
            result = "审核不通过";
        } else if (status.equals("2")) {
            result = "待审核";
        }
        return result;
    }


    /**
     * 书籍类型
     */

    public static String bookType(String type){
        String result = "";

        switch (type){
            case "1":
                result = "马克思主义、列宁主义、毛泽东思想、邓小平理论";
                break;
            case "2":
                result = "哲学、宗教";
                break;
            case "3":
                result = "文化、科学、教育、体育";
                break;
            case "4":
                result = "政治、法律";
                break;
            case "5":
                result = "历史、地理";
        }
        return result;
    }


    public static int getDistanceDays(String str1, String str2){
        DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);
        Date one;
        Date two;
        int days=0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            days = (int) (diff / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }


    /**
     * 指定日期加上天数后的日期
     * @param num 为增加的天数
     * @param newDate 创建时间
     * @return
     * @throws ParseException
     */
    public static String plusDay(int num,String newDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date  currdate = format.parse(newDate);
        System.out.println("现在的日期是：" + currdate);
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        currdate = ca.getTime();
        String enddate = format.format(currdate);
        System.out.println("增加天数以后的日期：" + enddate);
        return enddate;
    }


    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}