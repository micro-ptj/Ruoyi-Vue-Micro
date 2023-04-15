package top.xpit.geth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.xpit.geth.domain.MicroUserInfo;

/**
 * 用户信息Mapper接口
 * 
 * @author PTJ
 * @date 2023-03-19
 */
@Mapper
public interface MicroUserInfoMapper 
{
    /**
     * 查询用户信息
     * 
     * @param id 用户信息主键
     * @return 用户信息
     */
    public MicroUserInfo selectMicroUserInfoById(Long id);

    /**
     * 查询用户信息列表
     * 
     * @param microUserInfo 用户信息
     * @return 用户信息集合
     */
    public List<MicroUserInfo> selectMicroUserInfoList(MicroUserInfo microUserInfo);

    /**
     * 新增用户信息
     * 
     * @param microUserInfo 用户信息
     * @return 结果
     */
    public int insertMicroUserInfo(MicroUserInfo microUserInfo);

    /**
     * 修改用户信息
     * 
     * @param microUserInfo 用户信息
     * @return 结果
     */
    public int updateMicroUserInfo(MicroUserInfo microUserInfo);

    /**
     * 删除用户信息
     * 
     * @param id 用户信息主键
     * @return 结果
     */
    public int deleteMicroUserInfoById(Long id);

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMicroUserInfoByIds(Long[] ids);

    MicroUserInfo selectByUserId(@Param("userId") Long userId);

    String selectUserInfoByUserIdToPrivateKey(Long appUserId);

    String selectPrivateKeyByUserId(Long appUserId);
}
