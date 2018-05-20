/**
 * 书架管理管理初始化
 */
var Bookshelf = {
    id: "BookshelfTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Bookshelf.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '书架编码', field: 'bookshelfId', visible: true, align: 'center', valign: 'middle'},
            {title: '用户编码', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '小说编码', field: 'novelId', visible: true, align: 'center', valign: 'middle'},
            {title: '创建者', field: 'createBy', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remarks', visible: true, align: 'center', valign: 'middle'},
            {title: '删除标记', field: 'delFlag', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Bookshelf.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Bookshelf.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加书架管理
 */
Bookshelf.openAddBookshelf = function () {
    var index = layer.open({
        type: 2,
        title: '添加书架管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bookshelf/bookshelf_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看书架管理详情
 */
Bookshelf.openBookshelfDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '书架管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bookshelf/bookshelf_update/' + Bookshelf.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除书架管理
 */
Bookshelf.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bookshelf/delete", function (data) {
            Feng.success("删除成功!");
            Bookshelf.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bookshelfId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询书架管理列表
 */
Bookshelf.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Bookshelf.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Bookshelf.initColumn();
    var table = new BSTable(Bookshelf.id, "/bookshelf/list", defaultColunms);
    table.setPaginationType("client");
    Bookshelf.table = table.init();
});
