


let areacode=1;
$.ajax({
    url:"/theatersList",
    data:{areacode, areacode},
    type:"post",
    dataType: "json"
}).done(function(res){
    for(let i=0; i<=res.length; i++){
        $(".seoullist").append(`<li class="addon"><a class="findtcode" href="/theaters/${res[i].tcode}" th:value=${res[i].tcode}>${res[i].tname}</a></li>`)

    }


})

// 지역별 영화관 반환
function theaters(areacode){
    $(".seoullist").empty();
    $(".gyeonggilist").empty();
    if(areacode==1) {
        if($("#gyeonggi").hasClass("on")) $("#gyeonggi").removeClass("on");
        $("#seoul").addClass("on");

        // areacode 1 전달
    $.ajax({
        url:"/theatersList",
        data:{areacode, areacode},
        type:"post",
        dataType: "json"
    }).done(function(res){
        for(let i=0; i<=res.length; i++){
            $(".seoullist").append(`<li class="addon"><a class="findtcode" href="/theaters/${res[i].tcode}" th:value=${res[i].tcode}>${res[i].tname}</a></li>`)

            
        }
       })


}


    if(areacode==2) {
        if($("#seoul").hasClass("on")) $("#seoul").removeClass("on");
        $("#gyeonggi").addClass("on");

        // areacode 2 전달
        $.ajax({
            url:"/theatersList",
            data:{areacode, areacode},
            type:"post",
            dataType: "json"
        }).done(function(res){
            for(let i=0; i<=res.length; i++){
                $(".gyeonggilist").append(`<li class="addon"><a class="findtcode" href="/theaters/${res[i].tcode}" th:value=${res[i].tcode}>${res[i].tname}</a></li>`)
            }


        })




    }
}




// 하단 스케쥴 달력 부분 구현

let howmany = 8;
let today = new Date().getDate();
let month = new Date().getMonth()+1;
const year = new Date().getFullYear();
const lastDate = new Date(year, month, 0).getDate(); // 해당월의 마지막일
let day = new Date().getDay();
const dayArray = ["일", "월", "화", "수", "목", "금", "토"];
const dateArray = [];
for (let i = 0; i < howmany; i++) {
    let initToday = today + i;
    // 배열에 넘겨주려는 값이 해당 월의 마지막일보다 클 경우
    if (initToday > lastDate) initToday = today + i - lastDate;
    // 넘겨주려는 값에 마지막일 수를 뻅니다. (32 - 31 = 1)
    dateArray[i] = initToday;
}
const newdayArray = [];
for (let i = 0; i < howmany; i++) {
    let newday = day + i;
    // 불필요한 if문 제거
    newday = newday % 7;
    newdayArray[i] = dayArray[newday];
}

const item = document.getElementsByClassName("item");
for (let i = 0; i < howmany; i++) {
    //03, 04.. 월 출력시 0 추가
    let printmonth ="0"+month;
    if(printmonth>10){
        printmonth=month;
    }
    // month+1 추가, 1일 되면 현재 월에 +1
    if(dateArray[i]==1){
        month=month+1;
    }
    item[0].innerHTML += `<li class="">
                                        <div class="day" >
                                            <a href="#">
                                                <span>${printmonth}월</span>
                                                <em>${newdayArray[i]}</em>
                                                <strong>${dateArray[i]}</strong>
                                            </a>
                                        </div>
                                    </li>`

}
