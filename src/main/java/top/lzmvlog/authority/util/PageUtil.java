package top.lzmvlog.authority.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ShaoJie
 * @Date 2020年07月03 14:33
 * @Description: mybatis - plus 的分页工具
 * <p>
 * 当如果没有页码和每页显示的数量
 * 默认分页为第一个页显示10条数据
 */
@Data
@AllArgsConstructor
public class PageUtil {

    private Integer page = 1;

    private Integer pageNum = 10;

    public Integer getPage() {
        if ("".equals(page) || page == null) {
            return 1;
        }
        return page;
    }

    public Integer getPageNum() {
        if ("".equals(pageNum) || pageNum == null) {
            return 10;
        }
        return pageNum;
    }
}
