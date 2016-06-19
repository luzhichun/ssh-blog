package com.blog.ssh.vo;

import com.blog.ssh.pojo.Article;

/**
 * Created by wy on 2016/6/19 0019.
 */
public class ArticleVO extends Article{
    private int commentCount;

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
