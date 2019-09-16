var path = $("#path").val();
// var imgYes = "<img width='15px' src='"+path+"/images/y.png' />";
var imgYes = "<img width='15px' src='/ssm/statics/images/y.png' />";
// var imgYes = "<img width='15px' src='"+path+"/statics/images/y.png' />";
// var imgNo = "<img width='15px' src='"+path+"/images/n.png' />";
var imgNo = "<img width='15px' src='/ssm/statics/images/n.png' />";

/**
 * 提示信息显示
 * element:显示提示信息的元素（font）
 * css：提示样式
 * tipString:提示信息
 * status：true/false --验证是否通过
 */
function validateTip(element, css, tipString, status) {
    element.css(css);
    element.html(tipString);

    element.prev().attr("validateStatus", status);
}

var referer = $("#referer").val();

// 时间的判断
$(function () {
    now = new Date();
    hour = now.getHours();
    if (hour < 6) {
//            document.write("凌晨好！")
        $("#hao").html("凌晨好！");
    }
    else if (hour < 9) {
//            document.write("早上好！")
        $("#hao").html("早上好！");
    }
    else if (hour < 12) {
//            document.write("上午好！")
        $("#hao").html("上午好！");
    }
    else if (hour < 14) {
//            document.write("中午好！")
        $("#hao").html("中午好！");
    }
    else if (hour < 17) {
        // document.write("下午好！");
        $("#hao").html("下午好！");
    }
    else if (hour < 19) {
//            document.write("傍晚好！")
        $("#hao").html("傍晚好！");
    }
    else if (hour < 22) {
//            document.write("晚上好！")
        $("#hao").html("晚上好！");
    }
    else {
//            document.write("夜里好！")
        $("#hao").html("夜里好！");
    }

    $(".list li:eq(3) a").click(function () {
        $.ajax({
            type:"GET",
            url:"/ssm/user/userListJson",
            // data:{method:"deluser",uid:obj.attr("userid")},
            dataType:"json",
            success:function(data){
                console.log(data);
                if(data.delResult == "true"){//删除成功：移除删除行
                    alert("删除成功");
                    obj.parents("tr").remove();
                }else if(data.delResult == "false"){//删除失败
                    alert("对不起，删除用户【"+obj.attr("username")+"】失败");
                }else if(data.delResult == "notexist"){
                    alert("对不起，用户【"+obj.attr("username")+"】不存在");
                }
            },
            error:function(data){
                alert("对不起，删除失败");
            }
        });
    });
});





