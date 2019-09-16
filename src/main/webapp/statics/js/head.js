
$(function () {
    $(".list > a:eq(3)").on("click",function(){
        var obj = $(this);
            alert("测试！");
            $.ajax({
                type:"GET",
                url:path+"/jsp/user.do",
                data:{method:"deluser",uid:obj.attr("userid")},
                dataType:"json",
                success:function(data){
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