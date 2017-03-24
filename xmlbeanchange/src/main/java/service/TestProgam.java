package service;

import entity.BA_QLR;
import entity.Country;
import org.junit.Test;
import util.JaxbUtil;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/3/24
 * @description
 */
public class TestProgam {
    /**
     *
     */
    @Test
    public void showUnMarshaller() {
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                "<baQLR YSDM=\"6003000000\" BDCDYH=\"500115003029GB00255\" " +
                "SXH=\"1\" QLRMC=\"重庆市金司\" bdcdyh=\"渝（2015）长寿区不动产权\" " +
                "QZYSXLH=\"50000003014\" SFCZR=\"0\" ZJZL=\"7\" zjh=\"500221000039792   " +
                "1-1-1\" GJ=\"142\" XB=\"3\" QLRLX=\"2\" GYFS=\"0\" " +
                "GYQK=\"以上房屋 个权利人共有，共（或按份）共有。按份共有的共有份额各自为：\" QXDM=\"500115\" />\n";

        BA_QLR baQlr = JaxbUtil.converyToJavaBean(str, BA_QLR.class);

        System.out.println(baQlr.getZJH());
    }
}
