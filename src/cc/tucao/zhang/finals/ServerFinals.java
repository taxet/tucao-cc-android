/**
 * @author zhang.yangyang
 * @create_date 2013-6-24
 * @last_edit_author 
 * @last_edit_date 
 * @edit_remark
 */
package cc.tucao.zhang.finals;

/**
 * @name 远程服务相关常量
 * @author zhang.yangyang
 * @create_date 2013-6-24
 * @edit_remark 
 */
public class ServerFinals {
    	// 服务器地址
 	 public static final String TUCAO_HOST = " http://www.tucao.cc/";

 	// 远程接口常量
 	public static final String BEFORE_URL = TUCAO_HOST + "api_v1/";
 	
 	//apikey
 	public static final String APP_KEY = "EYiKRuBzmtcEQDavCQ";

 	// 初始化标签
 	public static final String UPDATE_INIT = "init";
 	
 	//下一页标签
 	public static final String UPDATE_NEXT = "next";
 	
 	//新浪视频 标签
 	public static final String VIDEO_TYPE_SINA = "sina";
 	//新浪网视频地址（除了vid）
 	public static final String SINA_VIDEO_URL = "http://v.iask.com/v_play.php?vid=";
}
