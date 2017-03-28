package util;

import entity.BA_QLR;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/3/24
 * @description
 */
public class ChangeXmlFormat {
    static Map<String,String> reflectData = new HashMap<String,String>();
    static{
        reflectData.put("ZTT_GY_QLR","baQLR");
    }


    public static Map<String,String> getParamValueByElement(String fileContent,String parentElement) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document doc;
        Element sonElement;
        Document sonDoc;
        String className;
        String elementName;
        doc = DocumentHelper.parseText(fileContent);
        Map<String,String> data = new HashMap<String,String>();
        Element root = doc.getRootElement();
        Element son = root.element(parentElement);
        List<Element> elements = son.elements();
        for(Element e : elements){
            elementName = e.getName();
            className = (reflectData.get(elementName) == null ? elementName : reflectData.get(elementName));
            sonElement = DocumentHelper.createElement(className);
            List<Attribute> attributes = e.attributes();
            for(Attribute attr : attributes){
                sonElement.setAttributeValue(attr.getName().toLowerCase(),attr.getText());

            }
            sonDoc = DocumentHelper.createDocument(sonElement);
            String s = sonDoc.asXML();
            data.put(e.getName(),s);
        }

        return data;
    }

    public static void main(String[] args) throws DocumentException {
        String xmlStr = "<Message>\n" +
                "  <Head>\n" +
                "    <BizMsgID>500115151030000031</BizMsgID>\n" +
                "    <ASID>AS100</ASID>\n" +
                "    <AreaCode>500115</AreaCode>\n" +
                "    <RecType>1000301</RecType>\n" +
                "    <RightType>3</RightType>\n" +
                "    <RegType>100</RegType>\n" +
                "    <CreateDate>2015-10-29T14:33:13</CreateDate>\n" +
                "    <RecFlowID>201504031190080</RecFlowID>\n" +
                "    <RegOrgID>500115</RegOrgID>\n" +
                "    <ParcelID />\n" +
                "    <EstateNum>500115003029GB00255</EstateNum>\n" +
                "    <DigitalSign>6bfa32a846896c9cf6</DigitalSign>\n" +
                "    <PreEstateNum>500115003029GB00255</PreEstateNum>\n" +
                "    <CertCount />\n" +
                "    <ProofCount />\n" +
                "    <PreCertID />\n" +
                "  </Head>\n" +
                "  <Data>\n" +
                "    <ZTT_GY_QLR YSDM=\"6003000000\" BDCDYH=\"500115003029GB00255\" SXH=\"1\" QLRMC=\"重庆市金司\" BDCQZH=\"渝（2015）长寿区不动产权\" QZYSXLH=\"50000003014\" SFCZR=\"0\" ZJZL=\"7\" ZJH=\"500221000039792   1-1-1\" GJ=\"142\" XB=\"3\" QLRLX=\"2\" GYFS=\"0\" GYQK=\"以上房屋 个权利人共有，共（或按份）共有。按份共有的共有份额各自为：\" QXDM=\"500115\" />\n" +
                "     </Data>\n" +
                "</Message>";
        Map<String,String> datas = getParamValueByElement(xmlStr,"Data");
        BA_QLR baQlr = JaxbUtil.converyToJavaBean(datas.get("ZTT_GY_QLR"), BA_QLR.class);
        System.out.println(baQlr.getZJH());
    }
}
