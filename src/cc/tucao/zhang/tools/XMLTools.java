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
 * @name XML������
 * @author zhang.yangyang
 * @create_date 2013-6-24
 * @edit_remark ʹ�õ��� ��ѧ��ʱ��д���ֳɵĴ��� ����û�иĶ� �жϴ�����Ϣ ����Ӧ�øĶ�Ϊ���ݷ���code�ж�
 */
public class XMLTools {
    public static StringBuffer GetXmlValue(String url, Context activity) {
	//�����緵�ش�����Ϣ // ��Ҫ�޸� ���� ���ص�code�ж�
	if(!IsOnline(activity)){
	    return new StringBuffer(activity.getText(R.string.server_noweb_xml).toString());
	}
	
	StringBuffer sbResult = new StringBuffer();
	// ����һ��http�ͻ���
	HttpClient client = new DefaultHttpClient();
	// ����һ��GET����
	HttpGet httpGet = new HttpGet(url);
	// ��������������󲢻�ȡ���������صĽ��
	HttpResponse response = null;
	try {
	    response = client.execute(httpGet);
	    //��ȡStream���ַ�
	    sbResult = ReturnXMLValue(sbResult, response);
	} catch (Exception e) {
	    DebugLog.logError("e=" + e.getMessage().toString());
	    // �޷����ʷ����������ش�����Ϣ
	    sbResult.append(activity.getText(R.string.server_noservice_xml)
		    .toString()); 
	}
	return sbResult;
    }

    /**
     * ��ȡ���ص���Ϣ
     * @param sbResult �������ַ�
     * @param response ����response
     * @return XML String
     * @throws Exception IllegalStateException��IOException�쳣
     */
    private static StringBuffer ReturnXMLValue(StringBuffer sbResult,
		HttpResponse response) throws Exception {
	// ���صĽ�����ܷŵ�InputStream��http Header�еȡ�
	InputStream inputStream = response.getEntity().getContent();
	// ��ȡ����
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
	// ���صĽ�����ܷŵ�InputStream��http Header�еȡ�
	InputStream inputStream = new FileInputStream(new File(localFile));
	// ��ȡ����
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
     * �����ļ������浽�ڴ濨����
     * @param urlString �ļ���ַ
     * @param path ����·��
     * @param fileName �ļ���
     * @return 0�ɹ���1�Ѵ��ڣ�-1��������
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
     * ��ȡ����inputstream����
     * @param urlStr ���ӵ�ַ
     * @return inpustream��
     * @throws IOException
     */
    private InputStream GetInputStreamFromUrl(String urlStr) throws IOException{
	URL url = new URL(urlStr);
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	return conn.getInputStream();
    }
    
    
    
    /**
     * �ж��Ƿ�������
     * @param activity context��Ϣ
     * @return �Ƿ������磬������WiFi��GPRS��HSDPA�ȵ�
     */
    public static boolean IsOnline(Context activity) {
	
	ConnectivityManager manager = (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);
	
	if (manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isAvailable()) {
	    // ������
	    if (manager.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI) {
		DebugLog.logInfo(SystemFinals.NETWORK_IS_WIFI);
		PublicVariable.isOnline = SystemFinals.NETWORK_IS_WIFI;
	    }else{
		DebugLog.logInfo(SystemFinals.NETWORK_IS_3G);
		PublicVariable.isOnline = SystemFinals.NETWORK_IS_3G;
	    }
	    return true;
	} else {
	    // ��������
	    DebugLog.logInfo(SystemFinals.NETWORK_NONE);
	    PublicVariable.isOnline = SystemFinals.NETWORK_NONE;
	    return false;
	}
    }
}
