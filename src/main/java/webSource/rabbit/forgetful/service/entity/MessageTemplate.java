package webSource.rabbit.forgetful.service.entity;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/2/13
 * @description
 */
public class MessageTemplate {
    String serverName;
    String status;
    String form;
    FileMessageTemplate messageTemplate;

    public MessageTemplate(String serverName, String status, String form) {
        this.serverName = serverName;
        this.status = status;
        this.form = form;
    }

    //JSON工具需要
    public MessageTemplate() {
    }

    public String getServerName() {
        return serverName;
    }

    public String getStatus() {
        return status;
    }

    public String getForm() {
        return form;
    }

    public FileMessageTemplate getMessageTemplate() {
        return messageTemplate;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public void setMessageTemplate(FileMessageTemplate messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

}
