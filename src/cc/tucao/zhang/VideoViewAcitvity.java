/**
 * @author zhang.yangyang
 * @create_date 2013年7月22日
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
 * @name 视频播放的activity
 * @author zhang.yangyang
 * @create_date 2013年7月22日
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
	    // 设置 VideoView 与 MediaController 建立联系
	    vv.setMediaController(mc);
	    // 让 VideoView 获取焦点。 
	    vv.requestFocus();
	    vv.start();// 开始播放
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
            //一般是数据库出错
            Toast.makeText(VideoViewAcitvity.this, "出错了 记住操作步骤 以及是哪个 视频联系我@zy405886431",
		    Toast.LENGTH_SHORT).show();
	    finish();
	}
    }
}
