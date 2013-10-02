/**
 * @author zhang.yangyang
 * @create_date 2013��7��13��
 * @last_edit_author 
 * @last_edit_date 
 * @edit_remark
 */
package cc.tucao.zhang.asynctask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import cc.tucao.zhang.R;
import cc.tucao.zhang.model.TuCaoModel;
import cc.tucao.zhang.tools.StringTool;
import cc.tucao.zhang.type.TuCaoType;

/**
 * @name ���е�����Ƶ��Ϣ���첽����
 * @author zhang.yangyang
 * @create_date 2013��7��13��
 * @edit_remark 
 */
public class AllVideoInfoAsyncTask extends AsyncTask<String, Integer,  TuCaoType> {
    private Activity activity;
    private String id;
    private String catid;
    private TuCaoType tuCaoType;
    
    private ImageView video_thumb;
    private ListView video_info_lv;
    private LinearLayout ll_progressbar;
    
    private SimpleAdapter adapter;
    
    private List<Map<String, String>> sourceList = new ArrayList<Map<String,String>>();
    
    public AllVideoInfoAsyncTask(Activity activity  , String catid , String id){
	super();
	this.activity = activity;
	this.id = id;
	this.catid = catid;
    }
    
    @Override
    protected void onPreExecute() {
	super.onPreExecute();
	video_thumb = (ImageView) activity.findViewById(R.id.video_thumb);
	
	ll_progressbar = (LinearLayout) activity.findViewById(R.id.ll_progressbar);
	ll_progressbar.setVisibility(View.VISIBLE);
	
	video_info_lv = (ListView) activity.findViewById(R.id.video_info_lv);
    }
    
    
    @Override
    protected TuCaoType doInBackground(String... params) {
	
	tuCaoType = new TuCaoType();
	TuCaoModel tcm = new TuCaoModel(activity);
	tuCaoType = tcm.getTuCaoValue(catid, id);

	return tuCaoType;
    }

    
    @Override
    protected void onPostExecute( TuCaoType result) {
	super.onPostExecute(result);
	//��� 
	//����ͼ
	video_thumb.setTag(result.getId());
	//���ǿ�ָ��
	//û����ͼƬ��ʱ�� ����ż������(��ָ��) ԭ��֪��
	if (!result.equals("")) {
	    if (!result.getThumb().equals("")) {
		LoadingHanderImageAsyncTask loadingHanderImageAsyncTask = new LoadingHanderImageAsyncTask(
			video_thumb, 
			result.getThumb(), 
			result.getId());
		loadingHanderImageAsyncTask.execute();
	    }
	}
	
	//���� ��ȡ��source ��ֳ���Ҫ�Ķ���
	sourceList = StringTool.GetTextForSource(result.getSource() , result.getTitle());

	adapter = new SimpleAdapter(
		activity, 
		sourceList, 
		R.layout.video_info_list_item, 
		new String[] {"number","name" , "type" , "vid"}, //type���� ������ vid�����ֵ� �϶���sina,13 λ��Ӧ���� youku,11λ�� Ӧ����qq
		new int[] {R.id.video_number , R.id.video_name , R.id.video_type,R.id.video_id });
	
	video_info_lv.setAdapter(adapter);
	
	ll_progressbar.setVisibility(View.GONE);
    }


}
