/**
 * @author zhang.yangyang
 * @create_date 2013-6-27
 * @last_edit_author 
 * @last_edit_date 
 * @edit_remark
 */
package cc.tucao.zhang.asynctask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import cc.tucao.zhang.R;
import cc.tucao.zhang.finals.ServerFinals;
import cc.tucao.zhang.finals.SystemFinals;
import cc.tucao.zhang.model.TuCaoListModel;
import cc.tucao.zhang.simpleadapter.TuCaoListAdapter;
import cc.tucao.zhang.type.TuCaoListType;
import cc.tucao.zhang.var.PublicVariable;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * @name �����б���첽����
 * @author zhang.yangyang
 * @create_date 2013-6-27
 * @edit_remark  ������� 
 */
public class AllListAsyncTask extends AsyncTask<String, Integer, List<TuCaoListType>> {
    private ListActivity activity = null;
    private int type;
    private String action = "init";
    
    private String page = "0";
    private LinearLayout ll_progressbar;
    private LinearLayout ll_btn_title_more;
    
    private TuCaoListAdapter tucaoListAdapter;
    private List<HashMap<String, String>> listTuCaoHash = new ArrayList<HashMap<String,String>>();
    
    /**
     * �����б���첽����
     * @param activity
     * @param action ��������
     * @param type ������cat
     */
    public AllListAsyncTask(ListActivity activity , String action, int type){
	super();
	this.activity = activity;
	this.type = type;//�����жϷ��� 
	this.action = action;//�жϲ�������
	
	//����ʹ�õ���ͬһ�� ������ hashmap �ᵼ�� ��� ���޵����� ����֮���� ���ֻ����һ�� ��������� ���ݶ���
	PublicVariable.tuCaoListHash.clear();
    }
    
    /**
     * ��ʼ��ʱ����ǰ�Ĳ��� ��һЩ׼������
     */
    @Override
    protected void onPreExecute() {
	super.onPreExecute();
	ll_progressbar = (LinearLayout) activity.findViewById(R.id.ll_progressbar);
	ll_progressbar.setVisibility(View.VISIBLE);

	ll_btn_title_more = (LinearLayout) activity.findViewById(R.id.ll_btn_title_more);
	ll_btn_title_more.setClickable(false);
	ll_btn_title_more.setEnabled(false);
    }
    
    /**
     * �����ʱ�ܳ�������
     * һ������ params[0] ҳ��(ҳ�����ǵ�����1234 �Ǹ���ÿҳ��ʾ����Ŀ�� ���ݵ���ʼ��� һҳ10������0(0-9)�ڶ�ҳ�ʹ���10(10-19))
     */
    @Override
    protected List<TuCaoListType> doInBackground(String... params) {

	TuCaoListModel tclm = new TuCaoListModel(activity);
	List<TuCaoListType> tucaoList = new ArrayList<TuCaoListType>();
	
	//����У�� ��Ҫ����Ϊ ������վ�� code �жϴ�����Ϣ
	if (action.equals(ServerFinals.UPDATE_INIT)) {
	    tucaoList = tclm.getTuCaoListValue(type, page, SystemFinals.LIST_ITEM_CONTENT, "id");
	}else if (action.equals(ServerFinals.UPDATE_NEXT)) {
	    page = params[0];
	    tucaoList = tclm.getTuCaoListValue(type, page, SystemFinals.LIST_ITEM_CONTENT, "id");
	}
	
	/**
	 * ѭ������lisTuCaoTypes������ �ŵ� var���µ� public���������� ׼��adaptera����
	 */
	for (Iterator<TuCaoListType> iterator = tucaoList.iterator(); iterator.hasNext();) {
	    TuCaoListType tuCaoListType = iterator.next();
	    
	    HashMap<String, String> hmTuCao = TuCaoListType.toHashMap(tuCaoListType);
	    
	    PublicVariable.tuCaoListHash.put(String.valueOf(tuCaoListType.getId()), tuCaoListType);
	    
	    listTuCaoHash.add(hmTuCao);
	}
	
	
	tucaoListAdapter = new TuCaoListAdapter(
		activity, 
		listTuCaoHash, 
		R.layout.area_info_list_item,
		new String[] {}, 
		new int[] { });
	return tucaoList;
    }
    
    /**
     * ִ�е�����һЩ���� �ɲ���UI
     */
    @Override
    protected void onPostExecute(List<TuCaoListType> result) {
	super.onPostExecute(result);
	ll_btn_title_more.setClickable(true);
	ll_btn_title_more.setEnabled(true);
	ll_progressbar.setVisibility(View.GONE);

	if (result.size() == 0) {
	    if (!PublicVariable.isOnline.equals("")) {
		if (PublicVariable.isOnline.equals(SystemFinals.NETWORK_IS_WIFI) 
		 || PublicVariable.isOnline.equals(SystemFinals.NETWORK_IS_3G)) {
		    Toast.makeText(activity, R.string.network_noserves,
			    Toast.LENGTH_SHORT).show();
		    PublicVariable.isOnline = "";
		}else if (PublicVariable.isOnline.equals(SystemFinals.NETWORK_NONE)) {
		    Toast.makeText(activity, R.string.network_none,
			    Toast.LENGTH_SHORT).show();
		    PublicVariable.isOnline = "";
		}
	    }
	} else {
	    if (!PublicVariable.isOnline.equals("")) {
		if (PublicVariable.isOnline.equals(SystemFinals.NETWORK_IS_WIFI)) {
		    Toast.makeText(activity, R.string.network_is_wifi,
			    Toast.LENGTH_SHORT).show();
		    PublicVariable.isOnline = "";
		}
		if (PublicVariable.isOnline.equals(SystemFinals.NETWORK_IS_3G)) {
		    Toast.makeText(activity, R.string.network_is_3G,
			    Toast.LENGTH_SHORT).show();
		    PublicVariable.isOnline = "";
		}
		if (PublicVariable.isOnline.equals(SystemFinals.NETWORK_NONE)) {
		    Toast.makeText(activity, R.string.network_none,
			    Toast.LENGTH_SHORT).show();
		    PublicVariable.isOnline = "";
		}
	    }
	}
	activity.setListAdapter(tucaoListAdapter);
    }
    
}
