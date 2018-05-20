/**
 * 初始化书架管理详情对话框
 */
var BookshelfInfoDlg = {
    bookshelfInfoData : {}
};

/**
 * 清除数据
 */
BookshelfInfoDlg.clearData = function() {
    this.bookshelfInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BookshelfInfoDlg.set = function(key, val) {
    this.bookshelfInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BookshelfInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BookshelfInfoDlg.close = function() {
    parent.layer.close(window.parent.Bookshelf.layerIndex);
}

/**
 * 收集数据
 */
BookshelfInfoDlg.collectData = function() {
    this
    .set('bookshelfId')
    .set('userId')
    .set('novelId')
    .set('createBy')
    .set('createTime')
    .set('remarks')
    .set('delFlag');
}

/**
 * 提交添加
 */
BookshelfInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bookshelf/add", function(data){
        Feng.success("添加成功!");
        window.parent.Bookshelf.table.refresh();
        BookshelfInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bookshelfInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BookshelfInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bookshelf/update", function(data){
        Feng.success("修改成功!");
        window.parent.Bookshelf.table.refresh();
        BookshelfInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bookshelfInfoData);
    ajax.start();
}

$(function() {

});
