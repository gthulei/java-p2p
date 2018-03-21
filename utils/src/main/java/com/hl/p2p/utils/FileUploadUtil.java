package com.hl.p2p.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUploadUtil {

  public static List<String> fileUpload(MultipartFile[] file, HttpServletRequest request) {
    //获得物理路径webapp所在路径
    String pathRoot = request.getSession().getServletContext().getRealPath("");
    String path = "";
    List<String> list = new ArrayList<>();
    if (file.length > 0) {
      for (MultipartFile f : file) {
        if (!f.isEmpty()) {
          // 生成uuid作为文件名称
          String uuid = UUID.randomUUID().toString().replaceAll("-", "");
          // 获得文件类型（可以判断如果不是图片，禁止上传）
          String contentType = f.getContentType();
          // 获得文件后缀名称
          String imageName = contentType.substring(contentType.indexOf("/") + 1);
          path = "/static/images/" + uuid + "." + imageName;
          try {
            f.transferTo(new File(pathRoot + path));
            list.add(path);
          } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("图片上次失败");
          }
        }
      }
    }
    return list;
  }

}
