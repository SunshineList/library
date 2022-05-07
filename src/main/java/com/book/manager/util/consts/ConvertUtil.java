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
}
