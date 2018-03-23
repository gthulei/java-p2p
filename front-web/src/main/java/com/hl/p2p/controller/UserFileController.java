package com.hl.p2p.controller;

import com.hl.p2p.pojo.Userfile;
import com.hl.p2p.server.ISystemdictionaryServer;
import com.hl.p2p.server.IUserFileServer;
import com.hl.p2p.utils.FileUploadUtil;
import com.hl.p2p.utils.JsonResult;
import com.hl.p2p.utils.RequireLogin;
import com.hl.p2p.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserFileController {

  @Autowired
  private IUserFileServer userFileServer;

  @Autowired
  private ISystemdictionaryServer systemdictionaryServer;

  @RequireLogin
  @RequestMapping("/userFile")
  public String UserFile(Model model){
    List<Userfile> result = userFileServer.getFiletype(UserContext.getCurrent().getId(),false);
    if(result.size()>0){
      model.addAttribute("userFiles",result);
      model.addAttribute("fileTypes",systemdictionaryServer.selectSystemdictionaryitemSn("userFileType"));
      return "userFiles_commit";
    }
    List<Userfile> vo = userFileServer.getFiletype(UserContext.getCurrent().getId(),true);
    model.addAttribute("userFiles",vo);
    return "userFiles";
  }

  @RequireLogin
  @RequestMapping(value = "/userFileApply.json",method = RequestMethod.POST)
  @ResponseBody
  public JsonResult userFileApply(@RequestParam("file") MultipartFile[] file, HttpServletRequest request){
    try {
      List<String> list = FileUploadUtil.fileUpload(file, request);
      if (list.size()<=0){
        return JsonResult.resultError("0000019","请选择图片");
      }
      userFileServer.addUserFile(list);
      return JsonResult.resultSuccess("图片上传成功");
    }catch (Exception e){
      return JsonResult.resultError("0000019",e.getMessage());
    }
  }

  @RequireLogin
  @RequestMapping(value = "/userFileSelectType.json",method = RequestMethod.POST)
  @ResponseBody
  public JsonResult userFileSelectType(Long[] id,Long[] filetypeId){
    try {
      userFileServer.updateUserFile(id,filetypeId);
      return JsonResult.resultSuccess("更新成功");
    }catch (Exception e){
      return JsonResult.resultError("0000019","更新失败");
    }
  }
}
