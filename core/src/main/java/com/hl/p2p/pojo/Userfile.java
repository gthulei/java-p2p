package com.hl.p2p.pojo;

public class Userfile extends BaseAuth{

    private Long score;

    private String file;

    private Long filetypeId;

    private Systemdictionaryitem filetype;

  public Systemdictionaryitem getFiletype() {
    return filetype;
  }

  public void setFiletype(Systemdictionaryitem filetype) {
    this.filetype = filetype;
  }

  public Userfile(int state, Logininfo applier, String file) {
    super(state, applier);
    this.file = file;
  }

  public Userfile() {
  }

  public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file == null ? null : file.trim();
    }

    public Long getFiletypeId() {
        return filetypeId;
    }

    public void setFiletypeId(Long filetypeId) {
        this.filetypeId = filetypeId;
    }
}