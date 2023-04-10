package top.xpit.geth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import top.xpit.geth.domain.MicroAppUser;
import top.xpit.geth.domain.MicroUserInfo;
import top.xpit.geth.domain.vo.UserInfoVo;

/**
 * 用户管理Mapper接口
 * 
 * @author PTJ
 * @date 2023-03-12
 */
@Mapper
public interface MicroAppUserMapper 
{
    /**
     * 查询用户管理
     * 
     * @param id 用户管理主键
     * @return 用户管理
     */
    public MicroAppUser selectMicroAppUserById(Long id);

    /**
     * 查询用户管理列表
     * 
     * @param microAppUser 用户管理
     * @return 用户管理集合
     */
    public List<MicroAppUser> selectMicroAppUserList(MicroAppUser microAppUser);

    /**
     * 新增用户管理
     * 
     * @param microAppUser 用户管理
     * @return 结果
     */
    public int insertMicroAppUser(MicroAppUser microAppUser);

    /**
     * 修改用户管理
     * 
     * @param microAppUser 用户管理
     * @return 结果
     */
    public int updateMicroAppUser(MicroAppUser microAppUser);

    /**
     * 删除用户管理
     * 
     * @param id 用户管理主键
     * @return 结果
     */
    public int deleteMicroAppUserById(Long id);

    /**
     * 批量删除用户管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMicroAppUserByIds(Long[] ids);

    /**
     * 批量删除${subTable.functionName}
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMicroUserInfoByUserIds(Long[] ids);
    
    /**
     * 批量新增${subTable.functionName}
     * 
     * @param microUserInfoList ${subTable.functionName}列表
     * @return 结果
     */
    public int batchMicroUserInfo(List<MicroUserInfo> microUserInfoList);
    

    /**
     * 通过用户管理主键删除${subTable.functionName}信息
     * 
     * @param id 用户管理ID
     * @return 结果
     */
    public int deleteMicroUserInfoByUserId(Long id);

    MicroAppUser selectByPhone(Long phone);

    UserInfoVo selectUserInfo(Long appUserId);
}
