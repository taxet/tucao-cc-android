/**
 * @author zhang.yangyang
 * @create_date 2013��7��13��
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
 * @name ������Ƶ��Ϣ
 * @author zhang.yangyang
 * @create_date 2013��7��13��
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
	//��Ƶ��Ϣ�ͷ�p�б��л�
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
	//��Ƶ�б���
	video_info_lv.setOnItemClickListener(new OnItemClickListener() {
	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		TextView vid = (TextView) view.findViewById(R.id.video_id);
		TextView name = (TextView) view.findViewById(R.id.video_name);
		TextView type = (TextView) view.findViewById(R.id.video_type);
		DebugLog.logDebug("�����Ƶ�� " + type.getText() + " ��Ƶ��vid �� " + vid.getText() + " �������ǣ�" + name.getText());
		
		Intent intent = new Intent(VideoInfoActivity.this,VideoViewAcitvity.class);

		//֮��Ĺ������� ������
		//ע�� type ������ʱ������ ��վ���ݿ������
		//��ʱʹ������ж� ����
		//������ʽ����̫�� ���˵���Ƶ vidȫ������ 9λ(������᲻��������)
		//�ſ�ĳ�����13λ ��ĸ������
		//��Ѷ��Ƶ������11λ ��ĸ������
		if (vid.getText().toString().matches("\\d+\\.?\\d*")) {
		    //������Ƶ
		    url = ServerFinals.SINA_VIDEO_URL+vid.getText().toString();
		    DebugLog.logInfo("sina , url :" + url);
		    //���ݳ� url ���ҽ������е� data ��ȡ�� ��Ƶ��ַ ������ƵԴ��ַ ����������
		}else if (type.getText()!=null && vid.getText().length() == 13 ) {
		  //�ſ���Ƶ
		    DebugLog.logInfo("youku");
		    Toast.makeText(VideoInfoActivity.this, "�ſ�������Ƶ����û��Ū��",
			    Toast.LENGTH_SHORT).show();
		}else if (type.getText()!=null && vid.getText().length() == 11 ) {
		   //qq��Ƶ ֻ������һ��qq�� ���ܲ�׼ȷ
		    DebugLog.logInfo("qq");
		    Toast.makeText(VideoInfoActivity.this, "��Ѷ��Ƶ������Ƶ����û��Ū��",
			    Toast.LENGTH_SHORT).show();
		}else {
		    //ֻ�п��������ݿ������
		    url = "erro";
		}
		
		intent.putExtra("url", url);
		startActivity(intent);
	    }
	});
    }
    /**
     * ����
     */
    private void init() {
	video_description.setText(description);
	video_TAG.setText(keywords);
	video_title.setText(title);
	video_username.setText(username);
	//�����б�
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
	keywords = keywords.substring(4);//ȥ�� ��list ����ӵ� TAG: ��4���ַ� 
	
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
