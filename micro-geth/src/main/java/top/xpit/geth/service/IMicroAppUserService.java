package top.xpit.geth.service;

import java.util.List;

import top.xpit.geth.domain.MicroAppUser;
import top.xpit.geth.domain.vo.UserInfoVo;

/**
 * 用户管理Service接口
 * 
 * @author PTJ
 * @date 2023-03-12
 */
public interface IMicroAppUserService 
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
     * 批量删除用户管理
     * 
     * @param ids 需要删除的用户管理主键集合
     * @return 结果
     */
    public int deleteMicroAppUserByIds(Long[] ids);

    /**
     * 删除用户管理信息
     * 
     * @param id 用户管理主键
     * @return 结果
     */
    public int deleteMicroAppUserById(Long id);

    UserInfoVo selectUserInfo();
}
