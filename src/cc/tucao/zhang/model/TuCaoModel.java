/**
 * @author zhang.yangyang
 * @create_date 2013-6-28
 * @last_edit_author 
 * @last_edit_date 
 * @edit_remark
 */
package cc.tucao.zhang.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import cc.tucao.zhang.finals.ServerFinals;
import cc.tucao.zhang.model.handler.TuCaoXMLHandler;
import cc.tucao.zhang.tools.DebugLog;
import cc.tucao.zhang.tools.StringTool;
import cc.tucao.zhang.tools.XMLTools;
import cc.tucao.zhang.type.TuCaoType;
import android.app.Activity;

/**
 * @name 接口相关操作
 * @author zhang.yangyang
 * @create_date 2013-6-28
 * @edit_remark 
 */
public class TuCaoModel {
    private Activity activity = null;

    public TuCaoModel(Activity activity ) {
	this.activity = activity;
    }


//============================
//接口调用
    /**
     * 获取视频相关信息
     * 
     * @param catid 分类id
     * @param id 去掉catid的视频id
     * @return TuCaoType集合
     */
    public TuCaoType getTuCaoValue(String catid , String id ){
	String url = ServerFinals.BEFORE_URL + "?apikey=" + ServerFinals.APP_KEY + "&type=xml" 
		+ "&action=get_video" + "&cat=" + catid +"&id=" + id;
	
	StringBuffer result = XMLTools.GetXmlValue(url, activity);
	
	DebugLog.logInfo("url :" +url);
	DebugLog.logInfo("xml result :" +StringTool.ClearCode(result));

	TuCaoXMLHandler tuCaoXMLHandler = getTuCaoValue(StringTool.ClearCode(result), activity);
	
	return tuCaoXMLHandler.getResult();
	
    }
    
    
    /**
     * get_video接口解析方法
     * @param result XML信息
     * @param activity Context内容
     * @return  TuCaoXMLHandler对象
     */
    private TuCaoXMLHandler getTuCaoValue(StringBuffer result,
	    Activity activity) {
	TuCaoXMLHandler tuCaoXMLHandler = null;
	try {
	    SAXParserFactory spf = SAXParserFactory.newInstance();
	    SAXParser saxParser = spf.newSAXParser();
	    tuCaoXMLHandler = new TuCaoXMLHandler();
	    saxParser.parse(new ByteArrayInputStream(result.toString().getBytes()), tuCaoXMLHandler);
	} catch (ParserConfigurationException e) {
	    e.printStackTrace();
	    return null;
	} catch (SAXException e) {
	    e.printStackTrace();
	    return null;
	} catch (IOException e) {
	    e.printStackTrace();
	    return null;
	}
	return tuCaoXMLHandler;
    }

}
