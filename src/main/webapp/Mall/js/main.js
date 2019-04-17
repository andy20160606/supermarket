var index = 0;//轮播图用
//初始化
//urlSearch()用于将href给json化，方便获取之后的链接之后的code值；
function urlSearch() {
    var str=location.href; //取得整个地址栏
    var num=str.split('?');
    var arr;
    var data={};
    var name;
    $.each(num,function (ind,val) {
        if(ind>0){
            if(val.split('&').length>1){
                for(var i=0;i<val.split('&').length;i++){
                    arr=val.split('&')[i].split('=');
                    name=arr[0];
                    data[name]=arr[1];
                }
            }else {
                arr=val.split('=');
                var name=arr[0];
                data[name]=arr[1];
            }
        }
    });
    return data;
}
window.onload = function () {
    var getRequest = urlSearch();
    var key = localStorage.getItem('key');
    if(key === "Login"){
        //获取链接之后的code值，如果有code执行if。如果没有，执行else;
        if (getRequest.code!==undefined&&getRequest.code!==null&&getRequest.code!==localStorage.getItem('code')) {
            localStorage.setItem('code',getRequest.code);
            this.code = getRequest.code;
            //把code值传给后台；
        }
        //执行微信授权操作；
        else{
            var pageUrl = window.location.href
                .replace(/[/]/g, "%2f")
                .replace(/[:]/g, "%3a")
                .replace(/[#]/g, "%23")
                .replace(/[&]/g, "%26")
                .replace(/[=]/g, "%3d");
            //调用微信登陆授权获取code值
            // window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid  + "" +
            //     "&redirect_uri=" + pageUrl + "&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
        }
    }
    else if(key === "Index"){
        $.ajax({//获取banner图
            url:'http://192.168.0.113:8080/info/findByKeyContaining?key=phonebanner',
            type:'get',
            success:function (data) {
                console.log(data);
                for(var i = 0;i<data.obj.length;i++){
                    $(".one_img_one").append("<img src=\"" + "http://192.168.0.113:8080/file/download?fileName=" +  data.obj[i].val + "\" class=\"one_imgone_img\" style=\"display: none;\">");
                    $(".one_img_point_item").append("<div class=\"point_item\" onclick='pointt($(\".point_item\").index(this))'></div>");
                }
                $(".one_imgone_img").eq(0).css('display','block');
                $(".point_item").eq(0).css('background','red');

                //轮播图设置
                var start = setInterval(autoplay,3500);
                var point = $(".point_item");
                var funny = function (i) {
                    pointt(i);
                };
                for(var i = 0;i<point.length;i++){
                    funny(i);
                }
            },
            error:function () {
                console.log("数据获取出错");
            }
        });
        $.ajax({//获取分类
            url:'http://192.168.0.113:8080/hy/list',
            type:'get',
            // contentType:'application/json',
            // data:{},
            // dataType:'json',
            success:function (data) {
                console.log(data);
                // $(".one_threebox").append('<div class="one_three_item">个人</div>')

                // $.ajax({//获取第一个分类小工具
                //     url:'http://192.168.0.113:8080/GET /cp/list?hymc=',
                //     type:'get',
                //     // contentType:'application/json',
                //     // data:{},
                //     // dataType:'json',
                //     success:function (data) {
                //         console.log(data);
                //
                //     },
                //     error:function () {
                //         console.log("小工具数据获取出错");
                //     }
                // });
            },
            error:function () {
                console.log("分类获取出错");
            }
        });

    }
    else if(key === "Introduction"){}
    else if(key === "Win"){}
    else if(key === "Custom"){}
    else if(key === "Designer"){}
    else if(key === "Member"){}
    else if(key === "Partner"){}
    else if(key === "Share"){}
};

//登陆界面
$(".login_four").click(function () {
    window.location.href = 'Index.html'
});

//首页
//分类切换
var n = 0;
$(".one_three_item").click(function () {
    $(".one_three_item").removeClass('item_add');
    $(this).addClass('item_add');
    if(n === 1){
        $(".one_three_item_i").toggleClass('item_i_add').children('#item_img').toggleClass('item_img');
        $(".expansion").toggle("");
        n = 0;
    }
});
//点击小工具进入小工具详情页
$(".one_four_item").click(function () {
    window.location.href = 'Introduction.html'
});
//点击大宝箱进入点击赢好礼界面
$(".one_two_href").click(function () {
    window.location.href = 'Win.html';
});
//点击我的优惠券进入优惠券
$(".one_button").click(function () {
    $(".mycard").toggle("");
});
//优惠券列表退出
$(".mycard_canel").click(function () {
    $(".mycard").toggle("");
});
//点击更多，出现更多的分类
$(".one_three_item_i").click(function () {
    if(n === 0){//下拉
        n = 1;
        $(".one_three_item").removeClass('item_add');
        $(this).toggleClass('item_i_add').children('#item_img').toggleClass('item_img');
        $(".expansion").toggle("");
    }
    else {//上拉
        n = 0;
        $(".one_three_item").removeClass('item_add');
        $(this).toggleClass('item_i_add').children('#item_img').toggleClass('item_img');
        $(".expansion").toggle("");
    }
});
//更多分类选中
$(".expansion_item").click(function () {
    $(".expansion_item").removeClass('expansion_item_add');
    $(this).addClass('expansion_item_add');
});
//轮播图设置
function autoplay() {
    if(index === $(".point_item").length){
        index = 0;
    }
    changeImg(index++);
}
function changeImg(index) {
    var list = $(".one_imgone_img");
    var list1 = $(".point_item");
    list.eq(index).css('display','block');
    list1.eq(index).css('background','red');
    for(var i = 0;i<list.length;i++){
        if(i!=index){
            list.eq(i).css('display','none');
            list1.eq(i).css('background','rgba(0,0,0,0.5)');
        }
    }
}
function pointt(i) {
    console.log("dianji" + i);
    changeImg(i);
}



//小工具详情页
//点击购买，出现预约报名界面
$(".content_three").click(function () {
    $(".buy").toggle("");
});
//点击预约界面提交，出现提交成功提示
$(".buy_submit").click(function () {
    $(".buy_ok").toggle();
    $(".buy").hide();
});
//点击提交成功提示界面按钮，出现小工具详情页
$(".buy_ok_submit").click(function () {
    $(".buy_ok").toggle("");
});

//点击赢好礼界面
$("#share").click(function () {
    window.location.href = "Share.html";
});
$("#partner").click(function () {
    window.location.href = "Partner.html";
});
$("#member").click(function () {
    window.location.href = "Member.html";
});
$("#designer").click(function () {
    window.location.href = "Designer.html";
});
$("#custom").click(function () {
    window.location.href = "Custom.html";
});

//分享界面
$(".limit_submit").click(function () {
    $(".share_limit").hide();
});