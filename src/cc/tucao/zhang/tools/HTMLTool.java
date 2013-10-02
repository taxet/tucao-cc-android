/**
 * @author zhang.yangyang
 * @create_date 2013-6-24
 * @last_edit_author 
 * @last_edit_date 
 * @edit_remark
 */
package cc.tucao.zhang.tools;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @name HTML��Ϣ������
 * @author zhang.yangyang
 * @create_date 2013-6-24
 * @edit_remark  ͼƬ��ַ��url��Ҫ���������һ�� ��Ƶ��Ϣ�ȵȵط��� ת�������� 
 */
public class HTMLTool {
	/**
	 * ����б��
	 * @param str ԭ�ַ�
	 * @return ������ַ�
	 */
	public static String ToDoubleYin(String str){
		return str.replace("\"", "\\\"");
	}
	
	/**
	 * ת��HTML���
	 * @param str ԭ�ַ�
	 * @return ������ַ�
	 */
	public static String ToHtml(String str) {
		String html = str;
		html = HtmlReplace(html, "&", "&amp;");
		html = HtmlReplace(html, "<", "&lt;");
		html = HtmlReplace(html, ">", "&gt;");
		html = HtmlReplace(html, "\r\n", "\n");
		html = HtmlReplace(html, "\n", "<br>\n");
		html = HtmlReplace(html, "\t", "         ");
		html = HtmlReplace(html, "     ", "   &nbsp;");
		return html;
	}
	
	/**
	 * ����url��ȡHttpURLConnection����
	 * @param url ���ӵ�ַ
	 * @return HttpURLConnection����
	 * @throws IOException
	 */
        public static HttpURLConnection GetHttpConnection(String url)
    	    	throws IOException {
            
            URL myFileUrl = null;
            myFileUrl = new URL(url);
            DebugLog.logDebug("FileUrl = " + myFileUrl.toString());
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            return conn;
            
        }
	
	public static boolean IsUrlExist(String url) 
		throws IOException{
	    
	    HttpURLConnection httpURLConn = HTMLTool.GetHttpConnection(url);
	    
	    if (httpURLConn == null) {
		return false;
	    }else {
		int resonseCode = httpURLConn.getResponseCode();
		
		if (resonseCode == 200) {
		    return true;
		}else {
		    return false;
		}
	    }
	}

	/**
	 * HTML�ַ��滻
	 * @param source ԭ�ַ�
	 * @param oldString Ҫ�滻��
	 * @param newString �滻�ɵ�
	 * @return ������ַ�
	 */
	public static String HtmlReplace(String source, String oldString,
			String newString) {
	    StringBuffer output = new StringBuffer();
	    int lengthOfSource = source.length(); // Դ�ַ�������
	    int lengthOfOld = oldString.length(); // ���ַ�������
	    int posStart = 0; // ��ʼ����λ��
	    int pos; // ���������ַ�����λ��
	    
	    while ((pos = source.indexOf(oldString, posStart)) >= 0) {
		output.append(source.substring(posStart, pos));
		output.append(newString);
		posStart = pos + lengthOfOld;
		}
	    if (posStart < lengthOfSource) {
		output.append(source.substring(posStart));
		}
	    return output.toString();
	}
}