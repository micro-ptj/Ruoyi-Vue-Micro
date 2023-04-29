package top.xpit.geth.service;

import java.util.List;
import top.xpit.geth.domain.MicroUserAddress;

/**
 * 地址管理Service接口
 * 
 * @author PTJ
 * @date 2023-04-29
 */
public interface IMicroUserAddressService 
{
    /**
     * 查询地址管理
     * 
     * @param id 地址管理主键
     * @return 地址管理
     */
    public MicroUserAddress selectMicroUserAddressById(Long id);

    /**
     * 查询地址管理列表
     * 
     * @param microUserAddress 地址管理
     * @return 地址管理集合
     */
    public List<MicroUserAddress> selectMicroUserAddressList(MicroUserAddress microUserAddress);

    /**
     * 新增地址管理
     * 
     * @param microUserAddress 地址管理
     * @return 结果
     */
    public int insertMicroUserAddress(MicroUserAddress microUserAddress);

    /**
     * 修改地址管理
     * 
     * @param microUserAddress 地址管理
     * @return 结果
     */
    public int updateMicroUserAddress(MicroUserAddress microUserAddress);

    /**
     * 批量删除地址管理
     * 
     * @param ids 需要删除的地址管理主键集合
     * @return 结果
     */
    public int deleteMicroUserAddressByIds(Long[] ids);

    /**
     * 删除地址管理信息
     * 
     * @param id 地址管理主键
     * @return 结果
     */
    public int deleteMicroUserAddressById(Long id);
}
