package edu.cdtu.utils;

/**
 * 该类用于保存登录用户的信息
 */
public class UserSaveTool
{
    private static String currentLoginUsername = null;//用户名
    private static String currentLoginAccountNumber = null;//用户卡号
    private static String currentLoginAccountPass = null;//卡密码
    private static String currentLoginMoney = null;//卡内余额

    public static String getCurrentLoginAccountNumber() {
        return currentLoginAccountNumber;
    }

    public static void setCurrentLoginAccountNumber(String currentLoginAccountNumber) {
        UserSaveTool.currentLoginAccountNumber = currentLoginAccountNumber;
    }

    public static String getCurrentLoginUsername() {
        return currentLoginUsername;
    }

    public static void setCurrentLoginUsername(String currentLoginUsername) {
        UserSaveTool.currentLoginUsername = currentLoginUsername;
    }

    public static String getCurrentLoginAccountPass() {
        return currentLoginAccountPass;
    }

    public static void setCurrentLoginAccountPass(String currentLoginAccountPass) {
        UserSaveTool.currentLoginAccountPass = currentLoginAccountPass;
    }

    public static String getCurrentLoginMoney() {
        return currentLoginMoney;
    }

    public static void setCurrentLoginMoney(String currentLoginMoney) {
        UserSaveTool.currentLoginMoney = currentLoginMoney;
    }
}
