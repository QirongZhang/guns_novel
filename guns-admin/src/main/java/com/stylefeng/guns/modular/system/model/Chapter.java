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
 * @since 2018-03-28
 */
@TableName("bus_chapter")
public class Chapter extends Model<Chapter> {

    private static final long serialVersionUID = 1L;

    /**
     * 章节编码
     */
    @TableId("chapter_id")
    private String chapterId;
    /**
     * 小说编码
     */
    @TableField("novel_id")
    private Integer novelId;
    /**
     * 章节名称
     */
    @TableField("chapter_name")
    private String chapterName;
    /**
     * 章节内容
     */
    @TableField("chapter_content")
    private String chapterContent;
    /**
     * 字数
     */
    @TableField("word_count")
    private Integer wordCount;
    /**
     * 排序
     */
    @TableField("ord_by")
    private Integer ordBy;
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


    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getNovelId() {
        return novelId;
    }

    public void setNovelId(Integer novelId) {
        this.novelId = novelId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterContent() {
        return chapterContent;
    }

    public void setChapterContent(String chapterContent) {
        this.chapterContent = chapterContent;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public Integer getOrdBy() {
        return ordBy;
    }

    public void setOrdBy(Integer ordBy) {
        this.ordBy = ordBy;
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
        return this.chapterId;
    }

    @Override
    public String toString() {
        return "Chapter{" +
        "chapterId=" + chapterId +
        ", novelId=" + novelId +
        ", chapterName=" + chapterName +
        ", chapterContent=" + chapterContent +
        ", wordCount=" + wordCount +
        ", ordBy=" + ordBy +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        ", remarks=" + remarks +
        ", delFlag=" + delFlag +
        "}";
    }
}
