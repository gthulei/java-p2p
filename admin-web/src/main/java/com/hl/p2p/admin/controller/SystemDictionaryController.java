package com.hl.p2p.admin.controller;

import com.hl.p2p.pojo.Systemdictionary;
import com.hl.p2p.query.SystemdictionaryQueryObject;
import com.hl.p2p.server.ISystemdictionaryServer;
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
public class SystemDictionaryController {

  @Autowired
  private ISystemdictionaryServer systemdictionaryServer;

  @RequireLogin
  @RequestMapping("/systemDictionary_list")
  public String SystemDictionary(@ModelAttribute("qo") SystemdictionaryQueryObject qo, Model model){
    model.addAttribute("pageResult",systemdictionaryServer.selectSystemdictionary(qo));
    return "systemDictionary_list";
  }

  @RequireLogin
  @RequestMapping("/systemDictionaryItem_list")
  public String systemDictionaryItem_list(){
    return "systemDictionaryItem_list";
  }

  @RequireLogin
  @RequestMapping(value = "/systemDictionaryUpdate.json",method = RequestMethod.POST)
  @ResponseBody
  public JsonResult systemDictionaryUpdate(Systemdictionary systemd){
    boolean b = systemdictionaryServer.saveSystemdictionary(systemd);
    if (b){
      return JsonResult.resultSuccess("保存成功");
    }
    return JsonResult.resultError("0000019","保存失败");
  }

  @RequireLogin
  @RequestMapping(value = "/systemDictionaryIdDel.json",method = RequestMethod.POST)
  @ResponseBody
  public JsonResult systemDictionaryIdDel(String id){
    boolean b = systemdictionaryServer.systemDictionaryIdDel(id);
    if (b){
      return JsonResult.resultSuccess("删除成功");
    }
    return JsonResult.resultError("0000019","删除失败");
  }

}
