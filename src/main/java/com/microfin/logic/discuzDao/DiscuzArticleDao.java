package com.microfin.logic.discuzDao;

import com.microfin.logic.entity.DiscuzArticle;
import com.microfin.logic.entity.User;

import java.util.List;

/**
 * 用户表操作
 *
 * @author manxiaolei
 */
public interface DiscuzArticleDao {

    List<DiscuzArticle> queryInfo(String key);

}
