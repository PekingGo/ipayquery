package com.microfin.logic.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by manxiaolei on 2017/10/26.
 */
public class QueryLog{
	private  long id ;
	private  String key_word;
	private  String category;
	private  String ip;
	private Date op_time;

	public QueryLog(String key_word,String category,String ip,Date op_time){
		this.key_word = key_word;
		this.category = category;
		this.ip=ip;
		this.op_time=op_time;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKey_word() {
		return key_word;
	}

	public void setKey_word(String key_word) {
		this.key_word = key_word;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getOp_time() {
		return op_time;
	}

	public void setOp_time(Date op_time) {
		this.op_time = op_time;
	}
}
