
console.log("theater.js 연결!")

let areacode=1;
$.ajax({
    url:"/theatersList",
    data:{areacode, areacode},
    type:"post",
    dataType: "json"
}).done(function(res){
    for(let i=0; i<=res.length; i++){
        $(".seoullist").append(`<li class=""><a title="${res[i].tname}" th:href="@{|/theaters/${res[i].tcode}|}" th:value=${res[i].tcode}>${res[i].tname}</a></li>`)
    }
})

// 지역별 영화관 반환
function theaters(areacode){
    $(".seoullist").empty();
    $(".gyeonggilist").empty();
    if(areacode==1) {
        if($("#gyeonggi").hasClass("on")) $("#gyeonggi").removeClass("on");
        $("#seoul").addClass("on");

        // areacode 0 전달
    $.ajax({
        url:"/theatersList",
        data:{areacode, areacode},
        type:"post",
        dataType: "json"
    }).done(function(res){
        for(let i=0; i<=res.length; i++){
            $(".seoullist").append(`<li class=""><a title="${res[i].tname}" th:href="@{|/theaters/${res[i].tcode}|}" th:value=${res[i].tcode}>${res[i].tname}</a></li>`)
        }
       })
}
    if(areacode==2) {
        if($("#seoul").hasClass("on")) $("#seoul").removeClass("on");
        $("#gyeonggi").addClass("on");

        // areacode 1 전달
        $.ajax({
            url:"/theatersList",
            data:{areacode, areacode},
            type:"post",
            dataType: "json"
        }).done(function(res){
            for(let i=0; i<=res.length; i++){
                $(".gyeonggilist").append(`<li class=""><a title="${res[i].tname}" th:href="@{|/theaters/${res[i].tcode}|}" th:value=${res[i].tcode}>${res[i].tname}</a></li>`)
            }
        })

    }
}
