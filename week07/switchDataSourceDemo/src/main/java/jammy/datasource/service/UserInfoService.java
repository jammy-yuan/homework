package jammy.datasource.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jammy.datasource.config.Master;
import jammy.datasource.config.Slave;
import jammy.datasource.mapper.UserInfoMapper;
import jammy.datasource.pojo.UserInfo;

@Service
public class UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Master
    public void addMaster(final UserInfo account) {
        final int i = userInfoMapper.insert(account);
        System.out.println("新增成功：" + i + "条");
    }

    @Master
    public List<UserInfo> findAllMaster() {
        return userInfoMapper.selectAll();
    }

    @Slave
    public void addSlave(final UserInfo account) {
        final int i = userInfoMapper.insert(account);
        System.out.println("新增成功：" + i + "条");
    }

    @Slave
    public List<UserInfo> findAllSlave() {
        return userInfoMapper.selectAll();
    }
}
