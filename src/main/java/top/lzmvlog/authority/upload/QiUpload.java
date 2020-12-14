package top.lzmvlog.authority.upload;

import cn.hutool.core.util.IdUtil;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.lzmvlog.authority.model.Resource;
import top.lzmvlog.authority.service.ResourceService;
import top.lzmvlog.authority.util.Utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author ShaoJie
 * @Date 2020年08月07 10:30
 * @Description: 七牛云文件上传
 * <p>
 * 操作文件，将文件上传到七牛云
 */
@Component
@Slf4j
public class QiUpload {

    @Autowired
    ResourceService resourceService;

    @Value("${qiniuyun.access.key}")
    private String accessKey;

    @Value("${qiniuyun.secret.key}")
    private String secretKey;

    @Value("${qiniuyun.bucket.name}")
    private String bucket;

    @Value("${qiniuyun.domainName}")
    private String domainName;

    /**
     * 上传文件
     *
     * @param multipartFile
     * @return
     */
    public Resource upload(MultipartFile multipartFile) {
        // 构造一个带指定 Region 对象的配置类
        Configuration configuration = new Configuration(Region.region0());
        // 构建上传管理器
        UploadManager uploadManager = new UploadManager(configuration);
        Response response = null;
        String url = null;
        Resource resource = new Resource();
        try {
            if (multipartFile == null) {
                log.info("上传文件为空");
                throw new RuntimeException("上传文件为空");
            }

            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(multipartFile.getBytes());
            Auth auth = Auth.create(accessKey, secretKey);
            // 上传的 token
            String upToken = auth.uploadToken(bucket);

            try {
                response = uploadManager.put(byteInputStream, IdUtil.fastSimpleUUID(), upToken, null, multipartFile.getContentType());
                //解析上传成功的结果
                DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                log.info("上传文件名 {} - 上传的hash {}", defaultPutRet.key, defaultPutRet.hash);
                String suffix = Utils.getSuffix(multipartFile.getOriginalFilename());
                // 保存文件
                url = String.format("%s/%s.%s", domainName, defaultPutRet.key, suffix);
                resourceService.save(resource.setId(IdUtil.fastSimpleUUID()).setUrl(url).setName(multipartFile.getOriginalFilename()).setSuffix(suffix).setCreateDate(LocalDateTime.now()));
            } catch (QiniuException ex) {
                log.error("qiniuyun - 上传出错 - {}", ex);
                throw new RuntimeException("上传出错");
            }
        } catch (IOException e) {
            log.error("qiniuyun - 文件 multipartFile 转换 byte[] 失败 - {}", e);
            throw new RuntimeException("文件 multipartFile 转换 byte[] 失败");
        }
        return resource;
    }

}
