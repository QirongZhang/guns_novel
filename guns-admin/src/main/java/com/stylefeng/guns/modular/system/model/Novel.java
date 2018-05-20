package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng123
 * @since 2018-03-21
 */
@TableName("bus_novel")
public class Novel extends Model<Novel> {

    private static final long serialVersionUID = 1L;

    /**
     * 小说编码
     */
    @TableId("novel_id")
    private String novelId;
    /**
     * 小说名称
     */
    @TableField("novel_name")
    private String novelName;
    /**
     * 作者
     */
    private String author;
    /**
     * 简介
     */
    private String introduction;
    /**
     * 图片地址
     */
    @TableField("novel_pic")
    private String novelPic;
    /**
     * 点击量
     */
    @TableField("click_rate")
    private Integer clickRate;
    /**
     * 状态
     */
    private String state;
    /**
     * 类别编码
     */
    @TableField("category_id")
    private Integer categoryId;
    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 删除标记
     */
    @TableField("del_flag")
    private String delFlag;


    public String getNovelId() {
        return novelId;
    }

    public void setNovelId(String novelId) {
        this.novelId = novelId;
    }

    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getNovelPic() {
        return novelPic;
    }

    public void setNovelPic(String novelPic) {
        this.novelPic = novelPic;
    }

    public Integer getClickRate() {
        return clickRate;
    }

    public void setClickRate(Integer clickRate) {
        this.clickRate = clickRate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    protected Serializable pkVal() {
        return this.novelId;
    }

    @Override
    public String toString() {
        return "Novel{" +
        "novelId=" + novelId +
        ", novelName=" + novelName +
        ", author=" + author +
        ", introduction=" + introduction +
        ", novelPic=" + novelPic +
        ", clickRate=" + clickRate +
        ", state=" + state +
        ", categoryId=" + categoryId +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        ", remarks=" + remarks +
        ", delFlag=" + delFlag +
        "}";
    }
}
