package top.xpit.geth.service.impl;

import java.util.List;
import top.xpit.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.xpit.geth.mapper.MicroGoodsMapper;
import top.xpit.geth.domain.MicroGoods;
import top.xpit.geth.service.IMicroGoodsService;

/**
 * 商品信息Service业务层处理
 * 
 * @author PTJ
 * @date 2023-04-02
 */
@Service
public class MicroGoodsServiceImpl implements IMicroGoodsService 
{
    @Autowired
    private MicroGoodsMapper microGoodsMapper;

    /**
     * 查询商品信息
     * 
     * @param id 商品信息主键
     * @return 商品信息
     */
    @Override
    public MicroGoods selectMicroGoodsById(Long id)
    {
        return microGoodsMapper.selectMicroGoodsById(id);
    }

    /**
     * 查询商品信息列表
     * 
     * @param microGoods 商品信息
     * @return 商品信息
     */
    @Override
    public List<MicroGoods> selectMicroGoodsList(MicroGoods microGoods)
    {
        return microGoodsMapper.selectMicroGoodsList(microGoods);
    }

    /**
     * 新增商品信息
     * 
     * @param microGoods 商品信息
     * @return 结果
     */
    @Override
    public int insertMicroGoods(MicroGoods microGoods)
    {
        microGoods.setCreateTime(DateUtils.getNowDate());
        return microGoodsMapper.insertMicroGoods(microGoods);
    }

    /**
     * 修改商品信息
     * 
     * @param microGoods 商品信息
     * @return 结果
     */
    @Override
    public int updateMicroGoods(MicroGoods microGoods)
    {
        microGoods.setUpdateTime(DateUtils.getNowDate());
        return microGoodsMapper.updateMicroGoods(microGoods);
    }

    /**
     * 批量删除商品信息
     * 
     * @param ids 需要删除的商品信息主键
     * @return 结果
     */
    @Override
    public int deleteMicroGoodsByIds(Long[] ids)
    {
        return microGoodsMapper.deleteMicroGoodsByIds(ids);
    }

    /**
     * 删除商品信息信息
     * 
     * @param id 商品信息主键
     * @return 结果
     */
    @Override
    public int deleteMicroGoodsById(Long id)
    {
        return microGoodsMapper.deleteMicroGoodsById(id);
    }
}
