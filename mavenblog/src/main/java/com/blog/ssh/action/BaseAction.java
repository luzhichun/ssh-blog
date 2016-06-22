package com.blog.ssh.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
public class BaseAction extends ActionSupport {
    private String urlName;

    public BaseAction() {
        ActionInvocation ai = (ActionInvocation) ActionContext.getContext().get(ActionContext.ACTION_INVOCATION);
        this.urlName = ai.getProxy().getActionName();
    }

    public String getUrlName() {

        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }
}
