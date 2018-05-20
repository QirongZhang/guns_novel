package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("bus_bookshelf")
public class Bookshelf extends Model<Bookshelf> {

    private static final long serialVersionUID = 1L;

    /**
     * 书架编码
     */
    @TableId(value = "bookshelf_id", type = IdType.AUTO)
    private Integer bookshelfId;
    /**
     * 用户编码
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 小说编码
     */
    @TableField("novel_id")
    private Integer novelId;
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
     * 备注
     */
    private String remarks;
    /**
     * 删除标记
     */
    @TableField("del_flag")
    private String delFlag;


    public Integer getBookshelfId() {
        return bookshelfId;
    }

    public void setBookshelfId(Integer bookshelfId) {
        this.bookshelfId = bookshelfId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNovelId() {
        return novelId;
    }

    public void setNovelId(Integer novelId) {
        this.novelId = novelId;
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
        return this.bookshelfId;
    }

    @Override
    public String toString() {
        return "Bookshelf{" +
        "bookshelfId=" + bookshelfId +
        ", userId=" + userId +
        ", novelId=" + novelId +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", remarks=" + remarks +
        ", delFlag=" + delFlag +
        "}";
    }
}
