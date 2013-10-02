/**
 * @author zhang.yangyang
 * @create_date 2013-6-24
 * @last_edit_author 
 * @last_edit_date 
 * @edit_remark
 */
package cc.tucao.zhang.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import cc.tucao.zhang.R;
import cc.tucao.zhang.finals.SystemFinals;
import cc.tucao.zhang.var.PublicVariable;
import android.content.Context;
import android.net.ConnectivityManager;

/**
 * @name XML处理类
 * @author zhang.yangyang
 * @create_date 2013-6-24
 * @edit_remark 使用的是 上学的时候写的现成的代码 懒了没有改动 判断错误信息 这里应该改动为根据返回code判断
 */
public class XMLTools {
    public static StringBuffer GetXmlValue(String url, Context activity) {
	//无网络返回错误信息 // 需要修改 根据 返回的code判断
	if(!IsOnline(activity)){
	    return new StringBuffer(activity.getText(R.string.server_noweb_xml).toString());
	}
	
	StringBuffer sbResult = new StringBuffer();
	// 创建一个http客户端
	HttpClient client = new DefaultHttpClient();
	// 创建一个GET请求
	HttpGet httpGet = new HttpGet(url);
	// 向服务器发送请求并获取服务器返回的结果
	HttpResponse response = null;
	try {
	    response = client.execute(httpGet);
	    //获取Stream到字符
	    sbResult = ReturnXMLValue(sbResult, response);
	} catch (Exception e) {
	    DebugLog.logError("e=" + e.getMessage().toString());
	    // 无法访问服务器，加载错误信息
	    sbResult.append(activity.getText(R.string.server_noservice_xml)
		    .toString()); 
	}
	return sbResult;
    }

    /**
     * 读取返回的信息
     * @param sbResult 连接用字符
     * @param response 数据response
     * @return XML String
     * @throws Exception IllegalStateException或IOException异常
     */
    private static StringBuffer ReturnXMLValue(StringBuffer sbResult,
		HttpResponse response) throws Exception {
	// 返回的结果可能放到InputStream，http Header中等。
	InputStream inputStream = response.getEntity().getContent();
	// 读取内容
	BufferedReader br = new BufferedReader(new InputStreamReader(
		inputStream, "utf-8"));
	String data = "";
	if (sbResult.toString().equalsIgnoreCase("")) {
	    while ((data = br.readLine()) != null) {
		sbResult.append(data);
	    }
	}
	inputStream.close();
	return sbResult;
    }
    
    
    public static StringBuffer ReturnXMLValue(StringBuffer sbResult,
		String localFile) throws Exception {
	// 返回的结果可能放到InputStream，http Header中等。
	InputStream inputStream = new FileInputStream(new File(localFile));
	// 读取内容
	BufferedReader br = new BufferedReader(new InputStreamReader(
			inputStream, "utf-8"));
	String data = "";
	if (sbResult.toString().equalsIgnoreCase("")) {
		while ((data = br.readLine()) != null) {
			sbResult.append(data);
		}
	}
	inputStream.close();
	return sbResult;
}
    
    /**
     * 下载文件并保存到内存卡方法
     * @param urlString 文件地址
     * @param path 保存路径
     * @param fileName 文件名
     * @return 0成功，1已存在，-1发生错误
     */
    public int DownloadFile2SDCard(String urlString,String path,String fileName){
	InputStream inputStream = null;
	try {
	    FileTool fileTool = new FileTool();
	    if(fileTool.IsFileExist(fileName, path)){
		return 1;
	    }else{
		inputStream = GetInputStreamFromUrl(urlString);
		File resultFile = fileTool.Write2SDFromInput(path, fileName, inputStream);
		if(resultFile==null){
		    return -1;
		}
	    }
	} catch (Exception e) {
	    DebugLog.logError("e=" + e.getMessage().toString());
	    return -1;
	}finally{
	    try {
		inputStream.close();
	    } catch (Exception e) {
		DebugLog.logError("e=" + e.getMessage().toString());
	    }
	}
	
	return 0;
    }
    
    /**
     * 获取连接inputstream方法
     * @param urlStr 连接地址
     * @return inpustream流
     * @throws IOException
     */
    private InputStream GetInputStreamFromUrl(String urlStr) throws IOException{
	URL url = new URL(urlStr);
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	return conn.getInputStream();
    }
    
    
    
    /**
     * 判断是否有网络
     * @param activity context信息
     * @return 是否有网络，可能是WiFi或GPRS、HSDPA等等
     */
    public static boolean IsOnline(Context activity) {
	
	ConnectivityManager manager = (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);
	
	if (manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isAvailable()) {
	    // 能联网
	    if (manager.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI) {
		DebugLog.logInfo(SystemFinals.NETWORK_IS_WIFI);
		PublicVariable.isOnline = SystemFinals.NETWORK_IS_WIFI;
	    }else{
		DebugLog.logInfo(SystemFinals.NETWORK_IS_3G);
		PublicVariable.isOnline = SystemFinals.NETWORK_IS_3G;
	    }
	    return true;
	} else {
	    // 不能联网
	    DebugLog.logInfo(SystemFinals.NETWORK_NONE);
	    PublicVariable.isOnline = SystemFinals.NETWORK_NONE;
	    return false;
	}
    }
}
