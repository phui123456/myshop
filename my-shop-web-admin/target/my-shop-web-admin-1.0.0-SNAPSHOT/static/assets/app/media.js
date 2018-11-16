var WeMedia = function () {

    // 私有方法

    var handlerInitICheck = function () {
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass: 'iradio_minimal-blue'
        });
    };

    var handlerInitDataTables = function (url, columns) {
        var dataTable = $('#dataTable').DataTable({
            'paging'      : true,
            'lengthChange': true,
            'searching'   : false,
            'ordering'    : false,
            'info'        : true,
            'autoWidth'   : true,
            "serverSide": true,
            "bStateSave":true,
            ajax: {
                url: url,
                type: 'GET'
            },
            columns: columns,
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            drawCallback: function (settings) {
                // 初始化 iCheck
                handlerInitICheck();

                // 全选
                $(".chk_master").iCheck("uncheck");
                $(".chk_master").on("ifClicked", function (event) {
                    if (!$(this).is(':checked')) {
                        $("#dataTable input[type='checkbox']").iCheck('check');
                    }

                    else {
                        $("#dataTable input[type='checkbox']").iCheck('uncheck');
                    }
                });
            }
        });

        bindEvent();
        return dataTable;

        /**
         * DataTables 相关事件绑定
         */
        function bindEvent() {
            // 重置 DataTables
            $("#btnDataTableReset").on("click", function () {
                window.location.reload();
            });
        }
    };

    var handlerShowAlert = function (message) {
        $("#modal-alert").modal("show");
        $("#modal-alert .modal-body p").html(message);
        $("#btnAlertOk").unbind("click");
        $("#btnAlertOk").bind("click", function () {
            $("#modal-alert").modal("hide");
        });
    };

    var handlerShowAlertLager = function (message) {
        $("#modal-alert .modal-dialog").addClass("modal-lg");
        handlerShowAlert(message);
    };

    var handlerShowConfirm = function (message, callback) {
        $("#modal-confirm").modal("show");
        $("#modal-confirm .modal-body p").html(message);
        $("#btnConfirmOk").unbind("click");
        $("#btnConfirmOk").bind("click", function () {
            callback();
        });
    };

    var handlerShowDelete = function (url, message) {
        handlerShowConfirm(message == null ? "确定删除该笔数据吗？" : message, function () {
            window.location.href = url;
        });
    };

    var handlerShowMultiDelete = function (url, message) {
        var _checkbox = $('input[type="checkbox"].chk_slave:checked');
        if (_checkbox.length < 1) {
            WeMedia.showAlert("尚未选择任何数据项");
            return;
        }

        handlerShowConfirm(message == null ? "确定删除已选数据吗？" : message, function () {
            var _ids = new Array();
            $(_checkbox).each(function () {
                _ids.push($(this).attr("id"));
            });

            window.location.href = url + _ids;
        });
    };

    var handlerShowEdit = function (url) {
        var _checkbox = $('input[type="checkbox"].chk_slave:checked');
        if (_checkbox.length < 1) {
            WeMedia.showAlert("尚未选择任何数据项");
        }

        else if (_checkbox.length > 1) {
            WeMedia.showAlert("只能选择一笔数据项");
        }

        else {
            var _id = _checkbox[0].id;
            window.location.href = url + _id;
        }
    };

    var handlerShowDetail = function (url) {
        $.ajax({
            url: url,
            type: 'GET',
            dataType: 'html',
            success: function (html) {
                handlerShowAlertLager(html);
            }
        });
    };

    var handlerInitTree = function (url) {
        var setting = {
            view: {
                selectedMulti: false
            },
            async: {
                enable: true,
                type: "get",
                url: url,
                autoParam: ["id"]
            },
            callback: {
                beforeClick: function (treeId, treeNode) {
                    $("#parentId").val(treeNode.id);
                    $("#parentName").val(treeNode.name);
                }
            }
        };

        $("#parentName").bind("click", function () {
            WeMedia.showAlert("<ul id='treeData' class='ztree'></ul>");
            $.fn.zTree.init($("#treeData"), setting);
        });
    };

    var handlerInitDropzoneUpload = function (dropId, inputId) {
        $("div#" + dropId).dropzone({
            paramName: "dropFile",
            maxFiles: 1,
            maxFilesize: 1,
            url: "/upload/image",
            init: function () {
                this.on("success", function (event, data) {
                    $("#" + inputId).val(data.imageUrl);
                });
            }
        });
    };

    var handlerInitWangEditor = function () {
        var E = window.wangEditor;
        var editor = new E('#myEditor');
        var $content = $('#content');

        editor.customConfig.onchange = function (html) {
            // 监控变化，同步更新到 textarea
            $content.val(html)
        };

        // 配置服务器端地址
        editor.customConfig.uploadImgServer = '/upload/image';
        editor.customConfig.uploadFileName = 'wangEditorFiles';

        editor.create();

        // 初始化 editor 的值
        editor.txt.html($content.val());
    };

    // 公共方法
    return {
        /**
         * 初始化 iCheck
         */
        initICheck: function () {
            handlerInitICheck();
        },

        /**
         * 初始化 DataTables
         * @param url 请求地址
         * @param params 请求参数
         * @param columns 数据展示
         */
        initDataTables: function (url, columns) {
            return handlerInitDataTables(url, columns);
        },

        /**
         * 提示对话框
         * @param message
         */
        showAlert: function (message) {
            handlerShowAlert(message);
        },

        /**
         * 提示对话框（大）
         * @param message
         */
        showAlertLager: function (message) {
            handlerShowAlertLager(message);
        },

        /**
         * 确认对话框
         * @param message
         * @param callback
         */
        showConfirm: function (message, callback) {
            handlerShowConfirm(message, callback);
        },

        /**
         * 删除对话框
         * @param id
         * @param message
         */
        showDelete: function (id, message) {
            handlerShowDelete(id, message);
        },

        /**
         * 批量删除
         */
        showDeleteMulti: function (url) {
            handlerShowMultiDelete(url);
        },

        /**
         * 跳转编辑页面
         * @param url
         */
        showEdit: function (url) {
            handlerShowEdit(url);
        },

        /**
         * 详情页对话框
         * @param url`
         */
        showDetail: function (url) {
            handlerShowDetail(url);
        },

        /**
         * 初始化 zTree
         * @param url
         */
        initTree: function (url) {
            handlerInitTree(url);
        },

        /**
         * 图片上传（Dropzone）
         * @param dropId Dropzone 元素 ID
         * @param inputId 存放图片路径的元素 ID
         */
        initDropzoneUpload: function (dropId, inputId) {
            handlerInitDropzoneUpload(dropId, inputId);
        },

        /**
         * 富文本编辑器
         */
        initWangEditor: function () {
            handlerInitWangEditor();
        }
    }
}();

// WeMedia 初始化器
jQuery(document).ready(function () {
    WeMedia.initICheck();
});