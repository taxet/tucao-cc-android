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
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import cc.tucao.zhang.finals.ServerFinals;
import cc.tucao.zhang.model.handler.TuCaoListXMLHandler;
import cc.tucao.zhang.tools.DebugLog;
import cc.tucao.zhang.tools.XMLTools;
import cc.tucao.zhang.type.TuCaoListType;
import android.app.Activity;

/**
 * @name �ӿ���ز���
 * @author zhang.yangyang
 * @create_date 2013-6-28
 * @edit_remark 
 */
public class TuCaoListModel {
    private Activity activity = null;

    public TuCaoListModel(Activity activity ) {
	this.activity = activity;
    }
    

//============================
//�ӿڵ���
    /**
     * ��ȡ��Ƶ�б�
     * 
     * @param catid ����id
     * @param start ��ʼ���
     * @param length ����
     * @param order ����ʽ
     * @return
     */
    public List<TuCaoListType> getTuCaoListValue(int catid , String start , int length , String order) {
	String url = ServerFinals.BEFORE_URL + "?apikey=" + ServerFinals.APP_KEY + "&type=xml" 
		+ "&start=" + start + "&length=" + length + "&order=" + order
		+ "&action=video_list" +"&cat=" +catid ;

	StringBuffer result = XMLTools.GetXmlValue(url, activity);
	
	DebugLog.logDebug("xml result : " +result);
	
	TuCaoListXMLHandler tuCaoListXMLHandler = getTuCaoListValue(result, activity);
	
	return tuCaoListXMLHandler.getResultList();
    }
    
    /**
     * video_list�ӿڽ�������
     * @param result XML��Ϣ
     * @param activity Context����
     * @return  tuCaoListXMLHandler����
     */
    private TuCaoListXMLHandler getTuCaoListValue(StringBuffer result,
	    Activity activity) {
	TuCaoListXMLHandler tuCaoListXMLHandler = null;
	try {
	    SAXParserFactory spf = SAXParserFactory.newInstance();
	    SAXParser saxParser = spf.newSAXParser();
	    tuCaoListXMLHandler = new TuCaoListXMLHandler();
	    saxParser.parse(new ByteArrayInputStream(result.toString().getBytes()), tuCaoListXMLHandler);
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
	return tuCaoListXMLHandler;
    }

}
