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
 * @name HTML信息处理类
 * @author zhang.yangyang
 * @create_date 2013-6-24
 * @edit_remark  图片地址的url需要用这个处理一下 视频信息等等地方的 转换有问题 
 */
public class HTMLTool {
	/**
	 * 处理斜杠
	 * @param str 原字符
	 * @return 处理后字符
	 */
	public static String ToDoubleYin(String str){
		return str.replace("\"", "\\\"");
	}
	
	/**
	 * 转义HTML标记
	 * @param str 原字符
	 * @return 处理后字符
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
	 * 根据url获取HttpURLConnection对象
	 * @param url 链接地址
	 * @return HttpURLConnection对象
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
	 * HTML字符替换
	 * @param source 原字符
	 * @param oldString 要替换的
	 * @param newString 替换成的
	 * @return 处理后字符
	 */
	public static String HtmlReplace(String source, String oldString,
			String newString) {
	    StringBuffer output = new StringBuffer();
	    int lengthOfSource = source.length(); // 源字符串长度
	    int lengthOfOld = oldString.length(); // 老字符串长度
	    int posStart = 0; // 开始搜索位置
	    int pos; // 搜索到老字符串的位置
	    
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