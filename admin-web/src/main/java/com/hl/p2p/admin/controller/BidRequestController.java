package com.hl.p2p.admin.controller;

import com.hl.p2p.query.BidRequestQueryObject;
import com.hl.p2p.server.IBidrequestServer;
import com.hl.p2p.utils.JsonResult;
import com.hl.p2p.utils.RequireLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BidRequestController {

  @Autowired
  private IBidrequestServer bidrequestServer;

  /**
   * 标审核列表
   * @param qo
   * @param model
   * @return
   */
  @RequireLogin
  @RequestMapping("/bidrequest_publishaudit_list")
  public String publishaudit(@ModelAttribute("qo") BidRequestQueryObject qo, Model model){
    model.addAttribute("pageResult",bidrequestServer.getApplyList(qo));
    return "publish_audit";
  }

  /**
   * 满标前审核
   * @param id
   * @param state
   * @return
   */
  @RequireLogin
  @RequestMapping(value = "/bidrequestPublishaudit.json",method = RequestMethod.POST)
  @ResponseBody
  public JsonResult bidrequestPublishaudit(Long id,int state,String remark){
    try {
      bidrequestServer.borrowFullAudit(id,state,remark);
      return JsonResult.resultSuccess("审核成功");
    }catch (Exception e){
      return JsonResult.resultError("0000015",e.getMessage());
    }
  };
}
