/**
 * @author zhang.yangyang
 * @create_date 2013��7��22��
 * @last_edit_author 
 * @last_edit_date 
 * @edit_remark
 */
package cc.tucao.zhang;

import java.io.File;

import cc.tucao.zhang.tools.DebugLog;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * @name ��Ƶ���ŵ�activity
 * @author zhang.yangyang
 * @create_date 2013��7��22��
 * @edit_remark 
 */
public class VideoViewAcitvity extends Activity{
    private String url;
    
    private VideoView vv;
    private MediaController  mc;
    private File file ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_layout);
        
        init();

        loading();
    }
    /**
     * 
     */
    private void loading() {
	vv = (VideoView) findViewById(R.id.vv_video_view);
	mc = new MediaController(this);
	file = new File(url);
	getWindow().setFormat(PixelFormat.TRANSLUCENT);
	if (file.exists()) {
	    vv.setVideoPath(file .getAbsolutePath());
	    // ���� VideoView �� MediaController ������ϵ
	    vv.setMediaController(mc);
	    // �� VideoView ��ȡ���㡣 
	    vv.requestFocus();
	    vv.start();// ��ʼ����
	}

    }
    /**
     * 
     */
    private void init() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        DebugLog.logError("url : " + url);
        if (url.equals("erro")) {
            //һ�������ݿ����
            Toast.makeText(VideoViewAcitvity.this, "������ ��ס�������� �Լ����ĸ� ��Ƶ��ϵ��@zy405886431",
		    Toast.LENGTH_SHORT).show();
	    finish();
	}
    }
}
