

$(document).ready(function() {

console.log("open!")
    // 영상 레이어 팝업
    $(".movie_player_popup").click(function(){
        $(".layerpopup").css("display","block")
    })


    $("#movie-close").click(function(){
        $(".layerpopup").css("display","none")
    })


    // 사진 슬라이드 기능
    let pagenum=0;
    let slideNum=$($(".item")).length;
    $("#btn-next").click(function(){
        console.log("next 클릭")
        if(pagenum>=slideNum-1){
            $($(".item")[pagenum]).css("display",'none');
            pagenum=0;
        }
        $($(".item")[pagenum]).css("display",'none');
        $($(".item")[pagenum+1]).css("display",'block');
        console.log(pagenum);
        pagenum++;
    })


    $("#btn-prev").click(function(){
        console.log("prev 클릭")
        if(pagenum<=0){
            $($(".item")[pagenum]).css("display",'none');
            pagenum=slideNum-1;
        }
        $($(".item")[pagenum]).css("display",'none');
        $($(".item")[pagenum-1]).css("display",'block');
        console.log(pagenum);
        pagenum--;
    })


    // 위시리스트 팝업 열고닫기

    $(".link-count").click(function(){
        console.log("wishlist 클릭")
        $(".wishlist").css("display","block")
    })

    $("#wishlistClose").click(function(){
        console.log("wishlist 닫기")
        $(".wishlist").css("display","none")
    })



    // 관람평 팝업 열고닫기

    $(".link-gradewrite").click(function(){
        console.log("관람평 클릭")
        $(".comment").css("display","block")
    })

    $("#regLayerClose").click(function(){
        $(".comment").css("display","none")
    })




});
