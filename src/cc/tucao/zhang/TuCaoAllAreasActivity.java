/**
 * @author zhang.yangyang
 * @create_date 2013��7��24��
 * @last_edit_author 
 * @last_edit_date 
 * @edit_remark
 */
package cc.tucao.zhang;

import cc.tucao.zhang.finals.SystemFinals;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

/**
 * @name loading֮���ҳ�� ��ʾ���еķ���
 * @author zhang.yangyang
 * @create_date 2013��7��24��
 * @edit_remark �������֮���  
 * Ŀ��ʱ�ټ�һ��menu�˵�������
 * Ŀǰ��֪���� catid ���� ÿ��������´��ݵ�cat������ʲô ��ȡ���Ķ��������Ǹ��ϼ��� catid 10 �� 38 ����10 �õ��Ľ����38 ������ͬ�� 
 */
public class TuCaoAllAreasActivity extends Activity {
    private ImageView iv_btn_title_more;

    private Button btn_animation_1;
    private Button btn_animation_mad;
    private Button btn_animation_mmd;
    private Button btn_animation_zonghe;

    private Button btn_music_1;
    private Button btn_music_mucis_2;
    private Button btn_music_fanchang;
    private Button btn_music_vocaloid;
    private Button btn_music_yanzou;

    private Button btn_game_1;
    private Button btn_game_yingxiang;
    private Button btn_game_jieshuo;

    private Button btn_sanciyuan_1;
    private Button btn_sanciyuan_xiwenlejian;
    private Button btn_sanciyuan_wudao;
    private Button btn_sanciyuan_yuleguichu;
    private Button btn_sanciyuan_kexue;

    private Button btn_heji_1;
    private Button btn_heji_donghua;
    private Button btn_heji_juchang;

