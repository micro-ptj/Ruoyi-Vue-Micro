package top.xpit.geth.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.transaction.annotation.Transactional;
import top.xpit.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.xpit.geth.mapper.MicroUserAddressMapper;
import top.xpit.geth.domain.MicroUserAddress;
import top.xpit.geth.service.IMicroUserAddressService;

/**
 * 地址管理Service业务层处理
 * 
 * @author PTJ
 * @date 2023-04-29
 */
@Service
public class MicroUserAddressServiceImpl implements IMicroUserAddressService 
{
    @Autowired
    private MicroUserAddressMapper microUserAddressMapper;

    /**
     * 查询地址管理
     * 
     * @param id 地址管理主键
     * @return 地址管理
     */
    @Override
    public MicroUserAddress selectMicroUserAddressById(Long id)
    {
        return microUserAddressMapper.selectMicroUserAddressById(id);
    }

    /**
     * 查询地址管理列表
     * 
     * @param microUserAddress 地址管理
     * @return 地址管理
     */
    @Override
    public List<MicroUserAddress> selectMicroUserAddressList(MicroUserAddress microUserAddress)
    {
        return microUserAddressMapper.selectMicroUserAddressList(microUserAddress);
    }

    /**
     * 新增地址管理
     * 
     * @param microUserAddress 地址管理
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertMicroUserAddress(MicroUserAddress microUserAddress)
    {
        if ("1".equals(microUserAddress.getIsDefault())){
            MicroUserAddress address = microUserAddressMapper.selectByUserIdAndDefault(microUserAddress.getUserId());
            if (Objects.nonNull(address)){
                throw new RuntimeException("已经存在默认地址");
            }
        }
        microUserAddress.setCreateTime(DateUtils.getNowDate());
        return microUserAddressMapper.insertMicroUserAddress(microUserAddress);
    }

    /**
     * 修改地址管理
     * 
     * @param microUserAddress 地址管理
     * @return 结果
     */
    @Override
    public int updateMicroUserAddress(MicroUserAddress microUserAddress)
    {
        microUserAddress.setUpdateTime(DateUtils.getNowDate());
        return microUserAddressMapper.updateMicroUserAddress(microUserAddress);
    }

    /**
     * 批量删除地址管理
     * 
     * @param ids 需要删除的地址管理主键
     * @return 结果
     */
    @Override
    public int deleteMicroUserAddressByIds(Long[] ids)
    {
        return microUserAddressMapper.deleteMicroUserAddressByIds(ids);
    }

    /**
     * 删除地址管理信息
     * 
     * @param id 地址管理主键
     * @return 结果
     */
    @Override
    public int deleteMicroUserAddressById(Long id)
    {
        return microUserAddressMapper.deleteMicroUserAddressById(id);
    }
}
