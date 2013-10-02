/**
 * @author zhang.yangyang
 * @create_date 2013-6-27
 * @last_edit_author 
 * @last_edit_date 
 * @edit_remark
 */
package cc.tucao.zhang.simpleadapter;

import java.util.List;
import java.util.Map;

import cc.tucao.zhang.R;
import cc.tucao.zhang.asynctask.LoadingHanderImageAsyncTask;
import cc.tucao.zhang.tools.StringTool;
import cc.tucao.zhang.type.TuCaoListType;
import cc.tucao.zhang.var.PublicVariable;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * @name 所有列表
 * @author zhang.yangyang
 * @create_date 2013-6-27
 * @edit_remark 新增图片加载 
 */
public class TuCaoListAdapter extends SimpleAdapter {
    private Activity ctx;
    
    
    public View getView(int position, View convertView, ViewGroup parent) {
	ViewHolder vh;
	View view = null;
	if (convertView == null) {
	    view = LayoutInflater.from(ctx).inflate(R.layout.area_info_list_item, null);
	    vh = new ViewHolder();
	    vh.iv_video_thumb = (ImageView) view.findViewById(R.id.iv_video_thumb);//图片控件
	    vh.ll_video_thumb = (LinearLayout) view.findViewById(R.id.ll_video_thumb);//用来设置缩略图的点击事件的 暂时用来隐藏缩略图
	    vh.tv_video_description = (TextView) view.findViewById(R.id.tv_video_description);//简介
	    vh.tv_video_keywords = (TextView) view.findViewById(R.id.tv_video_keywords);//TAG
	    vh.tv_video_updatetime = (TextView) view.findViewById(R.id.tv_video_updatetime);//更新时间
	    vh.tv_video_title = (TextView) view.findViewById(R.id.tv_video_title);//标题
	    vh.tv_video_username = (TextView) view.findViewById(R.id.tv_video_username);//up主
	    vh.id = (TextView) view.findViewById(R.id.id);
	    vh.catid = (TextView) view.findViewById(R.id.catid);
	    view.setTag(vh);
	}else {
	    view = convertView;
	    vh = (ViewHolder) convertView.getTag();
	}
	InitItemValues(view , position ,vh);
	return view;
    }
    
    
    
    /**
     * 对控件赋值
     * @param view view对象
     * @param position 位置
     * @param vh viewholder对象
     */
    private void InitItemValues(View view, int position, ViewHolder vh) {
	@SuppressWarnings("unchecked")
	Map<String, String> mapTuCao = (Map<String, String>) getItem(position);

	TuCaoListType tucaoListType = PublicVariable.tuCaoListHash.get(mapTuCao.get("id"));

	if (PublicVariable.tuCaoListHash.size() > 0 && tucaoListType != null) {

	    tucaoListType = PublicVariable.tuCaoListHash.get(mapTuCao.get("id"));

	    String dateTime = StringTool.GetStrTime(tucaoListType.getUpdatetime(), "MM-dd HH:mm:ss");

	    //中转数据的 id 和catid 默认不显示
	    vh.id.setText(tucaoListType.getId()+"");
	    vh.catid.setText(tucaoListType.getCatid()+"");
	    // 感觉界面很差 把tag隐藏了
	    vh.tv_video_keywords.setVisibility(View.GONE);
	    if (tucaoListType.getKeywords() != null) {
		vh.tv_video_keywords.setText(ctx.getText(R.string.TAG) + tucaoListType.getKeywords());
	    } else {
		vh.tv_video_keywords.setText(ctx.getText(R.string.TAG)+ "" + ctx.getText(R.string.none_TAG));
	    }
	    //简介内容
	    if (tucaoListType.getDescription() != null) {
		vh.tv_video_description.setText(tucaoListType.getDescription());
	    } else {
		vh.tv_video_description.setText(ctx.getText(R.string.none_description));
	    }
	    //用户名
	    vh.tv_video_username.setText(ctx.getText(R.string.up) + tucaoListType.getUsername());
	    //标题
	    vh.tv_video_title.setText(tucaoListType.getTitle() + "");
	    //更新时间
	    vh.tv_video_updatetime.setText(ctx.getText(R.string.time) + dateTime);
	    
//	    //省流量 图片gone掉 判断写反
//	    vh.ll_video_thumb.setVisibility(View.GONE);
//	    vh.iv_video_thumb.setVisibility(View.GONE);
	    if (!tucaoListType.getThumb().equals("")) {
		
		vh.iv_video_thumb.setTag(tucaoListType.getId());
		//异步加载
		LoadingHanderImageAsyncTask loadingHanderImageAsyncTask = new LoadingHanderImageAsyncTask(
			vh.iv_video_thumb, 
			tucaoListType.getThumb(), 
			String.valueOf(tucaoListType.getId()));
		loadingHanderImageAsyncTask.execute();
		
	    }
	}
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    /**
     * 
     * @param context
     * @param data
     * @param resource
     * @param from
     * @param to
     */
    public TuCaoListAdapter(Activity context, List<? extends Map<String, ?>> data,
	    int resource, String[] from, int[] to) {
	super(context, data, resource, from, to);
	this.ctx = context;
    }
    
    static class ViewHolder{
	//组件写在这里
	LinearLayout ll_video_thumb;
	ImageView iv_video_thumb;
	TextView tv_video_title;
	TextView tv_video_description;
	TextView tv_video_username;
	TextView tv_video_keywords;
	TextView tv_video_updatetime;
	TextView id;//不显示 存储id
	TextView catid;//不显示 存储catid
    }
}
