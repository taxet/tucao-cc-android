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
 * @name 视频相关信息类
 * @author zhang.yangyang
 * @create_date 2013-6-27
 * @edit_remark 有大多出的垃圾信息 全部完成后在清理把
 */
public class TuCaoType {
    private String code;
    private String id;
    private String catid;
    private String typeid;
    private String title;
    private String style;
    private String thumb;
    private String keywords;
    private String description;
    private String posids;
    private String url;
    private String listorder;
    private String status;
    private String sysadd;
    private String username; 
    private String inputtime;
    private String updatetime;
    private String content;
    private String paginationtype;
    private String maxcharperpage;
    private String template;
    private String paytype;
    private String relation;
    private String source;
    private String part;
    private String time;
    private String yuanchuang;
    private String info;
    private String subtitle;

    
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
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
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getPaginationtype() {
        return paginationtype;
    }
    public void setPaginationtype(String paginationtype) {
        this.paginationtype = paginationtype;
    }
    public String getMaxcharperpage() {
        return maxcharperpage;
    }
    public void setMaxcharperpage(String maxcharperpage) {
        this.maxcharperpage = maxcharperpage;
    }
    public String getTemplate() {
        return template;
    }
    public void setTemplate(String template) {
        this.template = template;
    }
    public String getPaytype() {
        return paytype;
    }
    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }
    public String getRelation() {
        return relation;
    }
    public void setRelation(String relation) {
        this.relation = relation;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getPart() {
        return part;
    }
    public void setPart(String part) {
        this.part = part;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getYuanchuang() {
        return yuanchuang;
    }
    public void setYuanchuang(String yuanchuang) {
        this.yuanchuang = yuanchuang;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public String getSubtitle() {
        return subtitle;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    
    
    
    @Override
    public String toString() {
	return "TuCaoType [code=" + code + ", id=" + id + ", catid=" + catid
		+ ", typeid=" + typeid + ", title=" + title + ", style="
		+ style + ", thumb=" + thumb + ", keywords=" + keywords
		+ ", description=" + description + ", posids=" + posids
		+ ", url=" + url + ", listorder=" + listorder + ", status="
		+ status + ", sysadd=" + sysadd + ", username=" + username
		+ ", inputtime=" + inputtime + ", updatetime=" + updatetime
		+ ", content=" + content + ", paginationtype=" + paginationtype
		+ ", maxcharperpage=" + maxcharperpage + ", template="
		+ template + ", paytype=" + paytype + ", relation=" + relation
		+ ", source=" + source + ", part=" + part + ", time=" + time
		+ ", yuanchuang=" + yuanchuang + ", info=" + info
		+ ", subtitle=" + subtitle + "]";
    }
    
    
    public static HashMap<String, String> toHashMap(TuCaoType tuCaoType) {
	HashMap<String, String> hashMap = new HashMap<String, String>();
	hashMap.put("catid", tuCaoType.getCatid());
	hashMap.put("code",tuCaoType.getCode());
	hashMap.put("content",tuCaoType.getContent());
	hashMap.put("description",tuCaoType.getDescription());
	hashMap.put("id",tuCaoType.getId());
	hashMap.put("info",tuCaoType.getInfo());
	hashMap.put("inputtime",tuCaoType.getInputtime());
	hashMap.put("keywords",tuCaoType.getKeywords());
	hashMap.put("listorder",tuCaoType.getListorder());
	hashMap.put("maxcharperpage",tuCaoType.getMaxcharperpage());
	hashMap.put("paginationtype",tuCaoType.getPaginationtype());
	hashMap.put("part",tuCaoType.getPart());
	hashMap.put("paytype",tuCaoType.getPaytype());
	hashMap.put("posids",tuCaoType.getPosids());
	hashMap.put("relation",tuCaoType.getRelation());
	hashMap.put("source",tuCaoType.getSource());
	hashMap.put("status",tuCaoType.getStatus());
	hashMap.put("style",tuCaoType.getStyle());
	hashMap.put("subtitle",tuCaoType.getSubtitle());
	hashMap.put("sysadd",tuCaoType.getSysadd());
	hashMap.put("template",tuCaoType.getTemplate());
	hashMap.put("thumb",tuCaoType.getThumb());
	hashMap.put("time",tuCaoType.getTime());
	hashMap.put("title",tuCaoType.getTitle());
	hashMap.put("typeid",tuCaoType.getTypeid());
	hashMap.put("updatetime",tuCaoType.getUpdatetime());
	hashMap.put("url",tuCaoType.getUrl());
	hashMap.put("username",tuCaoType.getUsername());
	hashMap.put("yuanchuang",tuCaoType.getYuanchuang());
	
	return hashMap;
    }
}
