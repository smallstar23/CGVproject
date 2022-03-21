// 스케쥴 불러오기
let mcode;
let tcode;
let schecode;
let hcode;

// step 1. 예매가이드 팝업
let guide = document.getElementsByClassName('button-guide');
let guideClose = document.getElementsByClassName('guideClose');
let popup = document.getElementsByClassName('popup_guide');
let blackscreen = document.getElementsByClassName("blackscreen");


guide[0].addEventListener('click', function () {
    popup[0].style.display = 'block';
    blackscreen[0].style.display = 'block';
});

guideClose[1].addEventListener('click', popupClose);
guideClose[0].addEventListener('click', popupClose);

function popupClose() {
    console.log("popup close()!")
    popup[0].style.display = 'none';
    blackscreen[0].style.display = 'none';
};

let placeholder = document.getElementsByClassName('placeholder');
// 0. 스케줄(시간)선택 가이드, 1. 영화선택 가이드, 2. 극장선택 가이드, 3. 좌석선택 가이드

const schedultGuideText = placeholder[0];
const movieGuideText = placeholder[1];
const theaterGuideText = placeholder[2];
const seatGuideText = placeholder[3];

let movie_click = document.getElementsByClassName('movieClick');
let movieTitle = document.getElementsByClassName('movie_title');

for (let i = 0; i <= movie_click.length - 1; i++) {
    movie_click[i].addEventListener('click', function () {
        for (let k = 0; k < movie_click.length; k++) {
            movie_click[k].classList.remove("selected");
        }
        movie_click[i].classList.add("selected");
        // mcode 저장
        mcode = movie_click[i].value;
        // 영화명 전달
        let movieName = document.getElementsByClassName('movieName')[i].getAttribute('title');
        movieTitle[0].style.display = 'block';
        placeholder[1].style.display = 'none';
        let movieSel = document.getElementById("movie_sel");
        movieSel.innerHTML = `<input style="background-color: #1d1d1c; color:#cccccc; font-weight: bold" name="movieName" id="movieName" value="${movieName}"></input>`;
        movieSel.setAttribute("href", "/movies/detail-view/" + mcodeArray[i]);

        // 포스터 넣기
        // src 값을 찾아오기(타임리프로 받아서 array로 전달)
        let moviePoster = document.getElementById("movie_poster");
        moviePoster.style.display = 'block';
        moviePoster.src = url[i];
        toFirstDateStatus()
        dimmedOrNot()
    });
}

// 날짜 부분 오늘 날짜 기준으로 howmany 값 조절하면 +howmany 만큼 날짜 뿌릴 수 있음, 아직 월 넘어가는건 못하겠음..
// 배열이나 객체같은 것들은 보통 const로 자료형을 잡아주는게 좋습니다.
// 배열의 요소값들이 바뀌는 것이지 배열자체의 위치값이 바뀌는것이 아닙니다. (이유)
// 그리고 howmany 만큼이 아니라 전에꺼였으면 howmany + 1 이었음 ㅡㅡ
let howmany = 25;
let today = new Date().getDate();
let month = new Date().getMonth() + 1;
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

const fordate = document.getElementById("fordate");
for (let i = 0; i < howmany; i++) {
    //2022.03.17(목)형식으로
    // month+1 추가, 1일 되면 현재 월에 +1
    if (dateArray[i] == 1) {
        month = month + 1;
        fordate.innerHTML += `<li class="month dimmed">
                                            <div>
                                                <span class="year">2022</span>
                                                <span class="month">${month}</span>
                                                <div>
                                                </div>
                                            </div>
                                            </li>`

    }
    // 1~9월일때는 0추가
    let newmonth;
    if (month < 10) {
        newmonth = "0" + month;
    }
    // 1~9일일때는 0추가
    if (dateArray[i] < 10) {
        dateArray[i] = "0" + dateArray[i]
    }
    let init = year + "-" + newmonth + "-" + dateArray[i] + "(" + newdayArray[i] + ")";
    fordate.innerHTML += `<li data-index="${i}" date="${init}" class="day passday checkdate dimmed" onclick="ifFindScheduleAction(this)"><a><span class="dayweek">${newdayArray[i]}</span><span class="day">${dateArray[i]}</span><span class="sreader"></span></a></li>`
}

