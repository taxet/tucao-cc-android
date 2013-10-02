package cc.tucao.zhang.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import cc.tucao.zhang.finals.FileFinals;
import cc.tucao.zhang.var.PublicVariable;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * @Name 图片处理相关类
 * @author zhang.yue
 * @create_date 2011-11-15
 * @last_edit_author
 * @last_edit_date
 * @remark
 * @edit_remark 缓存的图片如果 没加上特殊的扩展名 会在系统的图片库中显示图片 加上了.img（FileFinals.IMAGE_SYSTEM）扩展名
 */
public class PicTool {

    /**
     * 从uri获取图片文件路径 
     * @param uri uri对象
     * @return 图片路径
     */
    public static String GetLocalImagePathForURI(Uri uri, Activity activity) {
	String[] projection = { MediaStore.Images.Media.DATA };
	Cursor cursor = activity
		.managedQuery(uri, projection, null, null, null);
	if (cursor != null) {
	    // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
	    // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
	    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	    cursor.moveToFirst();
	    return cursor.getString(column_index);
	} else
	    return null;
    }

    /**
     * 返回图片文件 
     * @param url 图片url地址
     * @return BitMap
     */
    public static Bitmap ReturnBitMap(String url) {
	String imgName = StringTool.GetImageNameForUrl(url);
	
	if (imgName.equals("")) {
	    return null;
	} else {
	    FileTool ft = new FileTool();
	    String path = FileFinals.SDCARDROOT + File.separator + FileFinals.SDCARDIMAGEPATH;
	    
	    // 图片文件存在并且不是刷新的时候
	    if (ft.IsFileExist(imgName+FileFinals.IMAGE_SYSTEM, FileFinals.SDCARDIMAGEPATH)) {
		return ReturnLocalBitMap(path + File.separator + imgName);
	    } else {
		return ReturnBitMap(url, imgName, path);
	    }
	}
    }

    /**
     * 本地获取图片信息 
     * @param path 图片路径
     * @return bitmap对象
     */
    public static Bitmap ReturnLocalBitMap(String path) {
	Bitmap bitmap = BitmapFactory.decodeFile(path+FileFinals.IMAGE_SYSTEM);
	
	return bitmap;
    }

    /**
     * 抓取远程图片 
     * @param url 图片地址
     * @param imgName 图片名
     * @param path 地址
     * @return bitmap
     */
    public static Bitmap ReturnBitMap(String url, String imgName, String path) {

	Bitmap bitmap = null;
	try {
	    // 图片大小判断操作
	    InputStream size_is = HTMLTool.GetHttpConnection(url)
		    .getInputStream();
	    BitmapFactory.Options op = new BitmapFactory.Options();
	    op.inJustDecodeBounds = true;
	    @SuppressWarnings("unused")
	    Bitmap size_bitmap = BitmapFactory.decodeStream(size_is, null, op);
	    op.inJustDecodeBounds = true; // 仅获取宽高信息，不加载整个图片
	    boolean isop = false;
	    int size = PublicVariable.TOPIC_IMAGE_SHOW; // 设定获取的大小不能超过的宽高
	    int bili = 0; // 压缩比例
	    BitmapFactory.Options op_new = new BitmapFactory.Options();
	    if (op.outWidth > op.outHeight) {
		if (op.outWidth > size) {
		    bili = op.outWidth / size;
		    isop = true;
		}
	    } else {
		if (op.outHeight > size) {
		    bili = op.outHeight / size;

		    isop = true;
		}
	    }

	    // 根据比例判断
	    if (bili != 0) {
		if (bili < 2) {
		    bili = 2;
		} else {
		    bili = bili * 2 - 2;
		}
		if (bili % 2 != 0) {
		    bili = bili + 1;
		}
		op_new.inSampleSize = bili;
	    } else {
		isop = false;
	    }
	    size_bitmap = null;
	    size_is.close();

	    // 如果进行过压缩，加载处理后的图片，如果没有直接加载
	    InputStream is = HTMLTool.GetHttpConnection(url).getInputStream();
	    if (isop) {
		op_new.inPreferredConfig = Bitmap.Config.ARGB_4444;
		op_new.inPurgeable = true;
		op_new.inInputShareable = true;
		bitmap = BitmapFactory.decodeStream(is, null, op_new);
	    } else {
		bitmap = BitmapFactory.decodeStream(is);
	    }

	    // 将图片写入到内存卡中，做为缓存，将来直接本地读取
	    WriteBitmapToSdCard(FileFinals.SDCARDIMAGEPATH, path
		    + File.separator + imgName, bitmap);

	    is.close();

	} catch (Exception e) {
	    return null;
	}
	return bitmap;
    }

    /**
     * 保存Bitmap到sd卡中
     * @param path
     * @param img_path
     * @param bitmap
     */
    public static void WriteBitmapToSdCard(String path, String img_path, Bitmap bitmap) {
	FileTool ft = new FileTool();
	ft.CreateSDDir(path);
	FileOutputStream out = null;
	try {
	    out = new FileOutputStream(img_path+FileFinals.IMAGE_SYSTEM);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
    }
}
