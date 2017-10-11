package com.microfin.logic.entity;

import java.io.Serializable;

/**
* 手刷品牌黑名单Bean
*
 * Created by manxiaolei on 2017/10/10.
 */
public class BlackList implements Serializable {

		/**
		 *
		 */
		private static final long serialVersionUID = -1405162837443853781L;

		private int id;
		/**公司名*/
		private String company;
		/**是否在黑名单中*/
		private String inblacklist;
		/**发布日期*/
		private String issdate;

		/**
		 * @return the id
		 */
		public int getId() {
			return id;
		}

		/**
		 * @param id
		 *            the id to set
		 */
		public void setId(int id) {
			this.id = id;
		}

		/**
		 * @return the company
		 */
		public String getCompany() {
			return company;
		}

		/**
		 * @param company
		 *            the company to set
		 */
		public void setCompany(String company) {
			this.company = company;
		}

		/**
		 * @return the issdate
		 */
		public String getIssdate() {
			return issdate;
		}

		/**
		 * @param issdate
		 *            the issdate to set
		 */
		public void setIssdate(String issdate) {
			this.issdate = issdate;
		}

		/**
		 * @return the inblacklist
		 */
		public String getInblacklist() {
			return inblacklist;
		}

		/**
		 * @param inblacklist
		 *            the inblacklist to set
		 */
		public void setInblacklist(String inblacklist) {
			this.inblacklist = inblacklist;
		}

}
