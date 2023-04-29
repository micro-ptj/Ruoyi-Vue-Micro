package top.xpit.geth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import top.xpit.common.annotation.Excel;
import top.xpit.common.core.domain.BaseEntity;

/**
 * 地址管理对象 micro_user_address
 * 
 * @author PTJ
 * @date 2023-04-29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MicroUserAddress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String phone;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 是否默认 */
    @Excel(name = "是否默认")
    private Long isDefault;

    /** $column.columnComment */
    private Long delFlag;

}
