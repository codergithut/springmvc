package webSource.rabbit.forgetful.service.entity;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/2/13
 * @description
 */
public class FileMessageTemplate {
    String fileName;
    byte[] content;
    String type;
    String encode;

    public FileMessageTemplate(String fileName, String type) {
        this.fileName=fileName;
        this.content=content;
        this.type=type;
    }

    public FileMessageTemplate(){

    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getContent() {
        return content;
    }

    public String getType() {
        return type;
    }

    public String getEncode() {

        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setType(String type) {
        this.type = type;
    }
}
