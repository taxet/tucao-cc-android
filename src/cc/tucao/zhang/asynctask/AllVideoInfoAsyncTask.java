/**
 * @author zhang.yangyang
 * @create_date 2013年7月13日
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
 * @name 所有单个视频信息的异步加载
 * @author zhang.yangyang
 * @create_date 2013年7月13日
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
	//组件 
	//缩略图
	video_thumb.setTag(result.getId());
	//总是空指针
	//没加载图片的时候 还是偶尔报错(空指针) 原因不知道
	if (!result.equals("")) {
	    if (!result.getThumb().equals("")) {
		LoadingHanderImageAsyncTask loadingHanderImageAsyncTask = new LoadingHanderImageAsyncTask(
			video_thumb, 
			result.getThumb(), 
			result.getId());
		loadingHanderImageAsyncTask.execute();
	    }
	}
	
	//根据 获取的source 拆分出想要的东西
	sourceList = StringTool.GetTextForSource(result.getSource() , result.getTitle());

	adapter = new SimpleAdapter(
		activity, 
		sourceList, 
		R.layout.video_info_list_item, 
		new String[] {"number","name" , "type" , "vid"}, //type数据 不可信 vid是数字的 肯定是sina,13 位的应该是 youku,11位的 应该是qq
		new int[] {R.id.video_number , R.id.video_name , R.id.video_type,R.id.video_id });
	
	video_info_lv.setAdapter(adapter);
	
	ll_progressbar.setVisibility(View.GONE);
    }


}
