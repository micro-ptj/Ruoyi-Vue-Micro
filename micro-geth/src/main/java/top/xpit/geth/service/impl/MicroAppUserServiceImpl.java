package top.xpit.geth.service.impl;

import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson2.JSONObject;
import top.xpit.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

import top.xpit.common.utils.SecurityUtils;
import top.xpit.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import top.xpit.geth.domain.MicroUserInfo;
import top.xpit.geth.domain.query.AppIdentityVerifyParam;
import top.xpit.geth.domain.vo.UserInfoVo;
import top.xpit.geth.mapper.MicroAppUserMapper;
import top.xpit.geth.domain.MicroAppUser;
import top.xpit.geth.service.IMicroAppUserService;
import top.xpit.geth.util.VerifyIdentityUtil;

/**
 * 用户管理Service业务层处理
 * 
 * @author PTJ
 * @date 2023-03-12
 */
@Service
public class MicroAppUserServiceImpl implements IMicroAppUserService 
{
    @Autowired
    private MicroAppUserMapper microAppUserMapper;

    /**
     * 查询用户管理
     * 
     * @param id 用户管理主键
     * @return 用户管理
     */
    @Override
    public MicroAppUser selectMicroAppUserById(Long id)
    {
        return microAppUserMapper.selectMicroAppUserById(id);
    }

    /**
     * 查询用户管理列表
     * 
     * @param microAppUser 用户管理
     * @return 用户管理
     */
    @Override
    public List<MicroAppUser> selectMicroAppUserList(MicroAppUser microAppUser)
    {
        return microAppUserMapper.selectMicroAppUserList(microAppUser);
    }

    /**
     * 新增用户管理
     * 
     * @param microAppUser 用户管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertMicroAppUser(MicroAppUser microAppUser)
    {
        microAppUser.setCreateTime(DateUtils.getNowDate());
        int rows = microAppUserMapper.insertMicroAppUser(microAppUser);
        insertMicroUserInfo(microAppUser);
        return rows;
    }

    /**
     * 修改用户管理
     * 
     * @param microAppUser 用户管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateMicroAppUser(MicroAppUser microAppUser)
    {
        microAppUser.setUpdateTime(DateUtils.getNowDate());
        microAppUserMapper.deleteMicroUserInfoByUserId(microAppUser.getId());
        insertMicroUserInfo(microAppUser);
        return microAppUserMapper.updateMicroAppUser(microAppUser);
    }

    /**
     * 批量删除用户管理
     * 
     * @param ids 需要删除的用户管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMicroAppUserByIds(Long[] ids)
    {
        microAppUserMapper.deleteMicroUserInfoByUserIds(ids);
        return microAppUserMapper.deleteMicroAppUserByIds(ids);
    }

    /**
     * 删除用户管理信息
     * 
     * @param id 用户管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMicroAppUserById(Long id)
    {
        microAppUserMapper.deleteMicroUserInfoByUserId(id);
        return microAppUserMapper.deleteMicroAppUserById(id);
    }

    @Override
    public UserInfoVo selectUserInfo() {
        Long appUserId = SecurityUtils.getAppUserId();
        UserInfoVo userInfoVo = microAppUserMapper.selectUserInfo(appUserId);
        return userInfoVo;
    }

    /**
     * 新增${subTable.functionName}信息
     * 
     * @param microAppUser 用户管理对象
     */
    public void insertMicroUserInfo(MicroAppUser microAppUser)
    {
        List<MicroUserInfo> microUserInfoList = microAppUser.getMicroUserInfoList();
        Long id = microAppUser.getId();
        if (StringUtils.isNotNull(microUserInfoList))
        {
            List<MicroUserInfo> list = new ArrayList<MicroUserInfo>();
            for (MicroUserInfo microUserInfo : microUserInfoList)
            {
                microUserInfo.setUserId(id);
                list.add(microUserInfo);
            }
            if (list.size() > 0)
            {
                microAppUserMapper.batchMicroUserInfo(list);
            }
        }
    }

    /**
     * 实名认证
     * @param param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean identityVerify(AppIdentityVerifyParam param) {
        MicroAppUser appUser = new MicroAppUser();
        try {
            String verify = VerifyIdentityUtil.verify(param);
            JSONObject jsonObject = JSONObject.parseObject(verify);
            if ("0".equals(jsonObject.get("result").toString())){
                appUser.setId(SecurityUtils.getAppUserId());
                appUser.setIdCardNo(jsonObject.get("order_no").toString());
                appUser.setSex("女".equals(jsonObject.get("sex").toString()) ? 1L : 0L);
                appUser.setAddress(jsonObject.get("address").toString());
                appUser.setUpdateTime(DateUtils.getNowDate());
                appUser.setUpdateBy(SecurityUtils.getAppUserId().toString());
                //更新用户数据
                microAppUserMapper.updateMicroAppUser(appUser);
                return true;
            }else {
                return false;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
