/**
 * 章节管理管理初始化
 */
var Chapter = {
    id: "ChapterTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Chapter.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '章节编码', field: 'chapterId', visible: false, align: 'center', valign: 'middle'},
            {title: '顺序', field: 'ordBy', visible: true, align: 'center', valign: 'middle'},
            {title: '小说编码', field: 'novelId', visible: false, align: 'center', valign: 'middle'},
            {title: '章节内容', field: 'chapterContent', visible: false, align: 'center', valign: 'middle'},
            {title: '章节名', field: 'chapterName', visible: true, align: 'center', valign: 'middle'},
            {title: '字数', field: 'wordCount', visible: true, align: 'center', valign: 'middle'},
            {title: '创建者', field: 'createBy', visible: false, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: false, align: 'center', valign: 'middle'},
            {title: '修改者', field: 'updateBy', visible: false, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remarks', visible: true, align: 'center', valign: 'middle'},
            {title: '删除标记', field: 'delFlag', visible: false, align: 'center', valign: 'middle'}
    ];
};

var Novel = {
    id: "NovelTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Novel.initColumn = function () {
    return [
        {field: 'selectItem', radio: true,visible:true},
            {title: '小说编码', field: 'novelId', visible: false, align: 'center', valign: 'middle'},
            {title: '小说名称', field: 'novelName', visible: true, align: 'center', valign: 'middle'},
            {title: '作者', field: 'author', visible: false, align: 'center', valign: 'middle'},
            {title: '简介', field: 'introduction', visible: false, align: 'center', valign: 'middle'},
            {title: '图片地址', field: 'novelPic', visible: false, align: 'center', valign: 'middle'},
            {title: '点击量', field: 'clickRate', visible: false, align: 'center', valign: 'middle'},
            {title: '状态', field: 'state', visible: false, align: 'center', valign: 'middle'},
            {title: '类别编码', field: 'categoryId', visible: false, align: 'center', valign: 'middle'},
            {title: '创建者', field: 'createBy', visible: false, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: false, align: 'center', valign: 'middle'},
            {title: '更新者', field: 'updateBy', visible: false, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: false, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remarks', visible: false, align: 'center', valign: 'middle'},
            {title: '删除标记', field: 'delFlag', visible: false, align: 'center', valign: 'middle'}
    ];
};

Chapter.showChapterList = function () {
    var selected = $('#' + Novel.id).bootstrapTable('getSelections');
    var params = {
        'novelId': selected[0].novelId
    };

    var defaultColunms = Chapter.initColumn();
    var table = new BSTable(Chapter.id, "/chapter/list", defaultColunms);
    table.setQueryParams(params);
    table.setPaginationType("client");
    Chapter.table = table.init();
}


/**
 * 检查是否选中
 */
Chapter.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Chapter.seItem = selected[0];
        return true;
    }
};

/**
 * 检查小说是否选中
 */
Novel.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中一部小说！");
        return false;
    }else{
        Novel.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加章节管理
 */
Chapter.openAddChapter = function () {
    if (Novel.check()) {
        var index = layer.open({
            type: 2,
            title: '添加章节管理',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/chapter/chapter_add'
        });
        this.layerIndex = index;
    }

};

/**
 * 打开查看章节管理详情
 */
Chapter.openChapterDetail = function () {
    if (this.check()) {
        var selected = $('#' + this.id).bootstrapTable('getSelections');
        var index = layer.open({
            type: 2,
            title: '章节管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/chapter/chapter_update/' + selected[0].chapterId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除章节管理
 */
Chapter.delete = function () {
    if (this.check()) {
        var selected = $('#' + this.id).bootstrapTable('getSelections');
        var ajax = new $ax(Feng.ctxPath + "/chapter/delete", function (data) {
            Feng.success("删除成功!");
            Chapter.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("chapterId",selected[0].chapterId);
        ajax.start();
    }
};

/**
 * 查询章节管理列表
 */
Chapter.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Chapter.table.refresh({query: queryData});
};

$(function () {
    // var defaultColunms = Chapter.initColumn();
    // var table = new BSTable(Chapter.id, "/chapter/list", defaultColunms);
    // table.setPaginationType("client");
    // Chapter.table = table.init();

    var novelColunms = Novel.initColumn();
    var novelTable = new BSTable(Novel.id, "/novel/list", novelColunms);
    novelTable.setPaginationType("client");
    Novel.table = novelTable.init();
});
