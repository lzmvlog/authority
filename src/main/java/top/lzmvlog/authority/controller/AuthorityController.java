package top.lzmvlog.authority.controller;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lzmvlog.authority.model.Authority;
import top.lzmvlog.authority.service.AuthorityService;
import top.lzmvlog.authority.util.PageUtil;
import top.lzmvlog.authority.util.data.Response;

/**
 * @author ShaoJie
 * @Date 2020年07月30 17:50
 * @Description: 用户权限管理
 */
@RestController
@RequestMapping("authority")
public class AuthorityController {

    @Autowired
    AuthorityService authorityService;

    /**
     * 新增用户权限
     *
     * @param authority 用户权限对象
     * @return
     */
    @PutMapping("save")
    public Response save(Authority authority) {
        authorityService.save(authority);
        return new Response(HttpStatus.HTTP_OK, "新增成功");
    }

    /**
     * 根据用户信息查询用户权限
     *
     * @param authority 权限对象
     * @param pageUtil  分页信息
     * @return
     */
    @GetMapping("selectList")
    public Response selectList(Authority authority, PageUtil pageUtil) {
        return new Response(HttpStatus.HTTP_OK, authorityService.selectListByMemberId(new Page<>(pageUtil.getPage(), pageUtil.getPageNum()), authority));
    }

}