// 달력에서 가져온 checkdate의 값을 "(" 기준으로 나눈 후 앞부분만 새로 array에 저장함
const checkdate = document.getElementsByClassName("checkdate");
const newcheckdate = new Array();
for (let i = 0; i <= checkdate.length - 1; i++) {
    newcheckdate[i] = checkdate[i].getAttribute("date").split("(")[0];
}


// 영화관 선택 부분 구현

let theaterAreaClick = document.getElementsByClassName("theaterAreaClick");
let theaterClick = document.getElementsByClassName("theaterClick");
let areaSelect = document.getElementsByClassName("areaSelect");
let theaterSelect = document.getElementsByClassName("theaterSelect");
let infoTheater = document.getElementsByClassName("infoTheater");
let sendTheaterName = document.getElementsByClassName("sendTheaterName");
let area_theater_list = document.getElementsByClassName("area_theater_list");

let scheduleList = document.getElementById('scheduleList');
let sendHallInfo = document.getElementsByClassName('sendHallInfo');


theaterInit();

function theaterInit() {
    const theaterClick = document.querySelectorAll('.theaterClick');

    theaterClick.forEach(theater => {
        theater.onclick = function () {
            tcode = theater.value;
            toFirstDateStatus();
            transferTheaterName(theater);
            extraFunction(theaterClick);
            theater.classList.add('selected');
            dimmedOrNot();
        }
    });
}

function toFirstDateStatus() {
    const checkDate = document.querySelectorAll(".checkdate");
    document.querySelectorAll('#scheduleList li').forEach(list => list.classList.remove('selected'));
    document.querySelector('.movie_rating').firstElementChild.innerText = '';
    document.querySelector('.movie_type').firstElementChild.innerText = '';
    document.getElementById('scheduleList').innerHTML = '';
    document.querySelector('.sendDate').innerText = '';
    document.querySelector('.sendHallInfo').innerText = '';
    hcode="";
    schecode="";


    checkDate.forEach(d => {
        d.classList.add('dimmed');
        d.classList.remove('selected');
    })
}

function transferTheaterName(theater) {
    const theaterTransferred = document.getElementsByClassName('sendTheaterName')[0];

    theaterTransferred.setAttribute('href', '/theaters/' + theater.value);
    theaterTransferred.setAttribute('title', theater.getAttribute('tname'));
    theaterTransferred.innerText = theater.getAttribute('tname');
    theaterGuideText.style.display = 'none';
}

function extraFunction(theaterList) {
    for (let i = 0; i < infoTheater.length; i++) {
        infoTheater[i].style.display = 'block';
    }
    theaterList.forEach(theater => {
        theater.classList.remove('selected')
    })
}

function dimmedOrNot() {
    if(tcode == null || mcode == null) return;

    const regExp = '\\d{4}-\\d{2}-\\d{2}';
    const dateList = document.querySelectorAll('.checkdate');
    const result = findScheduleAjax(mcode, tcode);

    // 초기화
    dateList.forEach(date => date.classList.remove('findSchedule'));
    result.then(response => response.json())
        .then(data => {
            data.forEach(scheduleDTO => {
                const scdate = scheduleDTO.scdate.match(regExp)[0]; // 정규식 ex(2022-02-10)
                dateList.forEach(date => {

                    if (date.getAttribute('date').match(regExp)[0] == scdate) {
                        date.classList.remove('dimmed');
                        date.classList.add('findSchedule');
                        date.setAttribute('scdate', scdate); // 클릭시 검색을 위한 속성값

                    }
                })
            })
        })
}

function findScheduleAjax(mcode, tcode) {
    return fetch('/api/findSchedule', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            mcode: mcode,
            tcode: tcode,
        })
    })
}

function ifFindScheduleAction(DOM) {
    if (!DOM.classList.contains('findSchedule')) return false;

    const findSchedule = document.querySelectorAll('.findSchedule');

    // select 값 기입
    findSchedule.forEach(sc => sc.classList.remove('selected'));
    DOM.classList.add('selected')

    // 가이드라인 삭제
    schedultGuideText.style.display = 'none';

    // 하단에 정보 전달 (초기화 필요)
    document.querySelector('.sendDate').innerText = DOM.getAttribute('date');

    // AJAX시작
    const result = findScheduleFinalAjax(mcode, DOM.getAttribute('scdate'), tcode);
    result.then(response => response.json())
        .then(data => {
            generateScheduleBoneHtml(data).then(addSeatCountHtml)
        });
}

