/**
 * @author zhang.yangyang
 * @create_date 2013-6-27
 * @last_edit_author 
 * @last_edit_date 
 * @edit_remark
 */
package cc.tucao.zhang.type;

import java.util.HashMap;

/**
 * @name ��Ƶ�����Ϣ��
 * @author zhang.yangyang
 * @create_date 2013-6-27
 * @edit_remark �д�����������Ϣ ȫ����ɺ��������
 */
public class TuCaoListType {
    private String id; //ȥ��catid����Ƶid
    private String catid; //����id
    private String typeid;
    private String title; //����
    private String style;
    private String thumb; //����ͼ
    private String keywords; //�ؼ���
    private String description; //����
    private String posids;
    private String url; //��������ַ
    private String listorder;
    private String status;
    private String sysadd;
    private String username; //������
    private String inputtime; //¼��ʱ���
    private String updatetime; //����ʱ���
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCatid() {
        return catid;
    }
    public void setCatid(String catid) {
        this.catid = catid;
    }
    public String getTypeid() {
        return typeid;
    }
    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getStyle() {
        return style;
    }
    public void setStyle(String style) {
        this.style = style;
    }
    public String getThumb() {
        return thumb;
    }
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
    public String getKeywords() {
        return keywords;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPosids() {
        return posids;
    }
    public void setPosids(String posids) {
        this.posids = posids;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getListorder() {
        return listorder;
    }
    public void setListorder(String listorder) {
        this.listorder = listorder;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getSysadd() {
        return sysadd;
    }
    public void setSysadd(String sysadd) {
        this.sysadd = sysadd;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getInputtime() {
        return inputtime;
    }
    public void setInputtime(String inputtime) {
        this.inputtime = inputtime;
    }
    public String getUpdatetime() {
        return updatetime;
    }
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
    @Override
    public String toString() {
	return "TuCaoListType [id=" + id + ", catid=" + catid + ", typeid="
		+ typeid + ", title=" + title + ", style=" + style + ", thumb="
		+ thumb + ", keywords=" + keywords + ", description="
		+ description + ", posids=" + posids + ", url=" + url
		+ ", listorder=" + listorder + ", status=" + status
		+ ", sysadd=" + sysadd + ", username=" + username
		+ ", inputtime=" + inputtime + ", updatetime=" + updatetime
		+ ", getId()=" + getId() + ", getCatid()=" + getCatid()
		+ ", getTypeid()=" + getTypeid() + ", getTitle()=" + getTitle()
		+ ", getStyle()=" + getStyle() + ", getThumb()=" + getThumb()
		+ ", getKeywords()=" + getKeywords() + ", getDescription()="
		+ getDescription() + ", getPosids()=" + getPosids()
		+ ", getUrl()=" + getUrl() + ", getListorder()="
		+ getListorder() + ", getStatus()=" + getStatus()
		+ ", getSysadd()=" + getSysadd() + ", getUsername()="
		+ getUsername() + ", getInputtime()=" + getInputtime()
		+ ", getUpdatetime()=" + getUpdatetime() + ", getClass()="
		+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
		+ super.toString() + "]";
    }
    
    
    
    
    public static HashMap<String, String> toHashMap(TuCaoListType tuCaoType) {
	HashMap<String, String> hashMap = new HashMap<String, String>();
	hashMap.put("id", tuCaoType.getId());
	hashMap.put("catid",tuCaoType.getCatid());
	hashMap.put("typeid",tuCaoType.getTypeid());
	hashMap.put("title",tuCaoType.getTitle());
	hashMap.put("style",tuCaoType.getStyle());
	hashMap.put("thumb",tuCaoType.getThumb());
	hashMap.put("keywords",tuCaoType.getKeywords());
	hashMap.put("description",tuCaoType.getDescription());
	hashMap.put("posids",tuCaoType.getPosids());
	hashMap.put("url",tuCaoType.getUrl());
	hashMap.put("listorder",tuCaoType.getListorder());
	hashMap.put("status",tuCaoType.getStatus());
	hashMap.put("sysadd",tuCaoType.getSysadd());
	hashMap.put("username",tuCaoType.getUsername());
	hashMap.put("inputtime",tuCaoType.getInputtime());
	hashMap.put("updatetime",tuCaoType.getUpdatetime());
	
	return hashMap;
    }
}
