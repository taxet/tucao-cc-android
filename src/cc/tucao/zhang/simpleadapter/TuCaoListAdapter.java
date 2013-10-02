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
 * @name �����б�
 * @author zhang.yangyang
 * @create_date 2013-6-27
 * @edit_remark ����ͼƬ���� 
 */
public class TuCaoListAdapter extends SimpleAdapter {
    private Activity ctx;
    
    
    public View getView(int position, View convertView, ViewGroup parent) {
	ViewHolder vh;
	View view = null;
	if (convertView == null) {
	    view = LayoutInflater.from(ctx).inflate(R.layout.area_info_list_item, null);
	    vh = new ViewHolder();
	    vh.iv_video_thumb = (ImageView) view.findViewById(R.id.iv_video_thumb);//ͼƬ�ؼ�
	    vh.ll_video_thumb = (LinearLayout) view.findViewById(R.id.ll_video_thumb);//������������ͼ�ĵ���¼��� ��ʱ������������ͼ
	    vh.tv_video_description = (TextView) view.findViewById(R.id.tv_video_description);//���
	    vh.tv_video_keywords = (TextView) view.findViewById(R.id.tv_video_keywords);//TAG
	    vh.tv_video_updatetime = (TextView) view.findViewById(R.id.tv_video_updatetime);//����ʱ��
	    vh.tv_video_title = (TextView) view.findViewById(R.id.tv_video_title);//����
	    vh.tv_video_username = (TextView) view.findViewById(R.id.tv_video_username);//up��
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
     * �Կؼ���ֵ
     * @param view view����
     * @param position λ��
     * @param vh viewholder����
     */
    private void InitItemValues(View view, int position, ViewHolder vh) {
	@SuppressWarnings("unchecked")
	Map<String, String> mapTuCao = (Map<String, String>) getItem(position);

	TuCaoListType tucaoListType = PublicVariable.tuCaoListHash.get(mapTuCao.get("id"));

	if (PublicVariable.tuCaoListHash.size() > 0 && tucaoListType != null) {

	    tucaoListType = PublicVariable.tuCaoListHash.get(mapTuCao.get("id"));

	    String dateTime = StringTool.GetStrTime(tucaoListType.getUpdatetime(), "MM-dd HH:mm:ss");

	    //��ת���ݵ� id ��catid Ĭ�ϲ���ʾ
	    vh.id.setText(tucaoListType.getId()+"");
	    vh.catid.setText(tucaoListType.getCatid()+"");
	    // �о�����ܲ� ��tag������
	    vh.tv_video_keywords.setVisibility(View.GONE);
	    if (tucaoListType.getKeywords() != null) {
		vh.tv_video_keywords.setText(ctx.getText(R.string.TAG) + tucaoListType.getKeywords());
	    } else {
		vh.tv_video_keywords.setText(ctx.getText(R.string.TAG)+ "" + ctx.getText(R.string.none_TAG));
	    }
	    //�������
	    if (tucaoListType.getDescription() != null) {
		vh.tv_video_description.setText(tucaoListType.getDescription());
	    } else {
		vh.tv_video_description.setText(ctx.getText(R.string.none_description));
	    }
	    //�û���
	    vh.tv_video_username.setText(ctx.getText(R.string.up) + tucaoListType.getUsername());
	    //����
	    vh.tv_video_title.setText(tucaoListType.getTitle() + "");
	    //����ʱ��
	    vh.tv_video_updatetime.setText(ctx.getText(R.string.time) + dateTime);
	    
//	    //ʡ���� ͼƬgone�� �ж�д��
//	    vh.ll_video_thumb.setVisibility(View.GONE);
//	    vh.iv_video_thumb.setVisibility(View.GONE);
	    if (!tucaoListType.getThumb().equals("")) {
		
		vh.iv_video_thumb.setTag(tucaoListType.getId());
		//�첽����
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
	//���д������
	LinearLayout ll_video_thumb;
	ImageView iv_video_thumb;
	TextView tv_video_title;
	TextView tv_video_description;
	TextView tv_video_username;
	TextView tv_video_keywords;
	TextView tv_video_updatetime;
	TextView id;//����ʾ �洢id
	TextView catid;//����ʾ �洢catid
    }
}