function findScheduleFinalAjax(mcode, scdate, tcode) {
    if (mcode == null || scdate == null || tcode == null) {
        alert('정보를 다시 입력하세요');
        return;
    }
    return fetch(`/api/findSchedule?mcode=${mcode}&tcode=${tcode}&scdate=${scdate}`);
}

// 개인메모 async는 자바의 synchronized와 동일한 기능을 가지는 것 같음
// promise 객체를 이용하지는 않았지만 promise객체가 생성되는 시점을 바탕으로 동작하게 하면 데이터 충돌이 안일어남.
// 반복을 할 메서드
async function generateScheduleBoneHtml(scheduleDTOList) {
    const scheduleList = document.getElementById('scheduleList');
    scheduleList.innerHTML = ''; // 초기화
    let scheduleHtml = '';

    const hcodeInitList = []; // 현재까지 들어간 hcode목록
    for (let scheduleDTO of scheduleDTOList) {
        if (hcodeInitList.includes(scheduleDTO.hcode)) continue; // 현재까지 들어간 목록에 있으면 다음으로
        hcodeInitList.push(scheduleDTO.hcode);

        const _hallDTO = scheduleDTO.hallDTO;

        scheduleHtml += `<div class="theater" hcode="${scheduleDTO.hcode}" mcode="${scheduleDTO.mcode}">`;
        scheduleHtml += `<span class="title">
                        <span class="name">${_hallDTO.hname}</span>
                        <span class="floor">${_hallDTO.hguan + '관 ' + _hallDTO.location}</span>
                        <span class="seatcount"></span>
                    </span>`;
        scheduleHtml += `<ul>`
        /*
            <li>에 해당하는 내용이 들어감.
        */
        scheduleHtml += addDetailHtml(scheduleDTOList, scheduleDTO.hcode);
        scheduleHtml += `</ul>`
        scheduleHtml += `</div>`

    }
    scheduleList.innerHTML = scheduleHtml;
}

function addSeatCountHtml() {
    const theaterList = document.querySelectorAll('#scheduleList > .theater');
    theaterList.forEach(theater => {
        const hcode = theater.getAttribute('hcode');
        const spanSeatCount = theater.querySelectorAll('.seatcount')[0];
        getPromiseSeatCount(hcode).then(response => response.json()).then(data => spanSeatCount.innerText = `(총 ${data}석)`);
    })
}

function addDetailHtml(scheduleDTOList, hcode) {
    let detailHtml = '';
    for (let scheduleDTO of scheduleDTOList) {
        if (hcode !== scheduleDTO.hcode) continue;
        console.log(scheduleDTO)
        const startTime = scheduleDTO.scdate.split('T')[1].substring(0,5);
        //scdate = 2022-03-23T15:00:00  //split('T') = ['2022-03-23', '15:00:00'] // substring(0,5) = 15:00
        const endTemp = new Date(scheduleDTO.scdate.split('T')[0] + " " + scheduleDTO.scdate.split('T')[1]);
        endTemp.setMinutes(endTemp.getMinutes()+scheduleDTO.movieDTO.runtime);

        const endTime = `${addZero(endTemp.getHours())}:${addZero(endTemp.getMinutes())}`

        detailHtml += `<li size="${scheduleDTO.hallDTO.seatSize}" endtime="${endTime}"  schedule="${scheduleDTO.scdate}" schecode="${scheduleDTO.schecode}" start_tm="${startTime.replace(':', '')}" hcode="${hcode}" mcode="${scheduleDTO.mcode}" movieRating="${scheduleDTO.movieDTO.movieRating}">
                        <a class="button">
                            <span class="time" onclick="schecodeSelect(this)">
                                <span>${startTime}</span>
                            </span>
                            <span class="count">${scheduleDTO.hallDTO.seatSize}석</span>
                            <div class="sreader">종료시간 ${endTime}</div>
                            <span class="sreader mod"></span>
                        </a>
                      </li>`
    }
    return detailHtml;
}

