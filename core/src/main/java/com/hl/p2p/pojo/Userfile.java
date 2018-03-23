package com.hl.p2p.pojo;

public class Userfile extends BaseAuth{

    private Long score;

    private String file;

    private Long filetypeId;

    public Userfile(int state, Long applierId, String file) {
        super(state, applierId);
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