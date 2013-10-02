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
 * @Name 字符串相关处理
 * @author zhang.yangyang
 * @create_date 2013-07-03
 * @edit_remark 
 */
public class StringTool {
    
    /**
     * 获取格式化后的显示时间 
     * @param d 时间对象
     * @param format 格式，默认 年-月-日 时:分
     * @return 时间内容
     */
    public static String GetFormatTime(Date d, String format) {
	if (format == null || format.equalsIgnoreCase("")) {
	    format = "yyyy-MM-dd- HH:mm";
	}
	SimpleDateFormat dateFm = new SimpleDateFormat(format); // 格式化当前系统日期
	String dateTime = dateFm.format(d);
	return dateTime;
    }

    /**
     * 将时间戳转为字符串 
     * @param cc_time 时间戳
     * @return 转换后的字符串
     */
    public static String GetStrTime(String cc_time, String format) {
	String re_StrTime = null;
	if (format.equalsIgnoreCase("")) {
	    format = "MM-dd HH:mm:ss";
	}
	SimpleDateFormat sdf = new SimpleDateFormat(format);
	// 例如：cc_time=1291778220
	long lcc_time = Long.valueOf(cc_time);
	re_StrTime = sdf.format(new Date(lcc_time * 1000L));
	return re_StrTime;
    }

    /**
     * 根据url返回图片名 
     * @param url url地址
     * @return 图片文件名
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
     * 使用 传递过来的source 拆分出视频标题
     * @param source 内容
     * @param title 标题
     * @return list
     */
    public static List<Map<String, String>> GetTextForSource(String source , String title) {
	// 这段 有些乱 很可能会出错 备注先留着 
	
	List<Map<String, String>> video_info_maplist = new ArrayList<Map<String,String>>();
	Map<String , String> video_info_hashmap;
	
//	List<String> list = new ArrayList<String>();
	
	//判断是不是单个的视频 需要判断 字符串中是否有 ** 没有 就是 单个
	if (!source.contains("**")) {
	    //type=xxx|vid=xxx
	    //进来的 应该是 所有 单个分  p 的视频  进来之后 在字符串后边 加上 |
	    if (!source.substring(source.length()-1).equals("|")) {
		source += "|";
	    }
	    //type=xxx|vid=xxx|
	    //出现在这里的 是 单个的视频 结尾是| 还需要加上 标题 
	    source = source+title;
	}
	//以上 按照我想象的做到了 出现的 source 应该是 经过加工完成的 完全符合要求的 单个视频 type=xxx|vid=xxx|title
	DebugLog.logDebug("source : "+ source);
	
/**-------------------------------------*/
	
	//首先 按照** 作为分界线 拆分出来每个视频的信息 一般是 type=xxxx|xid=xxxxxxx|name
	String count[] = source.split("\\*\\*");
	for (int i = 0; i < count.length; i++) {
	    video_info_hashmap = new HashMap<String, String>();
	    video_info_hashmap.put("number", String.valueOf(i+1));
	    //count[i]就是没个单个视频所需要的内容 需要加入 hashmap
	    //首先 以 | 拆分字符串 获得subcount[]
	    String subcount[] = count[i].split("\\|");
	    for (int j = 0; j < subcount.length; j++) {
		//再以 = 拆分 写入 hashmap
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
//	    //从字符串中拆分出来 标题 或者分 P 的名字
//	    String string = count[i].substring(count[i].lastIndexOf("|") + 1);
//	    list.add(string);
//	}
	
	
	return video_info_maplist;
    }
    
    /**
     * 在htmltool中有功能类似的方法 没有采用
     * 替换字符串中的&amp; &quot; &nbsp; &lt; &gt;
     * @param str 要清除的字符串（StringBuffer）
     * @return 清除后的内容（StringBuffer）
     */
    public static StringBuffer ClearCode(StringBuffer str) {
	String s = str.toString();
	String s1 = s.replaceAll("&amp;", "|"); //备注 &amp; 应该是& 变动一下 替换成| 为了方便以后在拆分
	String s2 = s1.replaceAll("&quot;", "\"");
	String s3 = s2.replaceAll("&lt;", "<");
	String s4 = s3.replaceAll("&gt;", ">");
	String s5 = s4.replaceAll("&nbsp;", " ");
	
	StringBuffer sb = new StringBuffer(s5);
	return sb;
    }
}
