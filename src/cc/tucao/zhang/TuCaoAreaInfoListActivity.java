/**
 * @author zhang.yangyang
 * @create_date 2013��7��24��
 * @last_edit_author 
 * @last_edit_date 
 * @edit_remark
 */
package cc.tucao.zhang;

import cc.tucao.zhang.asynctask.AllListAsyncTask;
import cc.tucao.zhang.finals.ServerFinals;
import cc.tucao.zhang.finals.SystemFinals;
import cc.tucao.zhang.tools.DebugLog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @name ����С���� ����
 * @author zhang.yangyang
 * @create_date 2013��7��24��
 * @edit_remark ���� ���ݵ� type ������ʲô����
 * Ŀǰ��֪���� ������Ϣ�Ļ�ȡ���� ʹ�õ�����ǰд�� ������ ���صĴ�����Ϣ���ڱ��ض����Ǹ���api�� code�жϵ�
 */
public class TuCaoAreaInfoListActivity extends ListActivity {
    private TextView tv_title_text;
    
    private LinearLayout ll_btn_title_more;
    private LinearLayout ll_title_more_ani;
    private TextView tv_title_more;
    private ImageView iv_btn_title_more;
    private Button btn_title_up;
    private Button btn_title_down;
    private Button btn_title_refresh;
    
    private Button btn_title_jump;
    private EditText edit_title_jump;
    
    private InputMethodManager imm;
    
    private ListView lv;
    
