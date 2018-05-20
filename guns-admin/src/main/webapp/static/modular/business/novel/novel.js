/**
 * 小说管理管理初始化
 */
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
        {field: 'selectItem', radio: true},
            {title: '小说编码', field: 'novelId', visible: false, align: 'center', valign: 'middle'},
            {title: '小说名称', field: 'novelName', visible: true, align: 'center', valign: 'middle'},
            {title: '作者', field: 'author', visible: true, align: 'center', valign: 'middle'},
            {title: '简介', field: 'introduction', visible: true, align: 'center', valign: 'middle'},
            {title: '图片地址', field: 'novelPic', visible: false, align: 'center', valign: 'middle'},
            {title: '点击量', field: 'clickRate', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'state', visible: true, align: 'center', valign: 'middle'},
            {title: '类别编码', field: 'categoryId', visible: false, align: 'center', valign: 'middle'},
            {title: '创建者', field: 'createBy', visible: false, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: false, align: 'center', valign: 'middle'},
            {title: '更新者', field: 'updateBy', visible: false, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remarks', visible: true, align: 'center', valign: 'middle'},
            {title: '删除标记', field: 'delFlag', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Novel.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Novel.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加小说管理
 */
Novel.openAddNovel = function () {
    var index = layer.open({
        type: 2,
        title: '添加小说管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/novel/novel_add'
    });
    var avatarUp  = new $WebUpload("novelPic");
    avatarUp.init();
    this.layerIndex = index;
};

/**
 * 打开查看小说管理详情
 */
Novel.openNovelDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '小说管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/novel/novel_update/' + Novel.seItem.novelId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除小说管理
 */
Novel.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/novel/delete", function (data) {
            Feng.success("删除成功!");
            Novel.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("novelId",this.seItem.novelId);
        ajax.start();
    }
};

/**
 * 查询小说管理列表
 */
Novel.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Novel.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Novel.initColumn();
    var table = new BSTable(Novel.id, "/novel/list", defaultColunms);
    table.setPaginationType("client");
    Novel.table = table.init();
});
