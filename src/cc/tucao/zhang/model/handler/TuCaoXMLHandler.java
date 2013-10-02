/**
 * @author zhang.yangyang
 * @create_date 2013-7-1
 * @last_edit_author 
 * @last_edit_date 
 * @edit_remark
 */
package cc.tucao.zhang.model.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import cc.tucao.zhang.type.TuCaoType;

/**
 * @name 处理接口信息
 * @author zhang.yangyang
 * @create_date 2013-7-1
 * @edit_remark 
 */
public class TuCaoXMLHandler extends DefaultHandler{
    private TuCaoType tucaoType = null;
    private String preTag = "";
    
    public TuCaoXMLHandler() {
    }

    @Override
    public void startDocument() throws SAXException {
	super.startDocument();
	tucaoType = new TuCaoType();
    }

    @Override
    public void endDocument() throws SAXException {
	super.endDocument();

    }

    @Override
    public void startElement(String uri, String localName, String qName,
	    Attributes attributes) throws SAXException {
	super.startElement(uri, localName, qName, attributes);
	preTag = localName;
    }

    @Override
    public void endElement(String uri, String localName, String qName)
	    throws SAXException {
	super.endElement(uri, localName, qName);
	preTag = "";
    }

    @Override
    public void characters(char[] ch, int start, int length)
	    throws SAXException {
	
	super.characters(ch, start, length);
	
	String data = new String(ch, start, length);
	//添加公用参数
	if ("code".equals(preTag)) {
	    tucaoType.setCode(data);
	}else if ("id".equals(preTag)) {
	    tucaoType.setId(data);
	}else if ("catid".equals(preTag)) {
	    tucaoType.setCatid(data);
	}else if ("typeid".equals(preTag)) {
	    tucaoType.setTypeid(data);
	}else if ("title".equals(preTag)) {
	    tucaoType.setTitle(data);
	}else if ("style".equals(preTag)) {
	    tucaoType.setStyle(data);
	}else if ("thumb".equals(preTag)) {
	    tucaoType.setThumb(data);
	}else if ("keywords".equals(preTag)) {
	    tucaoType.setKeywords(data);
	}else if ("description".equals(preTag)) {
	    tucaoType.setDescription(data);
	}else if ("posids".equals(preTag)) {
	    tucaoType.setPosids(data);
	}else if ("url".equals(preTag)) {
	    tucaoType.setUrl(data);
	}else if ("listorder".equals(preTag)) {
	    tucaoType.setListorder(data);
	}else if ("status".equals(preTag)) {
	    tucaoType.setStatus(data);
	}else if ("sysadd".equals(preTag)) {
	    tucaoType.setSysadd(data);
	}else if ("username".equals(preTag)) {
	    tucaoType.setUsername(data);
	}else if ("inputtime".equals(preTag)) {
	    tucaoType.setInputtime(data);
	}else if ("updatetime".equals(preTag)) {
	    tucaoType.setUpdatetime(data);
	}else if ("content".equals(preTag)) {
	    tucaoType.setContent(data);
	}else if ("paginationtype".equals(preTag)) {
	    tucaoType.setPaginationtype(data);
	}else if ("maxcharperpage".equals(preTag)) {
	    tucaoType.setMaxcharperpage(data);
	}else if ("template".equals(preTag)) {
	    tucaoType.setTemplate(data);
	}else if ("paytype".equals(preTag)) {
	    tucaoType.setPaytype(data);
	}else if ("relation".equals(preTag)) {
	    tucaoType.setRelation(data);
	}else if ("source".equals(preTag)) {
	    tucaoType.setSource(data);
	}else if ("part".equals(preTag)) {
	    tucaoType.setPart(data);
	}else if ("time".equals(preTag)) {
	    tucaoType.setTime(data);
	}else if ("yuanchuang".equals(preTag)) {
	    tucaoType.setYuanchuang(data);
	}else if ("info".equals(preTag)) {
	    tucaoType.setInfo(data);
	}else if ("subtitle".equals(preTag)) {
	    tucaoType.setSubtitle(data);
	}
    }
    public TuCaoType getResult() {
	return tucaoType;
    }
}
