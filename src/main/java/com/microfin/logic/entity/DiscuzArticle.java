package com.microfin.logic.entity;

import com.microfin.common.util.StringUtil;

/**
 * Created by manxiaolei on 2018/1/4.
 */
public class DiscuzArticle {
	private String aid;
	private String author;
	private String title;
	private String summary;
	private String pic;
	private String url;
	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPic() {
		if(StringUtil.isNotEmpty(pic)){
			pic = "height:40vw;background-image:url(http://www.zfzj.cn/data/attachment/"+pic+")";
		}
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getUrl() {
		if(StringUtil.isNotEmpty(aid)){
			url ="http://www.zfzj.cn/portal.php?mod=view&aid="+aid;
		}
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
