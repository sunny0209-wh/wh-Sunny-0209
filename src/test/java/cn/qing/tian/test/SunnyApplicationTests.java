package cn.qing.tian.test;



import cn.qing.tian.test.service.impl.EmpServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SunnyApplicationTests {

//    @Autowired
//    private EmpServiceImpl eil;

    @Test
    public void contextLoads() {
//        String userName = "66666666666";
//        System.out.println("---------"+eil.queryName(userName));
        System.out.println(5>3?"y":"n");
    }

}
