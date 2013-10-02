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
 * @Name ͼƬ���������
 * @author zhang.yue
 * @create_date 2011-11-15
 * @last_edit_author
 * @last_edit_date
 * @remark
 * @edit_remark �����ͼƬ��� û�����������չ�� ����ϵͳ��ͼƬ������ʾͼƬ ������.img��FileFinals.IMAGE_SYSTEM����չ��
 */
public class PicTool {

    /**
     * ��uri��ȡͼƬ�ļ�·�� 
     * @param uri uri����
     * @return ͼƬ·��
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
     * ����ͼƬ�ļ� 
     * @param url ͼƬurl��ַ
     * @return BitMap
     */
    public static Bitmap ReturnBitMap(String url) {
	String imgName = StringTool.GetImageNameForUrl(url);
	
	if (imgName.equals("")) {
	    return null;
	} else {
	    FileTool ft = new FileTool();
	    String path = FileFinals.SDCARDROOT + File.separator + FileFinals.SDCARDIMAGEPATH;
	    
	    // ͼƬ�ļ����ڲ��Ҳ���ˢ�µ�ʱ��
	    if (ft.IsFileExist(imgName+FileFinals.IMAGE_SYSTEM, FileFinals.SDCARDIMAGEPATH)) {
		return ReturnLocalBitMap(path + File.separator + imgName);
	    } else {
		return ReturnBitMap(url, imgName, path);
	    }
	}
    }

    /**
     * ���ػ�ȡͼƬ��Ϣ 
     * @param path ͼƬ·��
     * @return bitmap����
     */
    public static Bitmap ReturnLocalBitMap(String path) {
	Bitmap bitmap = BitmapFactory.decodeFile(path+FileFinals.IMAGE_SYSTEM);
	
	return bitmap;
    }

    /**
     * ץȡԶ��ͼƬ 
     * @param url ͼƬ��ַ
     * @param imgName ͼƬ��
     * @param path ��ַ
     * @return bitmap
     */
    public static Bitmap ReturnBitMap(String url, String imgName, String path) {

	Bitmap bitmap = null;
	try {
	    // ͼƬ��С�жϲ���
	    InputStream size_is = HTMLTool.GetHttpConnection(url)
		    .getInputStream();
	    BitmapFactory.Options op = new BitmapFactory.Options();
	    op.inJustDecodeBounds = true;
	    @SuppressWarnings("unused")
	    Bitmap size_bitmap = BitmapFactory.decodeStream(size_is, null, op);
	    op.inJustDecodeBounds = true; // ����ȡ�����Ϣ������������ͼƬ
	    boolean isop = false;
	    int size = PublicVariable.TOPIC_IMAGE_SHOW; // �趨��ȡ�Ĵ�С���ܳ����Ŀ��
	    int bili = 0; // ѹ������
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

	    // ���ݱ����ж�
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

	    // ������й�ѹ�������ش�����ͼƬ�����û��ֱ�Ӽ���
	    InputStream is = HTMLTool.GetHttpConnection(url).getInputStream();
	    if (isop) {
		op_new.inPreferredConfig = Bitmap.Config.ARGB_4444;
		op_new.inPurgeable = true;
		op_new.inInputShareable = true;
		bitmap = BitmapFactory.decodeStream(is, null, op_new);
	    } else {
		bitmap = BitmapFactory.decodeStream(is);
	    }

	    // ��ͼƬд�뵽�ڴ濨�У���Ϊ���棬����ֱ�ӱ��ض�ȡ
	    WriteBitmapToSdCard(FileFinals.SDCARDIMAGEPATH, path
		    + File.separator + imgName, bitmap);

	    is.close();

	} catch (Exception e) {
	    return null;
	}
	return bitmap;
    }

    /**
     * ����Bitmap��sd����
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
