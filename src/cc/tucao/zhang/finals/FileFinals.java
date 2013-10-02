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
 * @name 文件相关常量
 * @author zhang.yangyang
 * @create_date 2013-6-24
 * @edit_remark 
 */
public class FileFinals {
    // 内存卡路径
    public static final String SDCARDROOT = Environment.getExternalStorageDirectory().getAbsolutePath();
    // 主文件夹
    public static final String SDCARDMAINPATH = "TuCao";
    // 缓存图片专用文件夹
    public static final String SDCARDIMAGEPATH = SDCARDMAINPATH + File.separator + "imagefiles";
    // 缓存视频专用文件夹
    public static final String SDCARDVIDEOPATH = SDCARDMAINPATH + File.separator + "videofiles";
    
    //图片缓存扩展名
    public static final String IMAGE_SYSTEM = ".img";
}