    private int star = 0;//��ʼ���
    private int type;//�������

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area_info_list);
        
        loading();
        
        init();
        
        setListener();
    }

    /**
     * �������
     */
    private void loading() {
	//��ȡ ��Ƶtype(�������)
	Intent intent = getIntent();
	type = intent.getIntExtra("type", 0);
	DebugLog.logDebug("type : "+type);
	
	ll_btn_title_more = (LinearLayout) findViewById(R.id.ll_btn_title_more);
	ll_title_more_ani = (LinearLayout) findViewById(R.id.ll_title_more_ani);
	tv_title_more = (TextView) findViewById(R.id.tv_title_more);
	iv_btn_title_more = (ImageView) findViewById(R.id.iv_btn_title_more);
	btn_title_up = (Button) findViewById(R.id.btn_title_up);
	btn_title_up.setEnabled(false);
	btn_title_up.setFocusable(false);
	btn_title_down = (Button) findViewById(R.id.btn_title_down);
	btn_title_refresh = (Button) findViewById(R.id.btn_title_refresh);
	
	tv_title_text = (TextView) findViewById(R.id.tv_title_text);
	
	btn_title_jump = (Button) findViewById(R.id.btn_title_jump);
	edit_title_jump = (EditText) findViewById(R.id.edit_title_jump);
	
	imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
	
	lv = TuCaoAreaInfoListActivity.this.getListView();
	
    }

    /**
     * ִ��
     */
    private void init() {
	new AllListAsyncTask(TuCaoAreaInfoListActivity.this, ServerFinals.UPDATE_INIT, type).execute();
    }
    
    /**
     * �����¼�
     */
    private void setListener() {
	if (type == SystemFinals.MAD_AMV) {
	    tv_title_text.setText(R.string.mad);
	}else if (type == SystemFinals.MMD) {
	    tv_title_text.setText(R.string.mmd);
	}else if (type == SystemFinals.ZONG_HE) {
	    tv_title_text.setText(R.string.zonghe);
	    
	}else if (type == SystemFinals.MUSIC_2) {
	    tv_title_text.setText(R.string.music_2);
	}else if (type == SystemFinals.FAN_CHANG) {
	    tv_title_text.setText(R.string.fanchang);
	}else if (type == SystemFinals.VOCALOID) {
	    tv_title_text.setText(R.string.vocaloid);
	}else if (type == SystemFinals.YAN_ZOU) {
	    tv_title_text.setText(R.string.yanzou);
	    
	}else if (type == SystemFinals.YING_XIANG) {
	    tv_title_text.setText(R.string.yingxiang);
	}else if (type == SystemFinals.JIE_SHUO) {
	    tv_title_text.setText(R.string.jieshuo);
	    
	}else if (type == SystemFinals.XI_WEN_LE_JIAN) {
	    tv_title_text.setText(R.string.xiwenlejian);
	}else if (type == SystemFinals.WU_DAO) {
	    tv_title_text.setText(R.string.wudao);
	}else if (type == SystemFinals.YU_LE_GUI_CHU) {
	    tv_title_text.setText(R.string.yuleguichu);
	}else if (type == SystemFinals.KE_XUE) {
	    tv_title_text.setText(R.string.kexue);
	    
	}else if (type == SystemFinals.DONG_HUA) {
	    tv_title_text.setText(R.string.donghua);
	}else if (type == SystemFinals.JU_CHANG) {
	    tv_title_text.setText(R.string.juchang);
	    
	}else if (type == SystemFinals.XIN_FAN_LIAN_ZAI) {
	    tv_title_text.setText(R.string.xinfanlianzai);
	}else if (type == SystemFinals.ZHONG_PEI_DONG_HUA) {
	    tv_title_text.setText(R.string.zhongpeidonghua);
	}else if (type == SystemFinals.NEW_SAN_CI_YUAN) {
	    tv_title_text.setText(R.string.newsanciyuan);
	    
	}else if (type == SystemFinals.ANIMATION) {
	    tv_title_text.setText(R.string.animation_1);
	}else if (type == SystemFinals.MUSIC) {
	    tv_title_text.setText(R.string.music_1);
	}else if (type == SystemFinals.GAME) {
	    tv_title_text.setText(R.string.game_1);
	}else if (type == SystemFinals.COMPLEX) {
	    tv_title_text.setText(R.string.sanciyuan_1);
	}else if (type == SystemFinals.COLLECTION) {
	    tv_title_text.setText(R.string.heji_1);
	}else if (type == SystemFinals.NEWANIMATION) {
	    tv_title_text.setText(R.string.xinfan_1);
	}
	
	//ƴ��"��ת��"�����õ�
	final String is = TuCaoAreaInfoListActivity.this.getResources().getString(R.string.to);
	final String jump_page = TuCaoAreaInfoListActivity.this.getResources().getString(R.string.jump_page);
	//��ת
	btn_title_jump.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		if (edit_title_jump.getText().toString().equals("0") || edit_title_jump.getText().toString().equals("")) {
		    star = (Integer.parseInt(tv_title_more.getText().toString().substring(1,tv_title_more.getText().length()-1)) - 1 ) * SystemFinals.LIST_ITEM_CONTENT;
		    
		    int page = star/SystemFinals.LIST_ITEM_CONTENT+1;
		    
		    isclick(page , v);
		}else {
		    star = (Integer.parseInt(edit_title_jump.getText().toString()) - 1) * SystemFinals.LIST_ITEM_CONTENT;
		    
		    int page = star/SystemFinals.LIST_ITEM_CONTENT+1;
		    tv_title_more.setText(is + page + jump_page);
		    
		    isclick(page , v);
		    
		    new AllListAsyncTask(TuCaoAreaInfoListActivity.this, ServerFinals.UPDATE_NEXT, type).execute(String.valueOf(star));
		}
	    }
	});
	//�б���
	lv.setOnItemClickListener(new OnItemClickListener() {

	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		TextView videoid = (TextView) view.findViewById(R.id.id);
		TextView catid = (TextView) view.findViewById(R.id.catid);
		TextView title = (TextView) view.findViewById(R.id.tv_video_title);//����
		TextView username = (TextView) view.findViewById(R.id.tv_video_username);//up��
		TextView description = (TextView) view.findViewById(R.id.tv_video_description);//���
		TextView keywords = (TextView) view.findViewById(R.id.tv_video_keywords);//TAG
		
		String videoinfoid = videoid.getText().toString();
		String videoinfocatid = catid.getText().toString();
		String videoinfotitle = title.getText().toString();
		String videoinfousername = username.getText().toString();
		String videoinfodescription = description.getText().toString();
		String videoinfokeywords = keywords.getText().toString();

		DebugLog.logInfo("id : " +videoinfoid);
		DebugLog.logInfo("catid : " +videoinfocatid);
		
		Intent intent = new Intent(TuCaoAreaInfoListActivity.this, VideoInfoActivity.class);
		intent.putExtra("videoinfoid", videoinfoid);
		intent.putExtra("videoinfocatid", videoinfocatid);
		intent.putExtra("videoinfotitle", videoinfotitle);
		intent.putExtra("videoinfousername", videoinfousername);
		intent.putExtra("videoinfodescription", videoinfodescription);
		intent.putExtra("videoinfokeywords", videoinfokeywords);
		startActivity(intent);
	    }
	});
	
	//�����˵�
	ll_btn_title_more.setOnClickListener(new OnClickListener() {
	    @Override
	    public void onClick(View v) {
		
		if (ll_title_more_ani.getVisibility() == View.GONE) {
		    ll_title_more_ani.setVisibility(View.VISIBLE);
		    ll_title_more_ani.setAnimation(AnimationUtils.loadAnimation(TuCaoAreaInfoListActivity.this, R.anim.push_in));
		    iv_btn_title_more.setImageResource(R.drawable.title_btn_more_down);
		}else {
		    ll_title_more_ani.setVisibility(View.GONE);
		    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
		    ll_title_more_ani.setAnimation(AnimationUtils.loadAnimation(TuCaoAreaInfoListActivity.this, R.anim.push_out));
		    iv_btn_title_more.setImageResource(R.drawable.title_btn_more);
		}
	    }
	});

	//ˢ�°�ť
	btn_title_refresh.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		star = 0;
		
		int page = star/SystemFinals.LIST_ITEM_CONTENT+1;
		tv_title_more.setText(is + page + jump_page);
		
		isclick(page , v);
		
		new AllListAsyncTask(TuCaoAreaInfoListActivity.this, ServerFinals.UPDATE_NEXT , type).execute(String.valueOf(star));
	    }
	});
	
	//��һҳ��ť
	btn_title_up.setOnClickListener(new OnClickListener() {
	    @Override
	    public void onClick(View v) {
		star = (Integer.parseInt(tv_title_more.getText().toString().substring(1,tv_title_more.getText().length()-1)) - 1 ) * SystemFinals.LIST_ITEM_CONTENT;
		star -= SystemFinals.LIST_ITEM_CONTENT;
		
		int page = star/SystemFinals.LIST_ITEM_CONTENT+1;
		tv_title_more.setText(is + page + jump_page);
		
		isclick(page , v);
		
		new AllListAsyncTask(TuCaoAreaInfoListActivity.this, ServerFinals.UPDATE_NEXT , type).execute(String.valueOf(star));
	    }
	});
	//��һҳ��ť
	btn_title_down.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		star = (Integer.parseInt(tv_title_more.getText().toString().substring(1,tv_title_more.getText().length()-1)) - 1 ) * SystemFinals.LIST_ITEM_CONTENT;
		star+=SystemFinals.LIST_ITEM_CONTENT;
		
		int page = star/SystemFinals.LIST_ITEM_CONTENT+1;
		tv_title_more.setText(is + page + jump_page);
		
		isclick(page , v);
		
		new AllListAsyncTask(TuCaoAreaInfoListActivity.this, ServerFinals.UPDATE_NEXT , type).execute(String.valueOf(star));
		}
	});
	
    }

    /**
     * ����һЩ�ظ��Ķ���
     * ���ø���˵��Ķ���Ч��
     * �ж� ��һҳ��ť�Ƿ���Ե�� 
     * ���ĸ��ఴ�椣������Σ�����Դ(�����η���)
     * ���ؽ��� �Լ�����
     * @param page ҳ��
     * @param v viwe
     */
    protected void isclick(int page , View v) {
	//�ж��Ƿ��ǵ�һҳ
	if (page == 1) {
	    btn_title_up.setEnabled(false);
	    btn_title_up.setFocusable(false);
	}else {
	    btn_title_up.setEnabled(true);
	    btn_title_up.setFocusable(true);
	}
	ll_title_more_ani.setVisibility(View.GONE);
	///���ؼ���
	imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
	
	ll_title_more_ani.setAnimation(AnimationUtils.loadAnimation(TuCaoAreaInfoListActivity.this, R.anim.push_out));
	iv_btn_title_more.setImageResource(R.drawable.title_btn_more);
    }
    
}
