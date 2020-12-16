package top.lzmvlog.authority.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author ShaoJie
 * @Date 2020年08月07 11:21
 * @Description: 文件实体
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@TableName(value = "resource")
@NoArgsConstructor
public class Resource {

    /**
     * 文件id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 文件的名称
     */
    private String name;

    /**
     * 文件连接
     */
    private String url;

    /**
     * 文件后缀
     */
    @TableField("suffix")
    private String suffix;

    /**
     * 创建时间
     */
    @TableField("create_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

}
