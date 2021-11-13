package com.lagou.edu.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PromotionSpaceDTO {


    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 广告位key
     */
    private String spaceKey;

    private Date createTime;

    private Date updateTime;

    private Integer isDel;

    private List<PromotionAdDTO> adDTOList;

    public List<PromotionAdDTO> getAdDTOList() {
        return adDTOList;
    }

    public void setAdDTOList(List<PromotionAdDTO> adDTOList) {
        this.adDTOList = adDTOList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpaceKey() {
        return spaceKey;
    }

    public void setSpaceKey(String spaceKey) {
        this.spaceKey = spaceKey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}
