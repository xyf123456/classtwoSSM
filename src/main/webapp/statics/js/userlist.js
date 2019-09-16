var userObj;

//用户管理页面上点击删除按钮弹出删除框(userList1.jsp)
function deleteUser(obj) {
    $.ajax({
        type: "GET",
        // url: path + "/jsp/user.do",
        url: "/ssm/user/delUser/"+obj.attr("userid"),
        dataType: "json",
        success: function (data) {
            if (data.status === "success") {//删除成功：移除删除行
                cancleBtn();
                obj.parents("tr").remove();
                window.location.href = "/ssm/user/userList";
            } else if (data.delResult === "fail") {//删除失败
                //alert("对不起，删除用户【"+obj.attr("username")+"】失败");
                changeDLGContent("对不起，删除用户【" + obj.attr("username") + "】失败");
            }
        },
        error: function (data) {
            //alert("对不起，删除失败");
            changeDLGContent("对不起，删除失败");
        }
    });
}

function openYesOrNoDLG() {
    $('.zhezhao').css('display', 'block');
    $('#removeUse').fadeIn();
}

function cancleBtn() {
    $('.zhezhao').css('display', 'none');
    $('#removeUse').fadeOut();
}

function changeDLGContent(contentStr) {
    var p = $(".removeMain").find("p");
    p.html(contentStr);
}

$(function () {
    //通过jquery的class选择器（数组）
    //对每个class为viewUser的元素进行动作绑定（click）
    /**
     * bind、live、delegate
     * on
     */
    $(".viewUser").on("click", function () {
        //将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
        var obj = $(this);
        // window.location.href = path + "/jsp/user.do?method=view&uid=" + obj.attr("userid");
        window.location.href = "/ssm/user/viewUser/" + obj.attr("userid");
    });
    // 修改用户
    $(".modifyUser").on("click", function () {
        var obj = $(this);
        // window.location.href =path +"/user/usermodify.html?uid=" + obj.attr("userid");
        // http://localhost:8080/ssm/user/usermodify.html?uid=1
        window.location.href ="/ssm/user/modifyUser/" + obj.attr("userid");
    });

    $('#no').click(function () {
        cancleBtn();
    });

    $('#yes').click(function () {
        deleteUser(userObj);
    });

    $(".deleteUser").on("click", function () {
        userObj = $(this);
        changeDLGContent("你确定要删除用户【" + userObj.attr("username") + "】吗？");
        openYesOrNoDLG();
    });


});