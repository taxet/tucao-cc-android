/**
 * @author zhang.yangyang
 * @create_date 2013-6-28
 * @last_edit_author 
 * @last_edit_date 
 * @edit_remark
 */
package cc.tucao.zhang.var;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cc.tucao.zhang.type.TuCaoListType;
import cc.tucao.zhang.type.TuCaoType;

/**
 * @name 公用变量
 * @author zhang.yangyang
 * @create_date 2013-6-28
 * @edit_remark 
 */
public class PublicVariable {
    
    //存储单个视频信息的公共  hash map
    public static HashMap<String, TuCaoType> tuCaoHash = new HashMap<String,TuCaoType>();
    //存视频列表的公共  hash map
    public static HashMap<String , TuCaoListType> tuCaoListHash = new HashMap<String, TuCaoListType>();    
    
    //判断是否有网络
    public static String isOnline = "";
    
    //设置图片的最大值（长度，宽度）
    public static int TOPIC_IMAGE_SHOW = 40;
    
    //存储 单个视频页面的 分p列表 信息的
    public static List<String> video_info_list = new ArrayList<String>();
}
