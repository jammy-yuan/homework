package jammy.datasource.switchdemo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jammy.datasource.pojo.UserInfo;
import jammy.datasource.service.UserInfoService;

@SpringBootTest
@RunWith(SpringRunner.class)
class SwitchdemoApplicationTests {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void addMasterTest() {
        final UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setUserName("master-test1");
        userInfo.setLoginId("master-login-id");
        userInfo.setPwd("111");
        userInfoService.addMaster(userInfo);
    }

    @Test
    public void findAllMasterTest() {
        final List<UserInfo> all = userInfoService.findAllMaster();
        System.out.println(all.toString());
    }

    @Test
    public void addSlaveTest() {
        final UserInfo userInfo = new UserInfo();
        userInfo.setId(2);
        userInfo.setUserName("slave-test1");
        userInfo.setLoginId("slave-login-id");
        userInfo.setPwd("111");
        userInfoService.addSlave(userInfo);
    }

    @Test
    public void findAllSlaveTest() {
        final List<UserInfo> all = userInfoService.findAllSlave();
        System.out.println(all.toString());
    }
}
