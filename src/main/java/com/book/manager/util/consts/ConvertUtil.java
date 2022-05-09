package com.book.manager.util.consts;

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
}
