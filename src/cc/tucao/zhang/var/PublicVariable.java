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
 * @name ���ñ���
 * @author zhang.yangyang
 * @create_date 2013-6-28
 * @edit_remark 
 */
public class PublicVariable {
    
    //�洢������Ƶ��Ϣ�Ĺ���  hash map
    public static HashMap<String, TuCaoType> tuCaoHash = new HashMap<String,TuCaoType>();
    //����Ƶ�б�Ĺ���  hash map
    public static HashMap<String , TuCaoListType> tuCaoListHash = new HashMap<String, TuCaoListType>();    
    
    //�ж��Ƿ�������
    public static String isOnline = "";
    
    //����ͼƬ�����ֵ�����ȣ���ȣ�
    public static int TOPIC_IMAGE_SHOW = 40;
    
    //�洢 ������Ƶҳ��� ��p�б� ��Ϣ��
    public static List<String> video_info_list = new ArrayList<String>();
}
