/**
 * @author zhang.yangyang
 * @create_date 2013-7-3
 * @last_edit_author 
 * @last_edit_date 
 * @edit_remark
 */
package cc.tucao.zhang.asynctask;

import cc.tucao.zhang.tools.PicTool;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * @name ͼƬ���첽������
 * @author zhang.yangyang
 * @create_date 2013-7-3
 * @edit_remark 
 */
public class LoadingHanderImageAsyncTask extends AsyncTask<String, Integer, String>{

    private ImageView imageView = null;
    private String url = null;
    private Bitmap bt = null;
    private String tag = "";
    
    /**
     * ��д���캯��
     * @param imageView �ؼ�
     * @param url ͷ��url
     * @param id ��Ƶid
     * @param tag �ؼ���ǩ
     */
    public LoadingHanderImageAsyncTask(ImageView imageView, String url , String tag) {
	super();
	this.imageView = imageView;
	this.url = url;
	this.tag = tag;
}

    @Override
    protected String doInBackground(String... params) {
	// �������Ǳ��ػ���Ĵӱ��ض�ȡ...
	// ͷ���ȡ������
	if (url.equals("")) {
	    bt = null;
	} else {
	    bt = PicTool.ReturnBitMap(url);
	}
	
	return null;
    }

    @Override
    protected void onPostExecute(String result) {
	super.onPostExecute(result);

	if (bt == null) {
	    //������ �ᵼ��ͼƬ���� 
	    //���Ʋ���ԭ��Ŀ�ָ��Ҳ�����ԭ��
//	    imageView.setImageResource(R.drawable.exit_icon);
	} else {
	    if (imageView != null && !tag.equals("")) {
		if (imageView.getTag() != null && imageView.getTag().toString().equals(tag)) {
		    imageView.setImageDrawable(null);
		    imageView.setBackgroundDrawable(null);
		    imageView.setImageBitmap(bt);
		}
	    }
	}
    }
}
