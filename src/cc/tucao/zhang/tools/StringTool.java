/**
 * @author zhang.yangyang
 * @create_date 2013-07-03
 * @last_edit_author 
 * @last_edit_date 
 * @edit_remark
 */
package cc.tucao.zhang.tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Name �ַ�����ش���
 * @author zhang.yangyang
 * @create_date 2013-07-03
 * @edit_remark 
 */
public class StringTool {
    
    /**
     * ��ȡ��ʽ�������ʾʱ�� 
     * @param d ʱ�����
     * @param format ��ʽ��Ĭ�� ��-��-�� ʱ:��
     * @return ʱ������
     */
    public static String GetFormatTime(Date d, String format) {
	if (format == null || format.equalsIgnoreCase("")) {
	    format = "yyyy-MM-dd- HH:mm";
	}
	SimpleDateFormat dateFm = new SimpleDateFormat(format); // ��ʽ����ǰϵͳ����
	String dateTime = dateFm.format(d);
	return dateTime;
    }

    /**
     * ��ʱ���תΪ�ַ��� 
     * @param cc_time ʱ���
     * @return ת������ַ���
     */
    public static String GetStrTime(String cc_time, String format) {
	String re_StrTime = null;
	if (format.equalsIgnoreCase("")) {
	    format = "MM-dd HH:mm:ss";
	}
	SimpleDateFormat sdf = new SimpleDateFormat(format);
	// ���磺cc_time=1291778220
	long lcc_time = Long.valueOf(cc_time);
	re_StrTime = sdf.format(new Date(lcc_time * 1000L));
	return re_StrTime;
    }

    /**
     * ����url����ͼƬ�� 
     * @param url url��ַ
     * @return ͼƬ�ļ���
     */
    public static String GetImageNameForUrl(String url) {
	String reName = url.substring(url.lastIndexOf("/") + 1);

	if (reName.contains(".")) {
	    return reName;
	} else {
	    return "";
	}
    }
    
    /**
     * ʹ�� ���ݹ�����source ��ֳ���Ƶ����
     * @param source ����
     * @param title ����
     * @return list
     */
    public static List<Map<String, String>> GetTextForSource(String source , String title) {
	// ��� ��Щ�� �ܿ��ܻ���� ��ע������ 
	
	List<Map<String, String>> video_info_maplist = new ArrayList<Map<String,String>>();
	Map<String , String> video_info_hashmap;
	
//	List<String> list = new ArrayList<String>();
	
	//�ж��ǲ��ǵ�������Ƶ ��Ҫ�ж� �ַ������Ƿ��� ** û�� ���� ����
	if (!source.contains("**")) {
	    //type=xxx|vid=xxx
	    //������ Ӧ���� ���� ������  p ����Ƶ  ����֮�� ���ַ������ ���� |
	    if (!source.substring(source.length()-1).equals("|")) {
		source += "|";
	    }
	    //type=xxx|vid=xxx|
	    //����������� �� ��������Ƶ ��β��| ����Ҫ���� ���� 
	    source = source+title;
	}
	//���� ����������������� ���ֵ� source Ӧ���� �����ӹ���ɵ� ��ȫ����Ҫ��� ������Ƶ type=xxx|vid=xxx|title
	DebugLog.logDebug("source : "+ source);
	
/**-------------------------------------*/
	
	//���� ����** ��Ϊ�ֽ��� ��ֳ���ÿ����Ƶ����Ϣ һ���� type=xxxx|xid=xxxxxxx|name
	String count[] = source.split("\\*\\*");
	for (int i = 0; i < count.length; i++) {
	    video_info_hashmap = new HashMap<String, String>();
	    video_info_hashmap.put("number", String.valueOf(i+1));
	    //count[i]����û��������Ƶ����Ҫ������ ��Ҫ���� hashmap
	    //���� �� | ����ַ��� ���subcount[]
	    String subcount[] = count[i].split("\\|");
	    for (int j = 0; j < subcount.length; j++) {
		//���� = ��� д�� hashmap
		String subsubcount[] = subcount[j].split("\\=");
		for (int k = 0; k < subsubcount.length; k++) {
		    if (j == 0) {
			video_info_hashmap.put("type", subsubcount[k]);
		    }else if (j == 1) {
			video_info_hashmap.put("vid", subsubcount[k]);
		    }else if (j == 2) {
			video_info_hashmap.put("name", subsubcount[k]);
			video_info_maplist.add(video_info_hashmap);
		    }
		}
	    }
	}
	
//	for (int i = 0; i < count.length; i++) {
//	    //���ַ����в�ֳ��� ���� ���߷� P ������
//	    String string = count[i].substring(count[i].lastIndexOf("|") + 1);
//	    list.add(string);
//	}
	
	
	return video_info_maplist;
    }
    
    /**
     * ��htmltool���й������Ƶķ��� û�в���
     * �滻�ַ����е�&amp; &quot; &nbsp; &lt; &gt;
     * @param str Ҫ������ַ�����StringBuffer��
     * @return ���������ݣ�StringBuffer��
     */
    public static StringBuffer ClearCode(StringBuffer str) {
	String s = str.toString();
	String s1 = s.replaceAll("&amp;", "|"); //��ע &amp; Ӧ����& �䶯һ�� �滻��| Ϊ�˷����Ժ��ڲ��
	String s2 = s1.replaceAll("&quot;", "\"");
	String s3 = s2.replaceAll("&lt;", "<");
	String s4 = s3.replaceAll("&gt;", ">");
	String s5 = s4.replaceAll("&nbsp;", " ");
	
	StringBuffer sb = new StringBuffer(s5);
	return sb;
    }
}
