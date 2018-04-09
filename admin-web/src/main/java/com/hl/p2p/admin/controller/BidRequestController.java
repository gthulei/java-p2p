package com.hl.p2p.admin.controller;

import com.hl.p2p.pojo.Bidrequest;
import com.hl.p2p.pojo.Bidrequestaudithistory;
import com.hl.p2p.query.BidRequestQueryObject;
import com.hl.p2p.server.*;
import com.hl.p2p.utils.JsonResult;
import com.hl.p2p.utils.RequireLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BidRequestController {

  @Autowired
  private IBidrequestServer bidrequestServer;

  @Autowired
  private IUserinfoServer userinfoServer;

  @Autowired
  private IRealauthServer realauthServer;

  @Autowired
  private IUserFileServer userFileServer;

  @Autowired
  private IBidrequestaudithistoryServer bidrequestaudithistoryServer;

  /**
   * 标审核列表
   * @param qo
   * @param model
   * @return
   */
  @RequireLogin
  @RequestMapping("/bidrequest_publishaudit_list")
  public String publishaudit(@ModelAttribute("qo") BidRequestQueryObject qo, Model model){
    qo.setQuertState(1);
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
  }

  @RequireLogin
  @RequestMapping("/borrow_info")
  public String borrowInfo(@RequestParam("id") Long id,Model model){
    Bidrequest result = bidrequestServer.get(id);
    // 标地信息
    model.addAttribute("bidRequest",result);
    // 标用户信息
    model.addAttribute("userInfo",userinfoServer.getUserinfoById(result.getCreateuser().getId()));
    // 标认证信息
    model.addAttribute("realAuth",realauthServer.getRealauthApplier(result.getCreateuser().getId()));
    // 风控信息
    model.addAttribute("userFiles",userFileServer.getUserFileByApplierList(result.getCreateuser().getId()));
    //审核历史
    List<Bidrequestaudithistory> historyList = bidrequestaudithistoryServer.getHistoryList();
    model.addAttribute("audits",historyList);
    return "borrow_info";
  }
}