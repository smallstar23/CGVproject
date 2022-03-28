// 학원 컴퓨터 연결!!
// 페이지 처음에 들어갈때는 무조건 서울 리스트 보여주기
let areacode=1;
$.ajax({
    url:"/theatersList",
    data:{areacode, areacode},
    type:"post",
    dataType: "json"
}).done(function(res){
    for(let i=0; i<=res.length-1; i++){
        $(".seoullist").append(`<li><a href="/theaters/${res[i].tcode}" value=${res[i].tcode}>${res[i].tname}</a></li>`)

    }

})


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
    let value="2022"+printmonth+dateArray[i];
    item[0].innerHTML += `<li class="addon" onclick="whatdate(this)" value="${value}">
                                        <div class="day" >
                                            <a>
                                                <span>${printmonth}월</span>
                                                <em>${newdayArray[i]}</em>
                                                <strong>${dateArray[i]}</strong>
                                            </a>
                                        </div>
                                    </li>`

    if(dateArray[i]==new Date().getDate()){
        $(".addon").addClass('on')
    }

}


//2022-03-20 형태로 변환해줌
function convertDateFormat(date) {
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    month = month >= 10 ? month : '0' + month;
    let day = date.getDate();
    day = day >= 10 ? day : '0' + day;
    return [year, month, day].join('-');
}

let newschdate= convertDateFormat(new Date());


let scheduleData = document.getElementById('scheduleData');



// 스케쥴 찾아오기
function showSchedule(tcode, newschdate){
    $.ajax({
        url:"/Schedule",
        data:{
            "tcode":tcode,
            "newschdate": newschdate
        },
        type:"post",
        dataType:"json"
    }).done(function(res) {
        // 스케쥴 값 초기화
        const movieData=new Array();
        const newMovieData=new Array();
        let check=true;
        const schedulelist= new Array();

        scheduleData.innerHTML="";
        // movie 정보만 따로 배열에 저장함
        for (let i = 0; i <= res.length - 1; i++) {
            movieData[i] = res[i].movieDTO;
            schedulelist[i]= res[i];
        }
        // 같은 영화 정보가 있는지 확인, 같으면 새로운 배열에 저장 X, 새로운 무비정보만 저장함
        for (let i = 0; i <= movieData.length - 1; i++) {
            check = true;
            // 중복 체크
            for (value in newMovieData) {
                if (Object.values(newMovieData[value])[0] == movieData[i].mcode) check = false;
            }
            if (check) {
                newMovieData.push(movieData[i])
            }
        }
        // 중복된 값을 제거한 무비 배열 만큼 반복함
        newMovieData.forEach(movie => {
            scheduleData.innerHTML +=
                `
                                        <li>
                                        <div class="col-times">
                                            <div class="info-movie">
                                        <span class="ico-grade ${(movie.movieRating == '전체 이용가' ? 'grade-all' : '') +
                (movie.movieRating == '12세 이상' ? 'grade-12' : '') +
                (movie.movieRating == '15세 이상' ? 'grade-15' : '') +
                (movie.movieRating == '19세 이상' ? 'grade-19' : '')}">
                                            ${movie.movieRating}</span> 
                                            <a href="/movies/detail-view/${movie.mcode}" target="_parent"><strong>
                                                ${movie.titleKo}</strong></a>
                                                <span class="round lightblue">
                                            <em>
                                                ${movie.mscreen}</em>
                                        </span><span class="">
                                            <em>
                                                </em>
                                        </span><i>
                                                ${movie.genre}</i>/ <i>
                                                ${movie.runtime}분</i>/ <i>
                                               ${movie.launchDate}
                                                개봉</i>
                                            </div>
                                          
                                            `
            // 무비 마다 새롭게 hall정보가 전달되어야 함, hall 정보를 각각 받아와서 중복된 hall 제거함
            const hallDTOlist = new Array();
            const newHallDTOList = new Array();
            schedulelist.forEach(schedule => {
                if (schedule.mcode == movie.mcode) {
                    hallDTOlist.push(schedule.hallDTO);
                    for (let i = 0; i <= hallDTOlist.length - 1; i++) {
                        check = true;
                        // 중복 체크
                        for (value in newHallDTOList) {
                            if (newHallDTOList[value].hcode == hallDTOlist[i].hcode) check = false;
                        }
                        if (check) {
                            newHallDTOList.push(hallDTOlist[i])
                        }
                    }
                }
            })
            // 무비 마다 중복된 hall 을 제거한 list만큼 반복문으로 더해줌
            newHallDTOList.forEach(HallDTO => {
                scheduleData.innerHTML +=
                    `                                    <div class="type-hall">
                                                        <div class="info-hall">
                                                            <ul>
                                                                <li>${HallDTO.hname}</li>
                                                                <li>${HallDTO.hguan}관</li>
                                                                <li>${HallDTO.seatSize}석</li>
                                                            </ul>
                                                        </div>
                                                        `

                schedulelist.forEach((timeList, index)=>{
                    if(timeList.mcode === movie.mcode && timeList.hcode === HallDTO.hcode){
                        let starttime=timeList.scdate.substring(11,16);
                        scheduleData.innerHTML+=
                            `
                                        <div class="info-timetable" style="width: fit-content; display: inline-flex">
                                            <ul>
                                                <li><em style="color:black">${starttime}</em><em>빈자리</em></li>
                                            </ul>
                                        </div>
                    `

                    }
                })


            })
            scheduleData.innerHTML += `</div></div></li>`

        })
    }).error(function(err){
        scheduleData.innerHTML="";
    })


}



// 지역별 영화관 반환
    function theaters(areacode) {
        $(".seoullist").empty();
        $(".gyeonggilist").empty();
        if (areacode == 1) {
            if ($("#gyeonggi").hasClass("on")) $("#gyeonggi").removeClass("on");
            $("#seoul").addClass("on");

            // areacode 1 전달
            $.ajax({
                url: "/theatersList",
                data: {areacode, areacode},
                type: "post",
                dataType: "json"
            }).done(function (res) {
                for (let i = 0; i <= res.length; i++) {
                    $(".seoullist").append(`<li><a href="/theaters/${res[i].tcode}" th:value=${res[i].tcode}>${res[i].tname}</a></li>`)


                }
            })


        }

        if (areacode == 2) {
            if ($("#seoul").hasClass("on")) $("#seoul").removeClass("on");
            $("#gyeonggi").addClass("on");

            // areacode 2 전달
            $.ajax({
                url: "/theatersList",
                data: {areacode, areacode},
                type: "post",
                dataType: "json"
            }).done(function (res) {
                for (let i = 0; i <= res.length; i++) {
                    $(".gyeonggilist").append(`<li><a href="/theaters/${res[i].tcode}" th:value=${res[i].tcode}>${res[i].tname}</a></li>`)
                }


            })


        }
    }


// 스케쥴 실행
showSchedule(tcode, newschdate);

// 달력에서 클릭한 날짜 전달
function whatdate(date){
    if($(".addon").hasClass('on')){
        $(".addon").removeClass('on')
    }
    $(date).addClass('on');
    let newDate= new String(date.value);
    //스케쥴 초기화
    scheduleData.innerHTML ="";
    showSchedule(tcode, newDate.substring(0,4)+"-"+newDate.substring(4,6)+"-"+newDate.substring(6,8))
}

// 로그인 쪽으로
function logingogo(){
    alert("로그인을 해주세요.")
    location.href = "/user/login"
}