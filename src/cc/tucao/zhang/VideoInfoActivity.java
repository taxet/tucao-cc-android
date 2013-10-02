/**
 * @author zhang.yangyang
 * @create_date 2013年7月13日
 * @last_edit_author 
 * @last_edit_date 
 * @edit_remark
 */
package cc.tucao.zhang;

import cc.tucao.zhang.asynctask.AllVideoInfoAsyncTask;
import cc.tucao.zhang.finals.ServerFinals;
import cc.tucao.zhang.tools.DebugLog;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @name 单个视频信息
 * @author zhang.yangyang
 * @create_date 2013年7月13日
 * @edit_remark 
 */
public class VideoInfoActivity extends Activity {
    private String id;
    private String catid;
    private String title;
    private String username;
    private String description;
    private String keywords;
    
    private TextView video_title;
    private TextView video_username;
    private TextView video_description;
    private TextView video_TAG;
    private Button video_info_button;
    private LinearLayout ll_video_info_source;
    private ScrollView sv_video_info_scrollview;
    private Button video_info_button_back;
    
    private ListView video_info_lv;
    
    private String url;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_info);
        
        loading(this);
        
        init();
        
        onclick();
    }

    
    /**
     * 
     */
    private void onclick() {
	//视频信息和分p列表切换
	video_info_button_back.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		sv_video_info_scrollview.setVisibility(View.VISIBLE);
		ll_video_info_source.setVisibility(View.GONE);
	    }
	});
	video_info_button.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		sv_video_info_scrollview.setVisibility(View.GONE);
		ll_video_info_source.setVisibility(View.VISIBLE);
		
	    }
	});
	//视频列表点击
	video_info_lv.setOnItemClickListener(new OnItemClickListener() {
	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		TextView vid = (TextView) view.findViewById(R.id.video_id);
		TextView name = (TextView) view.findViewById(R.id.video_name);
		TextView type = (TextView) view.findViewById(R.id.video_type);
		DebugLog.logDebug("这个视频是 " + type.getText() + " 视频，vid 是 " + vid.getText() + " ，标题是：" + name.getText());
		
		Intent intent = new Intent(VideoInfoActivity.this,VideoViewAcitvity.class);

		//之后的工作就是 播放器
		//注意 type 数据暂时不可信 网站数据库的问题
		//暂时使用这个判定 待定
		//正则表达式不是太懂 新浪的视频 vid全是数字 9位(不清楚会不会在增加)
		//优酷的长度是13位 字母加数字
		//腾讯视频长度是11位 字母加数字
		if (vid.getText().toString().matches("\\d+\\.?\\d*")) {
		    //新浪视频
		    url = ServerFinals.SINA_VIDEO_URL+vid.getText().toString();
		    DebugLog.logInfo("sina , url :" + url);
		    //传递出 url 并且解析其中的 data 获取到 视频地址 传递视频源地址 启动播放器
		}else if (type.getText()!=null && vid.getText().length() == 13 ) {
		  //优酷视频
		    DebugLog.logInfo("youku");
		    Toast.makeText(VideoInfoActivity.this, "优酷网的视频解析没有弄好",
			    Toast.LENGTH_SHORT).show();
		}else if (type.getText()!=null && vid.getText().length() == 11 ) {
		   //qq视频 只见到过一次qq的 可能不准确
		    DebugLog.logInfo("qq");
		    Toast.makeText(VideoInfoActivity.this, "腾讯视频网的视频解析没有弄好",
			    Toast.LENGTH_SHORT).show();
		}else {
		    //只有可能是数据库出问题
		    url = "erro";
		}
		
		intent.putExtra("url", url);
		startActivity(intent);
	    }
	});
    }
    /**
     * 加载
     */
    private void init() {
	video_description.setText(description);
	video_TAG.setText(keywords);
	video_title.setText(title);
	video_username.setText(username);
	//加载列表
	new AllVideoInfoAsyncTask(VideoInfoActivity.this, catid, id).execute();
    }

    /**
     * @param activity 
     */
    private void loading(Activity activity) {
	Intent intent = getIntent();
	id = intent.getStringExtra("videoinfoid");
	catid = intent.getStringExtra("videoinfocatid");
	
	title = intent.getStringExtra("videoinfotitle");
	username = intent.getStringExtra("videoinfousername");
	
	description = intent.getStringExtra("videoinfodescription");
	
	keywords = intent.getStringExtra("videoinfokeywords");
	keywords = keywords.substring(4);//去掉 在list 中添加的 TAG: 这4个字符 
	
	video_description = (TextView) findViewById(R.id.video_description);
	video_TAG = (TextView) findViewById(R.id.video_TAG);
	video_title = (TextView) findViewById(R.id.video_title);
	video_username = (TextView) findViewById(R.id.video_username);

	video_info_lv = (ListView) findViewById(R.id.video_info_lv);
	
	video_info_button = (Button) findViewById(R.id.video_info_button);
	sv_video_info_scrollview = (ScrollView) findViewById(R.id.sv_video_info_scrollview);
	ll_video_info_source = (LinearLayout) findViewById(R.id.ll_video_info_source);
	video_info_button_back = (Button) findViewById(R.id.video_info_button_back);
    }

}