function getPromiseSeatCount(hcode) {
    if (hcode == null) {
        alert('홀의 정보를 받지 못하였습니다 ( 총 좌석 수 )');
        return false;
    }
    return fetch('/api/schedule/getSeatCount?hcode=' + hcode)
}

function schecodeSelect(DOM) {

    const parentList = DOM.parentNode.parentElement;
    const spanTitle = DOM.parentNode.parentNode.parentElement.previousElementSibling; // <span class="title"> ( 홀 정보 들어있는 곳 )
    schecode = parentList.getAttribute('schecode');
    hcode = parentList.getAttribute('hcode');
    let schedule=parentList.getAttribute('schedule');

    document.querySelectorAll('#scheduleList li').forEach(infoList => infoList.classList.remove('selected'));
    parentList.classList.add('selected');

    // step1 하단에 내용 전달하는 부분입니다.
    document.querySelector('.sendHallInfo').innerText = spanTitle.querySelector('.floor').innerText;
    document.querySelector('.movie_type > span').innerText = spanTitle.querySelector('.name').innerText;
    document.querySelector('.movie_rating > span').innerText = parentList.getAttribute('movieRating');

    document.querySelector('.sendDate').innerText = document.querySelector('.findSchedule.selected')
        .getAttribute('date') +' '+ DOM.firstElementChild.innerText;

    // step2 상단 내용 찍어주는 부분입니다.
    document.querySelector('.theater-info > .site').innerText=spanTitle.querySelector('.name').innerText
    document.querySelector('.theater-info > .screen').innerText=spanTitle.querySelector('.floor').innerText;
    document.querySelector('.theater-info > .seatNum> .totalNum').innerText=parentList.getAttribute('size');
    document.querySelector('.playYMD-info > .date').innerText=schedule.substring(0,10);
    document.querySelector('.playYMD-info > .time').innerText=schedule.substring(11,16)+" ~ "+parentList.getAttribute('endtime')

}
function addZero(number){
    return parseInt(number) < 10 ? "0" + number : number;
}

/*
    작성자 : 김영신
    2022-03-05 03:12
    지역 선택 부분
 */
for (let item of theaterAreaClick) {
    item.addEventListener('click', function () {
        for (let ti of theaterAreaClick) ti.classList.remove('selected');
        item.classList.add('selected');
    })
}


// 슬라이드 부분 구현
let pagenum = 0;
let btnLeft = document.getElementById('btn-left');
let btnRight = document.getElementById('tnb_step_btn_right');
let step = document.getElementsByClassName('step');
let tnb = document.getElementById("tnb");
let layerPopup = document.getElementsByClassName("ft_layer_popup");
let btn_ok = document.getElementsByClassName("btn_ok");

btnRight.addEventListener('click', function () {
    console.log(pagenum);
    if (pagenum == 2) {
        tnb.style.display = 'none';
    }
    console.log("btn right()");
    step[pagenum].style.display = 'none';
    step[pagenum + 1].style.display = 'block';
    tnb.className = 'tnb step' + (pagenum + 2);
    pagenum++;
    if (pagenum == 1) {
        placeholder[3].style.display = 'none';
        layerPopup[3].style.display = 'block';
        layerPopup[4].style.display = 'block';
        blackscreen[0].style.display = "block";
    }
    if (pagenum == 3) {
        let payment = document.getElementsByClassName("popup_reservation_check")[0].style.display = "block";
    }

});

// 레이어팝업 닫기
btn_ok[3].addEventListener('click', function () {
    layerPopup[3].style.display = 'none';
    blackscreen[0].style.display = "none";
})
btn_ok[4].addEventListener('click', function () {
    layerPopup[4].style.display = 'none';

})

btnLeft.addEventListener('click', function () {
    console.log(pagenum);
    console.log("btn left()");
    step[pagenum].style.display = 'none';
    step[pagenum - 1].style.display = 'block';
    tnb.className = 'tnb step' + (pagenum);
    pagenum--;

});


// 영화 스크롤 내려가기
$(".movieScroll").scroll(function () {
    let topY = $(this).scrollTop();
    $(".Movieslider-y").css("top", topY / 2);

})
// 날짜 스크롤 내려가기
$(".dateScroll").scroll(function () {
    let topY = $(this).scrollTop();
    $(".dateScroller-y").css("top", topY / 2);

})

