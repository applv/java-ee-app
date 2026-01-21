package com.applv.sobes.wildflyapp.web.struts;

import static com.applv.sobes.wildflyapp.web.servlet.Constants.WEB_INF_JSP_DIR;
import static org.apache.struts2.action.Action.SUCCESS;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

@Namespace("/v2/search")
@ResultPath(WEB_INF_JSP_DIR)
public class CardSearchAction {

  @Action(results = {@Result(name = SUCCESS, location = "searchCards.jsp")})
  public String execute() {
    return SUCCESS;
  }
}
