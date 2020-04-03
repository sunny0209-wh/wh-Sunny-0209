package cn.qing.tian.test.service.impl;

import cn.qing.tian.test.entity.shiroEntity.Emp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
public class FileServiceImpl {
    private final EmpServiceImpl esi;

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public FileServiceImpl(EmpServiceImpl esi) {
        this.esi = esi;
    }

    //上传单个文件
    public String upLoad(MultipartFile file,String userName)
    {
        try
        {
            if(file.isEmpty())
            {
                return "文件为空";
            }
            //获取文件名
            String fileName = file.getOriginalFilename();
            logger.info("上传的文件名是"+fileName);
            //获取文件的后缀名
            assert fileName != null;
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //设置文件的储存路径
            //C:/保存编写的前端程序/SunnyTest/userTitleImg/
            String filePath = "C:/前端/vue/myvue-wh/static/titleImgs/";
            //"/userTitleImg/"+
            String headImg = "/static/titleImgs/"+fileName;

            String path = filePath + fileName;
            File dest = new File(path);
            //检查是否存在目录
            if(!dest.getParentFile().exists())
            {
                dest.getParentFile().mkdirs();//创建新的文件夹
            }
            file.transferTo(dest);//写入文件
            esi.updateImg(headImg,userName);
            return "上传成功";
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            return "上传失败";
        }
    }

    //文件下载
    @GetMapping("/dowload")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response)
    {
        String fileName = "test.txt";//文件名
        //设置文件路径
        File file = new File("C:/Users/16643/Downloads/test.txt");
        if (file.exists())//判断文件父目录是否存在
        {
            response.setContentType("application/force-download");//设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);//设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;//文件输入流
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();//输出流
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                logger.info("下载"+fileName+"成功");
                return "下载成功";
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "下载失败";
    }
}
