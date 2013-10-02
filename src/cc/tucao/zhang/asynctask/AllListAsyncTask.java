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
 * @name 所有列表的异步加载
 * @author zhang.yangyang
 * @create_date 2013-6-27
 * @edit_remark  整理代码 
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
     * 所有列表的异步加载
     * @param activity
     * @param action 操作类型
     * @param type 分区的cat
     */
    public AllListAsyncTask(ListActivity activity , String action, int type){
	super();
	this.activity = activity;
	this.type = type;//用来判断分区 
	this.action = action;//判断操作类型
	
	//由于使用的是同一个 公共的 hashmap 会导致 这个 无限的增大 测试之后发现 这段只调用一次 就在这清空 （暂定）
	PublicVariable.tuCaoListHash.clear();
    }
    
    /**
     * 开始耗时任务前的步骤 做一些准备工作
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
     * 处理耗时很长的任务
     * 一个参数 params[0] 页数(页数不是单纯的1234 是根据每页显示的条目数 传递的起始序号 一页10个传递0(0-9)第二页就传递10(10-19))
     */
    @Override
    protected List<TuCaoListType> doInBackground(String... params) {

	TuCaoListModel tclm = new TuCaoListModel(activity);
	List<TuCaoListType> tucaoList = new ArrayList<TuCaoListType>();
	
	//错误校验 需要更正为 根据网站的 code 判断错误信息
	if (action.equals(ServerFinals.UPDATE_INIT)) {
	    tucaoList = tclm.getTuCaoListValue(type, page, SystemFinals.LIST_ITEM_CONTENT, "id");
	}else if (action.equals(ServerFinals.UPDATE_NEXT)) {
	    page = params[0];
	    tucaoList = tclm.getTuCaoListValue(type, page, SystemFinals.LIST_ITEM_CONTENT, "id");
	}
	
	/**
	 * 循环读出lisTuCaoTypes的数据 放到 var包下的 public公共变量里 准备adaptera调用
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
     * 执行到最后的一些调用 可操作UI
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
