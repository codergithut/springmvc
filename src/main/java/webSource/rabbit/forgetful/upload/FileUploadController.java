package webSource.rabbit.forgetful.upload;


import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import rabbit.service.entity.FileMessageTemplate;
import rabbit.service.entity.MessageTemplate;
import rabbit.service.provider.SendMessage;
import rabbit.util.FileCharsetDetector;

@Controller
public class FileUploadController {

    @Autowired
    SendMessage sendMessage;

    @RequestMapping(value="/upload", method= RequestMethod.GET)
    public  String provideUploadInfo() {
        return "upload_springbooot";
    }

    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@RequestParam("name") String name
            , @RequestParam("file") MultipartFile file
    ){
        String fileName=null;

        System.out.println("this is test");
        if (!file.isEmpty()) {
            try {
                String encode=new FileCharsetDetector().guessFileEncoding(file.getInputStream());
                fileName=file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                MessageTemplate message=new MessageTemplate();
                FileMessageTemplate fileMessage=new FileMessageTemplate();
                fileMessage.setContent(file.getBytes());
                fileMessage.setEncode(encode);
                fileMessage.setFileName(fileName);
                message.setMessageTemplate(fileMessage);
                sendMessage.sendDirectMsg(JSON.toJSONString(message));
                return "You successfully uploaded " + fileName;
            } catch (Exception e) {
                return "You failed to upload " + fileName + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + fileName + " because the file was empty.";
        }
    }

}