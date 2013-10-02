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
 * @name 图片的异步加载类
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
     * 重写构造函数
     * @param imageView 控件
     * @param url 头像url
     * @param id 视频id
     * @param tag 控件标签
     */
    public LoadingHanderImageAsyncTask(ImageView imageView, String url , String tag) {
	super();
	this.imageView = imageView;
	this.url = url;
	this.tag = tag;
}

    @Override
    protected String doInBackground(String... params) {
	// 添加如果是本地缓存的从本地读取...
	// 头像获取并保存
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
	    //开启后 会导致图片乱跳 
	    //估计不明原因的空指针也是这的原因
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
