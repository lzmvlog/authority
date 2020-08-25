package top.lzmvlog.authority.controller;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.lzmvlog.authority.model.Resource;
import top.lzmvlog.authority.service.ResourceService;
import top.lzmvlog.authority.upload.QiUpload;
import top.lzmvlog.authority.util.data.R;

/**
 * @author ShaoJie
 * @Date 2020年08月07 14:19
 * @Description: 文件访问控制器
 */
@RestController
@RequestMapping("resource")
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    @Autowired
    QiUpload qiUpload;

    /**
     * 文件上传
     *
     * @param multipartFile 文件
     * @return resource 文件信息
     */
    @GetMapping("save")
    public R save(@RequestParam("file") MultipartFile multipartFile) {
        return new R(HttpStatus.HTTP_OK, qiUpload.upload(multipartFile));
    }

    /**
     * 查询文件
     *
     * @param page     分页信息
     * @param resource 文件信息
     * @return list 分页的文件集合
     */
    @GetMapping("selectList")
    public R selectList(Page page, Resource resource) {
        return new R(HttpStatus.HTTP_OK, resourceService.selectList(page, resource));
    }

    /**
     * 删除文件
     *
     * @param resource 删除文件
     * @return
     */
    @GetMapping("delete")
    public R delete(Resource resource) {
        resourceService.delete(resource);
        return new R(HttpStatus.HTTP_OK, "删除成功");
    }

    /**
     * 更新文件信息
     *
     * @param resource 文件信息
     * @return
     */
    @PostMapping("update")
    public R update(Resource resource) {
        resourceService.update(resource);
        return new R(HttpStatus.HTTP_OK, "更新成功");
    }

}
