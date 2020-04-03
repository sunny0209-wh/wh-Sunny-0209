package cn.qing.tian.test.utils;
import org.apache.commons.codec.binary.Hex;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

   public String md5Salt(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        //将密码以utf-8的编码方式转换成二进制
        byte[] dest =  md.digest(password.getBytes(StandardCharsets.UTF_8));
        //转换成哈希码
        String src = Hex.encodeHexString(dest);
        System.out.println("md5:"+src);
        String qt = "qingtian";
        //将字符串转换为字符数组。
        char[] as = (src+qt).toCharArray();
        for (int i=0,j=32;j<40;i+=3,j++)
        {
            int t = j%2==0?i+1:i;
            char team = as[t];
            as[t] = as[j];
            as[j] = team;
        }
        String builder = "e30ed8db6a94d8c7c0b9e469f2";
        //将字符数组转换成字符串
        String qwer = String.valueOf(as);
        char[] cs = (builder+qwer).toCharArray();
        for(int i=0,j=32;j<48;i+=3,j++)
        {
            int t = j%2==0?i+1:i;
            char temp = cs[t];
            cs[t] = cs[j];
            cs[j] = temp;
        }
        char temp;
        for(int i=0;i<cs.length/2;i++)
        {
            temp=cs[i];
            cs[i]=cs[cs.length-i-1];
            cs[cs.length-i-1]=temp;
        }
       return String.valueOf(cs);
    }
}
