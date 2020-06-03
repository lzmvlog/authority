package top.lzmvlog.authority.util.date;

import java.util.Calendar;
import java.util.Date;

/**
 * @author ShaoJie
 * @Date 2020年06月03 10:27
 * @Description: 获取时间的工具类
 */
public class DateUtil {

    /**
     * 获取当前时间的之后的一个小时的时间
     *
     * @return Date 之后的一个小时
     */
    public Date getNowDateOneTime() {
        Calendar calendar = Calendar.getInstance();
        // HOUR_OF_DAY 指示一天中的小时
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 1);
        return cn.hutool.core.date.DateUtil.date(calendar.getTime());
    }
}