// step2 인원 선택시 활성화 (어른)
/* Step 2 에 쓰이는 JS */

function adult_clickInit(){
    const adult_click = document.getElementsByClassName('adult_click');
    
}
for (let i = 0; i <= adult_click.length - 1; i++) {
    adult_click[i].addEventListener("click", function () {
        for (let j = 0; j <= adult_click.length - 1; j++) {
            adult_click[j].className = "adult_click";
            let dimmed = document.getElementById("dimmed");
            dimmed.classList.remove("dimmed");
            this.classList.add("selected");
            if (i == 0) {
                dimmed.classList.add("dimmed");
                if (youthNum != 0) {
                    dimmed.className = 'section section-seat';
                }
            }

        }
    })

}

// 청소년

let youthNum = 0;
let youth_click = document.getElementsByClassName("youth_click");
for (let i = 0; i <= youth_click.length - 1; i++) {
    youth_click[i].addEventListener("click", function () {
        for (let j = 0; j <= youth_click.length - 1; j++) {
            youth_click[j].className = "youth_click";
            let dimmed = document.getElementById("dimmed");
            dimmed.classList.remove("dimmed");
            this.classList.add("selected");
            if (i == 0) {
                dimmed.classList.add("dimmed");
                if (adultNum != 0) {
                    dimmed.className = 'section section-seat';
                }
            }
        }
    })

}


//step 3 간편결제 -> 카카오페이 설정시 활성화

$("#last_pay_radio3").on({
    click: function () {
        console.log("카카오페이!!");
        $("#payCredit").css("display", "none");
        $("#smartPayCon").css("display", "block").on({
            click: function () {

                $(".kakaopayDiv").css("display", "block");

            }
        })


    }
})
// 포인트 사용(닫기 아직안됨!!!)
$(".clickPoint").on({
    click: function () {
        if ($(".payPoint").css("display", "block")) {
            $(".payPoint").css("display", "none");
        }
        console.log("포인트!!");
        $(".payPoint").css("display", "block");
    }
})


/*
    EpsteinKim의 reservation_EpsteinKim.js
*/

const alpha = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
showSeat('H', '8', '0', '0');
seatInit();

function showSeat(rowVal, colVal, em_rowVal, em_colVal) {
    let sHtml = "";
    let st_row = convertNumber(rowVal) + 1; //seatHTML L == 12
    let st_col = colVal;    //seatHtml st_col 19

    let st_row_empty = em_rowVal.replaceAll(' ', '').toUpperCase().trim().split(','); //database G == 7
    let cnt = 0;
    st_row_empty.forEach(e_r => st_row_empty[cnt++] = String(convertNumber(e_r) + 1))

    let st_col_empty = em_colVal.replaceAll(' ', '').trim().split(',');  //database

    for (let row = 0; row < st_row; row++) {
        // 가로 좌석 생성
        sHtml += `<div id="${convertAlpha(row)}" class="row_div">`;
        sHtml += `<div class="row-text">${convertAlpha(row)}</div>`;
        for (let col = 0; col < st_col; col++) {
            // 세로 좌석 생성
            sHtml += `<div class="seat" id="${convertAlpha(row) + Number(col + 1)}">${col + 1}</div>`
            // 세로 통로 생성 부분
            st_col_empty.forEach(function (emptyCol) {
                if (emptyCol == col + 1) {
                    sHtml += `<div class="empty"></div>`;
                }
            })
        }
        sHtml += `</div>`;
        // 가로 통로 생성 부분
        st_row_empty.forEach(emptyRow => {
            if (emptyRow == row + 1) {
                sHtml += `<div class="empty"></div>`;
            }
        })
    }
    // 대상 id를 가지고 있는 컴포넌트
    document.getElementById('seats').innerHTML = sHtml;
}

function seatInit() {
    const allSeat = document.getElementsByClassName('seat');
    for (let i = 0; i < allSeat.length; i++) {
        let seat = allSeat[i];

        seat.addEventListener('click', function () {
            if (seat.classList.contains('selected')) seat.classList.remove('selected');
            else seat.classList.add('selected');
        })
    }
}

function convertAlpha(number) {
    return alpha[Number(number)];
}

function convertNumber(alph) {
    return Number(alpha.indexOf(alph));
}
