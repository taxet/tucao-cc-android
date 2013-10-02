/**
 * @author zhang.yangyang
 * @create_date 2013-6-26
 * @last_edit_author 
 * @last_edit_date 
 * @edit_remark
 */
package cc.tucao.zhang;

import cc.tucao.zhang.finals.FileFinals;
import cc.tucao.zhang.tools.FileTool;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;

/**
 * @name ����ʱ��loading����
 * @author zhang.yangyang
 * @create_date 2013-6-26
 * @edit_remark
 */
public class LoadingActivity extends Activity {
    private LinearLayout linearLayout_loading = null;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.loading);
	//����ʱ����1
	handler = new Handler() {
	    public void handleMessage(android.os.Message msg) {
		switch (msg.what) {
		case 0:
		    // ��ת������
		    Intent intent = new Intent(LoadingActivity.this,
			    TuCaoAllAreasActivity.class);
		    startActivity(intent);
		    finish();
		    break;
		case 1:// ������
		    Intent intent1 = new Intent(LoadingActivity.this,
			    VideoViewAcitvity.class);
		    String url = Environment.getExternalStorageDirectory()
			    .getAbsolutePath() + "/test.flv";
		    intent1.putExtra("url", url);
		    startActivity(intent1);
		    finish();
		    break;
		}
	    }
	};

	AlphaAnimation loadingImageAlpha = new AlphaAnimation(0.1f, 1.0f);
	loadingImageAlpha.setDuration(2000);

	linearLayout_loading = (LinearLayout) findViewById(R.id.linearLayout_loading);
	linearLayout_loading.setAnimation(loadingImageAlpha);

	// ������������ִ��Loading����
	loadingImageAlpha.setAnimationListener(new AnimationListener() {
	    @Override
	    public void onAnimationStart(Animation animation) { }
	    @Override
	    public void onAnimationRepeat(Animation animation) { }
	    @Override
	    public void onAnimationEnd(Animation animation) {
		/**
		 * ����ִ����Ϻ�ִ��
		 */
		// �����ļ���
		FileTool fileTool = new FileTool();
		if (!fileTool.IsFileExist(FileFinals.SDCARDMAINPATH)) {// ��Ŀ¼�Ƿ���� �����ڴ���
		    fileTool.CreateSDDir(FileFinals.SDCARDMAINPATH);
		}
		if (!fileTool.IsFileExist(FileFinals.SDCARDIMAGEPATH)) {// ͼƬĿ¼�Ƿ���� �����ڴ���
		    fileTool.CreateSDDir(FileFinals.SDCARDIMAGEPATH);
		}
		// if (!fileTool.IsFileExist(FileFinals.SDCARDVIDEOPATH))
		// {//��ƵĿ¼�Ƿ���� �����ڴ���
		// fileTool.CreateSDDir(FileFinals.SDCARDVIDEOPATH);
		// }
		
		//loading��ͣ��2��
		new Thread() {
		    public void run() {
			try {
			    Thread.sleep(2000);
			} catch (InterruptedException e) {
			    e.printStackTrace();
			}
			//����ʱ����1
			handler.sendEmptyMessage(0);
		    }
		}.start();
	    }
	});
    }
}