package top.xpit.geth.mapper;

import java.util.List;
import top.xpit.geth.domain.MicroGoods;

/**
 * 商品信息Mapper接口
 * 
 * @author PTJ
 * @date 2023-04-02
 */
public interface MicroGoodsMapper 
{
    /**
     * 查询商品信息
     * 
     * @param id 商品信息主键
     * @return 商品信息
     */
    public MicroGoods selectMicroGoodsById(Long id);

    /**
     * 查询商品信息列表
     * 
     * @param microGoods 商品信息
     * @return 商品信息集合
     */
    public List<MicroGoods> selectMicroGoodsList(MicroGoods microGoods);

    /**
     * 新增商品信息
     * 
     * @param microGoods 商品信息
     * @return 结果
     */
    public int insertMicroGoods(MicroGoods microGoods);

    /**
     * 修改商品信息
     * 
     * @param microGoods 商品信息
     * @return 结果
     */
    public int updateMicroGoods(MicroGoods microGoods);

    /**
     * 删除商品信息
     * 
     * @param id 商品信息主键
     * @return 结果
     */
    public int deleteMicroGoodsById(Long id);

    /**
     * 批量删除商品信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMicroGoodsByIds(Long[] ids);
}