    private Button btn_xinfan_1;
    private Button btn_xinfan_xinfanlianzai;
    private Button btn_xinfan_zhongpeidonghua;
    private Button btn_xinfan_newsanciyuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.all_areas);

	loading();
	setListener();
    }

    private void setListener() {
	//�����
	btn_animation_1.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.ANIMATION);

		startActivity(intent);
	    }
	});
	btn_game_1.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.GAME);

		startActivity(intent);
	    }
	});
	btn_music_1.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.MUSIC);

		startActivity(intent);
	    }
	});
	btn_heji_1.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.COLLECTION);

		startActivity(intent);
	    }
	});
	btn_sanciyuan_1.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.COMPLEX);

		startActivity(intent);
	    }
	});
	btn_xinfan_1.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.NEWANIMATION);

		startActivity(intent);
	    }
	});
	// MAD
	btn_animation_mad.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.MAD_AMV);

		startActivity(intent);
	    }
	});
	// MMD
	btn_animation_mmd.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.MMD);

		startActivity(intent);
	    }
	});
	// �ۺ�
	btn_animation_zonghe.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.ZONG_HE);

		startActivity(intent);
	    }
	});
	// ����Ԫ����
	btn_music_mucis_2.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.MUSIC_2);

		startActivity(intent);
	    }
	});
	// ����
	btn_music_fanchang.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.FAN_CHANG);

		startActivity(intent);
	    }
	});
	// v��
	btn_music_vocaloid.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.VOCALOID);

		startActivity(intent);
	    }
	});
	// ����
	btn_music_yanzou.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.YAN_ZOU);

		startActivity(intent);
	    }
	});
	// ��ϷӰ��
	btn_game_yingxiang.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.YING_XIANG);

		startActivity(intent);
	    }
	});
	// ��Ϸ��˵
	btn_game_jieshuo.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.JIE_SHUO);

		startActivity(intent);
	    }
	});
	// ϲ���ּ�
	btn_sanciyuan_xiwenlejian.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.XI_WEN_LE_JIAN);

		startActivity(intent);
	    }
	});
	// �赸
	btn_sanciyuan_wudao.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.WU_DAO);

		startActivity(intent);
	    }
	});
	// ���ֹ���
	btn_sanciyuan_yuleguichu.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.YU_LE_GUI_CHU);

		startActivity(intent);
	    }
	});
	// ��ѧ
	btn_sanciyuan_kexue.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.KE_XUE);

		startActivity(intent);
	    }
	});
	// �����ϼ�
	btn_heji_donghua.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.DONG_HUA);

		startActivity(intent);
	    }
	});
	// �糡�ϼ�
	btn_heji_juchang.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.JU_CHANG);

		startActivity(intent);
	    }
	});
	// �·�����
	btn_xinfan_xinfanlianzai.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.XIN_FAN_LIAN_ZAI);

		startActivity(intent);
	    }
	});
	// ���䶯��
	btn_xinfan_zhongpeidonghua.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.ZHONG_PEI_DONG_HUA);

		startActivity(intent);
	    }
	});
	// �·�����Ԫ
	btn_xinfan_newsanciyuan.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(TuCaoAllAreasActivity.this,
			TuCaoAreaInfoListActivity.class);

		intent.putExtra("type", SystemFinals.NEW_SAN_CI_YUAN);

		startActivity(intent);
	    }
	});
    }

    /**
     * 
     */
    private void loading() {
	iv_btn_title_more = (ImageView) findViewById(R.id.iv_btn_title_more);
	iv_btn_title_more.setVisibility(View.GONE);

	btn_animation_1 = (Button) findViewById(R.id.btn_animation_1);
	btn_animation_mad = (Button) findViewById(R.id.btn_animation_mad);
	btn_animation_mmd = (Button) findViewById(R.id.btn_animation_mmd);
	btn_animation_zonghe = (Button) findViewById(R.id.btn_animation_zonghe);
	
	btn_music_1 = (Button) findViewById(R.id.btn_music_1);
	btn_music_mucis_2 = (Button) findViewById(R.id.btn_music_mucis_2);
	btn_music_fanchang = (Button) findViewById(R.id.btn_music_fanchang);
	btn_music_vocaloid = (Button) findViewById(R.id.btn_music_vocaloid);
	btn_music_yanzou = (Button) findViewById(R.id.btn_music_yanzou);

	btn_game_1 = (Button) findViewById(R.id.btn_game_1);
	btn_game_yingxiang = (Button) findViewById(R.id.btn_game_yingxiang);
	btn_game_jieshuo = (Button) findViewById(R.id.btn_game_jieshuo);

	btn_sanciyuan_1 = (Button) findViewById(R.id.btn_sanciyuan_1);
	btn_sanciyuan_xiwenlejian = (Button) findViewById(R.id.btn_sanciyuan_xiwenlejian);
	btn_sanciyuan_wudao = (Button) findViewById(R.id.btn_sanciyuan_wudao);
	btn_sanciyuan_yuleguichu = (Button) findViewById(R.id.btn_sanciyuan_yuleguichu);
	btn_sanciyuan_kexue = (Button) findViewById(R.id.btn_sanciyuan_kexue);

	btn_heji_1 = (Button) findViewById(R.id.btn_heji_1);
	btn_heji_donghua = (Button) findViewById(R.id.btn_heji_donghua);
	btn_heji_juchang = (Button) findViewById(R.id.btn_heji_juchang);

	btn_xinfan_1 = (Button) findViewById(R.id.btn_xinfan_1);
	btn_xinfan_xinfanlianzai = (Button) findViewById(R.id.btn_xinfan_xinfanlianzai);
	btn_xinfan_zhongpeidonghua = (Button) findViewById(R.id.btn_xinfan_zhongpeidonghua);
	btn_xinfan_newsanciyuan = (Button) findViewById(R.id.btn_xinfan_newsanciyuan);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
	// �������ذ�Ť,��ʾ�Ƿ�ȷ���˳�
	if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
		&& event.getAction() == KeyEvent.ACTION_DOWN
		&& event.getRepeatCount() == 0) {
	    boolean return_value = false;
	    show(0);
	    return return_value;
	}
	return super.dispatchKeyEvent(event);
    }
    /**
     * ��ʾdialog
     * @param i i=0�˳� i=1һЩ�򵥽���
     */
    private void show(int i) {
	AlertDialog.Builder mBuilder = new AlertDialog.Builder(
		    TuCaoAllAreasActivity.this);
	if (i==0) {
	    //ȷ���˳�
	    mBuilder.setIcon(R.drawable.exit_icon)
	    .setTitle(R.string.app_exit)
	    .setPositiveButton(R.string.ok,
		    new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog,
			int which) {
		    TuCaoAllAreasActivity.this.finish();
		}
	    }).setNegativeButton(R.string.no, null).create()
	    .show();
	}else if (i==1) {
	    //��ʾһЩ�򵥽���
	    mBuilder.setTitle(R.string.app_info).setMessage(R.string.app_info_text)
	    .setNegativeButton(R.string.ok, null).create()
	    .show();
	}
    }

    //�����Ĳ˵��¼�
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
	case R.id.menu_exit:
	    show(0);
	    break;

	case R.id.menu_info:
	    show(1);
	    break;
	}
        return super.onOptionsItemSelected(item);
    }
    //���������Ĳ˵�
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	getMenuInflater().inflate(R.menu.loading, menu);
	return true;
    }
}
