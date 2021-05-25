
    $(function(){
        $(window).scroll(function(){//scroll 页面滚动事件
            var  sTop=$(window).scrollTop();//scrollTop  滚动距离
            
            if (sTop>=500) {
                $("bktop").fadeIn();
            } else{
                $("bktop").fadeOut()
            }
        })
        $("bktop").click(function(){
            $("body,html").animate({scrollTop:0},500)
        })
    })