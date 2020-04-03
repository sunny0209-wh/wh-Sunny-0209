package cn.qing.tian.test.controller.fileController;

import cn.qing.tian.test.service.impl.FileServiceImpl;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {
    private final FileServiceImpl fsi;
    public FileController(FileServiceImpl fsi) {
        this.fsi = fsi;
    }

    //文件上传
    @PostMapping("/uploads")
    public String upLoad(MultipartFile file,String userName)
    {
        System.out.println(userName);
        if(file == null)
        {
            System.out.println("file的值空");
        }
        assert file != null;
        return JSON.toJSONString(fsi.upLoad(file,userName));
    }
}
