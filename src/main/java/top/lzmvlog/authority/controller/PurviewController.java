package top.lzmvlog.authority.controller;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lzmvlog.authority.model.Purview;
import top.lzmvlog.authority.service.PurviewService;
import top.lzmvlog.authority.util.PageUtil;
import top.lzmvlog.authority.util.data.Response;

/**
 * @author ShaoJie
 * @Date 2020年07月30 11:43
 * @Description: 权限管理
 */
@RestController
@RequestMapping("purview")
public class PurviewController {

    @Autowired
    PurviewService purviewService;

    /**
     * 保存权限信息
     *
     * @param purview 权限对象
     * @return
     */
    @PutMapping("save")
    public Response save(@NotNull Purview purview) {
        purviewService.insert(purview);
        return new Response(HttpStatus.HTTP_OK, "添加成功");
    }

    /**
     * 查询权限集合
     *
     * @param purview  权限对象
     * @param pageUtil 分页对象
     * @return
     */
    @PostMapping("selectList")
    public Response selectList(@NotNull Purview purview, PageUtil pageUtil) {
        return new Response(HttpStatus.HTTP_OK, purviewService.selectList(new Page<>(pageUtil.getPage(),
                pageUtil.getPageNum()), purview));
    }

}
