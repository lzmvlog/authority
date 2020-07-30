package top.lzmvlog.authority.util;

/**
 * @author ShaoJie
 * @Date 2020年07月16 15:16
 * @Description: 功能类
 */
public class Utils {

    /**
     * 取文件后缀
     *
     * @param resourceName 文件名称
     * @return
     */
    public static String getSuffix(String resourceName) {
        int index = resourceName.lastIndexOf('.');
        return resourceName.substring(index + 1);
    }

    /**
     * 判断字符是否为空
     * 适用类型判断： String
     *
     * @param charSequence 判断的类型值
     * @return
     */
    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0 || charSequence.toString().trim().length() == 0;
    }

}
