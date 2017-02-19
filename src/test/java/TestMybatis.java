import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pansoft.logic.entity.User;
import com.pansoft.logic.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
// 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestMybatis {
    private static Logger logger = Logger.getLogger(TestMybatis.class);

    // private ApplicationContext ac = null;
    @Autowired
    UserService userService;

    // @Before
    // public void before() {
    // ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    // userService = (IUserService) ac.getBean("userService");
    // }

    @Test
    public void test1() {
        User loginUser = new User();
        loginUser.setU_account("admin");
        loginUser.setU_password("123456");
        User user = userService.getUserByNameAndPwd(loginUser);
        // System.out.println(user.getUserName());
        // logger.info("值："+user.getUserName());
        System.out.println(JSONObject.fromObject(user));
        /* logger.info(JSON.toJSONString(user)); */
    }
}