/**
 * @author zhang.yangyang
 * @create_date 2013-7-1
 * @last_edit_author 
 * @last_edit_date 
 * @edit_remark
 */
package cc.tucao.zhang.model.handler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import cc.tucao.zhang.type.TuCaoListType;

/**
 * @name 处理接口信息(List)
 * @author zhang.yangyang
 * @create_date 2013-7-1
 * @edit_remark 
 */
public class TuCaoListXMLHandler extends DefaultHandler{
    private TuCaoListType tucaolistType = null;
    private List<TuCaoListType> listtucaoType = null;
    private String preTag = "";
    
    public TuCaoListXMLHandler() {
    }

    @Override
    public void startDocument() throws SAXException {
	super.startDocument();
	listtucaoType = new ArrayList<TuCaoListType>();
	tucaolistType = new TuCaoListType();
    }

    @Override
    public void endDocument() throws SAXException {
	super.endDocument();

    }

    @Override
    public void startElement(String uri, String localName, String qName,
	    Attributes attributes) throws SAXException {
	super.startElement(uri, localName, qName, attributes);
	if ("item".equals(localName)) {
	    tucaolistType = new TuCaoListType();
	}
	preTag = localName;
    }

    @Override
    public void endElement(String uri, String localName, String qName)
	    throws SAXException {
	super.endElement(uri, localName, qName);
	if ("item".equals(localName)) {
	    if (tucaolistType != null) {
		
		listtucaoType.add(tucaolistType);
		tucaolistType = null;
	    }
	}
	preTag = "";
    }

    @Override
    public void characters(char[] ch, int start, int length)
	    throws SAXException {
	
	super.characters(ch, start, length);
	
	String data = new String(ch, start, length);
	//添加公用参数
	if ("id".equals(preTag)) {
	    tucaolistType.setId(data);
	}else if ("catid".equals(preTag)) {
	    tucaolistType.setCatid(data);
	}else if ("typeid".equals(preTag)) {
	    tucaolistType.setTypeid(data);
	}else if ("title".equals(preTag)) {
	    tucaolistType.setTitle(data);
	}else if ("style".equals(preTag)) {
	    tucaolistType.setStyle(data);
	}else if ("thumb".equals(preTag)) {
	    tucaolistType.setThumb(data);
	}else if ("keywords".equals(preTag)) {
	    tucaolistType.setKeywords(data);
	}else if ("description".equals(preTag)) {
	    tucaolistType.setDescription(data);
	}else if ("posids".equals(preTag)) {
	    tucaolistType.setPosids(data);
	}else if ("url".equals(preTag)) {
	    tucaolistType.setUrl(data);
	}else if ("listorder".equals(preTag)) {
	    tucaolistType.setListorder(data);
	}else if ("status".equals(preTag)) {
	    tucaolistType.setStatus(data);
	}else if ("sysadd".equals(preTag)) {
	    tucaolistType.setSysadd(data);
	}else if ("username".equals(preTag)) {
	    tucaolistType.setUsername(data);
	}else if ("inputtime".equals(preTag)) {
	    tucaolistType.setInputtime(data);
	}else if ("updatetime".equals(preTag)) {
	    tucaolistType.setUpdatetime(data);
	}
	
    }


    public List<TuCaoListType> getResultList() {
	return listtucaoType;
    }
}
