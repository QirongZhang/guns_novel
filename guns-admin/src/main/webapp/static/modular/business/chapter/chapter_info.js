/**
 * 初始化章节管理详情对话框
 */
var ChapterInfoDlg = {
    chapterInfoData : {},
    editor: null
};

/**
 * 清除数据
 */
ChapterInfoDlg.clearData = function() {
    this.chapterInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ChapterInfoDlg.set = function(key, val) {
    this.chapterInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ChapterInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ChapterInfoDlg.close = function() {
    parent.layer.close(window.parent.Chapter.layerIndex);
}

/**
 * 收集数据
 */
ChapterInfoDlg.collectData = function() {
    this
    .set('chapterId')
    .set('novelId')
    .set('chapterContent')
    .set('chapterName')
    .set('wordCount')
    .set('createBy')
    .set('createTime')
    .set('updateBy')
    .set('updateTime')
    .set('remarks')
    .set('delFlag');
    this.chapterInfoData['chapterContent'] = ChapterInfoDlg.editor.txt.html();
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    this.chapterInfoData['novelId'] = selected[0].novelId;
    this.chapterInfoData['ordBy'] = $('#ordBy').val();
}

/**
 * 提交添加
 */
ChapterInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/chapter/add", function(data){
        Feng.success("添加成功!");
        window.parent.Chapter.table.refresh();
        ChapterInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.chapterInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ChapterInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/chapter/update", function(data){
        Feng.success("修改成功!");
        window.parent.Chapter.table.refresh();
        ChapterInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.chapterInfoData);
    ajax.start();
}

$(function() {
    var E = window.wangEditor;
    var editor = new E('#editor');
    editor.create();
    editor.txt.html($("#chapterContent").val());
    ChapterInfoDlg.editor = editor;
});
