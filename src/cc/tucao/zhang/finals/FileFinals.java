/**
 * @author zhang.yangyang
 * @create_date 2013-6-24
 * @last_edit_author 
 * @last_edit_date 
 * @edit_remark
 */
package cc.tucao.zhang.finals;

import java.io.File;

import android.os.Environment;

/**
 * @name �ļ���س���
 * @author zhang.yangyang
 * @create_date 2013-6-24
 * @edit_remark 
 */
public class FileFinals {
    // �ڴ濨·��
    public static final String SDCARDROOT = Environment.getExternalStorageDirectory().getAbsolutePath();
    // ���ļ���
    public static final String SDCARDMAINPATH = "TuCao";
    // ����ͼƬר���ļ���
    public static final String SDCARDIMAGEPATH = SDCARDMAINPATH + File.separator + "imagefiles";
    // ������Ƶר���ļ���
    public static final String SDCARDVIDEOPATH = SDCARDMAINPATH + File.separator + "videofiles";
    
    //ͼƬ������չ��
    public static final String IMAGE_SYSTEM = ".img";
}
