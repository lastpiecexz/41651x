package com.itfollowme.looklook.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by notre on 2018/5/15.
 */

public class MeiziResult implements Serializable {
  private boolean error;
  private List<MeiziPhoto> results;

  public boolean isError() {
    return error;
  }

  public void setError(boolean error) {
    this.error = error;
  }

  public List<MeiziPhoto> getResults() {
    return results;
  }

  public void setResults(List<MeiziPhoto> results) {
    this.results = results;
  }

  public static class MeiziPhoto implements Serializable{
    /*
    _id: "5a392689421aa90fe50c0293",
createdAt: "2017-12-19T22:47:37.468Z",
desc: "12-19",
publishedAt: "2017-12-25T08:28:04.64Z",
source: "chrome",
type: "福利",
url: "http://7xi8d6.com1.z0.glb.clouddn.com/20171219224721_wFH5PL_Screenshot.jpeg",
used: true,
who: "daimajia"
     */
    private String id;
    private Date createdAt;
    private String desc;
    private Date publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public Date getCreatedAt() {
      return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
      this.createdAt = createdAt;
    }

    public String getDesc() {
      return desc;
    }

    public void setDesc(String desc) {
      this.desc = desc;
    }

    public Date getPublishedAt() {
      return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
      this.publishedAt = publishedAt;
    }

    public String getSource() {
      return source;
    }

    public void setSource(String source) {
      this.source = source;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public boolean isUsed() {
      return used;
    }

    public void setUsed(boolean used) {
      this.used = used;
    }

    public String getWho() {
      return who;
    }

    public void setWho(String who) {
      this.who = who;
    }
  }
}
