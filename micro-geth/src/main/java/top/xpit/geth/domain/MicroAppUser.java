package top.xpit.geth.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import top.xpit.common.annotation.Excel;
import top.xpit.common.core.domain.BaseEntity;

/**
 * 用户管理对象 micro_app_user
 * 
 * @author PTJ
 * @date 2023-03-12
 */
public class MicroAppUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户名 */
    @Excel(name = "用户名")
    private String username;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickname;

    /** 头像 */
    @Excel(name = "头像")
    private String avatar;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private Long phone;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 0激活 1未激活 */
    @Excel(name = "0激活 1未激活")
    private Long activate;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别 */
    @Excel(name = "性别")
    private Long sex;

    /** 身份证号码 */
    @Excel(name = "身份证号码")
    private String idCardNo;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** $column.columnComment */
    private Long delFlag;

    /** $table.subTable.functionName信息 */
    private List<MicroUserInfo> microUserInfoList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setPhone(Long phone) 
    {
        this.phone = phone;
    }

    public Long getPhone() 
    {
        return phone;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setActivate(Long activate) 
    {
        this.activate = activate;
    }

    public Long getActivate() 
    {
        return activate;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSex(Long sex) 
    {
        this.sex = sex;
    }

    public Long getSex() 
    {
        return sex;
    }
    public void setIdCardNo(String idCardNo) 
    {
        this.idCardNo = idCardNo;
    }

    public String getIdCardNo() 
    {
        return idCardNo;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setDelFlag(Long delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Long getDelFlag() 
    {
        return delFlag;
    }

    public List<MicroUserInfo> getMicroUserInfoList()
    {
        return microUserInfoList;
    }

    public void setMicroUserInfoList(List<MicroUserInfo> microUserInfoList)
    {
        this.microUserInfoList = microUserInfoList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("username", getUsername())
            .append("nickname", getNickname())
            .append("avatar", getAvatar())
            .append("phone", getPhone())
            .append("password", getPassword())
            .append("activate", getActivate())
            .append("name", getName())
            .append("sex", getSex())
            .append("idCardNo", getIdCardNo())
            .append("address", getAddress())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("delFlag", getDelFlag())
            .append("microUserInfoList", getMicroUserInfoList())
            .toString();
    }
}
