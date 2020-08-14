package top.lzmvlog.authority.util;

import lombok.experimental.UtilityClass;

/**
 * @author ShaoJie
 * @Date 2020年07月16 15:16
 * @Description: 功能类
 */
@UtilityClass
public class Utils {

    /**
     * 取文件后缀
     *
     * @param resourceName 文件名称
     * @return String 文件后缀名称
     */
    public String getSuffix(String resourceName) {
        int index = resourceName.lastIndexOf('.');
        return resourceName.substring(index + 1);
    }

    /**
     * 判断字符是否为空
     * 适用类型判断： String
     *
     * @param charSequence 判断的类型值
     * @return boolean 字符是否为空
     */
    public boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0 || charSequence.toString().trim().length() == 0;
    }

}
