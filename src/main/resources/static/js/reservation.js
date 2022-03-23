// 스케쥴 불러오기
let mcode;
let tcode;
let schecode=null;
let hcode=null;
let pcode; //price idx
let memberIdx=document.getElementById('member_idx').value;

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
        movieSel.innerHTML = `<input style="background-color: #1d1d1c; color:#cccccc; font-weight: bold" name="movieName" id="movieName" value="${movieName}">`;
        movieSel.setAttribute("href", "/movies/detail-view/" + mcodeArray[i]);

        // 포스터 넣기
        // src 값을 찾아오기(타임리프로 받아서 array로 전달)
        let moviePoster = document.getElementById("movie_poster");
        moviePoster.style.display = 'block';
        moviePoster.src = url[i];

        // step3 예매정보 전달하기(영화정보만)
        document.querySelector('.movie_name > td' ).innerText=movieName;
        document.querySelector('.poster > img').setAttribute("src",  url[i])



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
    const theaterClick = qsAll('.theaterClick');

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
    const checkDate = qsAll(".checkdate");
    qsAll('#scheduleList li').forEach(list => list.classList.remove('selected'));
    qs('.movie_rating').firstElementChild.innerText = '';
    qs('.movie_type').firstElementChild.innerText = '';
    qs('#scheduleList').innerHTML = '';
    qs('.sendDate').innerText = '';
    qs('.sendHallInfo').innerText = '';
    $('#tnb_step_btn_right').removeClass('on');
    hcode=null; schecode=null;

    checkDate.forEach(d => {
        d.classList.add('dimmed');
        d.classList.remove('selected');
    })
}

function transferTheaterName(theater) {

    const theaterTransferred = document.getElementsByClassName('sendTheaterName')[0]
    theaterTransferred.setAttribute('href', '/theaters/' + theater.value);
    theaterTransferred.setAttribute('title', theater.getAttribute('tname'));
    theaterTransferred.innerText = theater.getAttribute('tname');
    theaterGuideText.style.display = 'none';
    // 예매정보에 극장정보 넘기기
    qs('.theater > td').innerText = theater.getAttribute('tname');

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
    if (tcode == null || mcode == null) return;

    const regExp = '\\d{4}-\\d{2}-\\d{2}';
    const dateList = qsAll('.checkdate');
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

    const findSchedule = qsAll('.findSchedule');

    // select 값 기입
    findSchedule.forEach(sc => sc.classList.remove('selected'));
    DOM.classList.add('selected')

    // 가이드라인 삭제
    schedultGuideText.style.display = 'none';

    // 하단에 정보 전달 (초기화 필요)
    qs('.sendDate').innerText = DOM.getAttribute('date');

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
    const theaterList = qsAll('#scheduleList > .theater');
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

        const startTime = scheduleDTO.scdate.split('T')[1].substring(0,5);
        //scdate = 2022-03-23T15:00:00  //split('T') = ['2022-03-23', '15:00:00'] // substring(0,5) = 15:00
        const endTemp = new Date(scheduleDTO.scdate.split('T')[0] + " " + scheduleDTO.scdate.split('T')[1]);
        endTemp.setMinutes(endTemp.getMinutes() + scheduleDTO.movieDTO.runtime);

        const endTime = `${addZero(endTemp.getHours())}:${addZero(endTemp.getMinutes())}`

        detailHtml += `<li remain_seat="120"  schecode="${scheduleDTO.schecode}" schedule="${scheduleDTO.scdate}" start_tm="${startTime.replace(':', '')}" endtime="${endTime}" hcode="${hcode}" mcode="${scheduleDTO.mcode}" movieRating="${scheduleDTO.movieDTO.movieRating}">
                        <a class="button">
                            <span class="time" onclick="schecodeSelect(this)">
                                <span>${startTime}</span>
                            </span>
                            <span class="count">남은 좌석</span>
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
    const schedule = parentList.getAttribute('schedule');

    qsAll('#scheduleList li').forEach(infoList => infoList.classList.remove('selected'));
    parentList.classList.add('selected');

    qs('.sendDate').setAttribute('end_time', parentList.getAttribute('endtime'))

    // step1 하단에 내용 전달하는 부분입니다.
    ticket_tnbInit(spanTitle, parentList, DOM);

   //  // step2 상단 내용 찍어주는 부분입니다.
   //  document.querySelector('.theater-info > .site').innerText=spanTitle.querySelector('.name').innerText
   //  document.querySelector('.theater-info > .screen').innerText=spanTitle.querySelector('.floor').innerText;
   //  document.querySelector('.playYMD-info > .date').innerText=schedule.substring(0,10);
   //  document.querySelector('.playYMD-info > .time').innerText=schedule.substring(11,16)+" ~ "+parentList.getAttribute('endtime');
   //  $('#tnb_step_btn_right').addClass('on');
   //
   //  // step3 결제 직전 예매정보 확인하는 부분입니다.
   // document.querySelector('.screen > td').innerText=spanTitle.querySelector('.floor').innerText;
   // document.querySelector('.movie_date > td').innerText=schedule.substring(0,10) +"  "+schedule.substring(11,16)+" ~ "+parentList.getAttribute('endtime');


   // step3 kakaopay 클릭시에 ticket table에 저장할 코드를 같이 보내줍니다. // step 3 준비를 왜 step 1에서 하나요??  ( 김영신 )
   //  document.querySelector('.reservation_info').innerHTML+=
   //      `
   //      <input type="hidden" name="selSeat" value="A1">
   //      <input type="hidden" name="schecode" value="${schecode}">
   //      `


    // 마지막 단계인 스케줄 코드까지 선택이 됬으므로 좌석 init을 실행함 ( step 2 준비 )
    seatHtmlReadAndCreate().then(seatRead)

    infoInit() // 관, 홀정보, 시간 표시 , 극장별 금액 초기화
}
// step1 하단에 내용 전달하는 부분입니다.
function ticket_tnbInit(spanTitle, parentList, DOM){
    qs('.sendHallInfo').innerText = spanTitle.querySelector('.floor').innerText;
    qs('.movie_type > span').innerText = spanTitle.querySelector('.name').innerText;
    qs('.movie_rating > span').innerText = parentList.getAttribute('movieRating');
    qs('.sendDate').innerText = qs('.findSchedule.selected')
            .getAttribute('date').replaceAll('-','.') + ' ' + DOM.firstElementChild.innerText;
}
function infoInit() {
    T_H_Init();
    playYMDInfoInit()
    priceInit();
}

function T_H_Init() {
    qs('.site').innerText = qs('.sendTheaterName').innerText; // 강남 CGV 표기
    qs('.site + span').innerText = qs('.sendHallInfo').innerText; // 관 표기
}

function playYMDInfoInit() {
    const dateText = qs('.sendDate').innerText // 2022.03.23(수) 03:01
    const date = dateText.match('\\d{4}.\\d{2}.\\d{2}')[0] // 2022.03.23
    const day = dateText.match('\\([가-힣]{1}\\)')[0]; // (수)
    const startTime = dateText.match('\\d{2}:\\d{2}')[0];// 03:01
    const endTime = qs('.sendDate').getAttribute('end_time'); // 05:57

    const playYMD = qs('.playYMD-info');
    const dateInit = playYMD.children[0];
    const dayInit = playYMD.children[1];
    const timeInit = playYMD.children[2];
    dateInit.innerText = date;
    dayInit.innerText = day;
    timeInit.innerText = `${startTime} ~ ${endTime}`;r
}

function priceInit(){ // 작업중
    const scdate = qs('.sendDate').innerText; // 2022.02.23(수) 03:01
    const startTime = scdate.split(' ')[1] // 03:01
    const week = scdate.match('[가-힣]')[0]; // (수) -> '수'

    getTheaterPriceAjax(tcode, startTime, week).then(re => re.json())
        .then(data=> theaterPriceInit(data));
}

function getTheaterPriceAjax(tcode, startTime, week){ // 작업중
    return fetch(`/api/ticket/getPrice?tcode=${tcode}&startTime=${startTime}&week=${week}`)
}

function theaterPriceInit(priceDTO){
    pcode = priceDTO.pcode;
    const adultPrice = qs('.row.payment-adult .price');
    const studentPrice = qs('.row.payment-youth .price');
    adultPrice.innerText = priceDTO.adultPrice;
    studentPrice.innerText = priceDTO.stuPrice;
}


function addZero(number) {
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
let payment = document.getElementsByClassName("popup_reservation_check");

// function으로 재구현
function fnright() {
    if(pagenum==2){
        // 결제창 띄우기
        payment[0].style.display = "block";

    }
    if(pagenum==1){
        console.log("btn right()");
        step[pagenum].style.display = 'none';
        step[pagenum+1].style.display = 'block';
        tnb.className = 'tnb step' + (pagenum + 2);
        pagenum++;
    }
    console.log(pagenum);
    if (pagenum == 0) {
        // 영화, 스케쥴을 선택하기 전이라면
        if (memberIdx == "") {
            alert("로그인 후 이용하세요.")
            location.href = '/user/login'
        } else if (schecode == null && hcode == null) {
            alert("영화와 스케쥴을 선택해주세요.");
        } else {
            console.log("btn right()");
            step[pagenum].style.display = 'none';
            step[pagenum + 1].style.display = 'block';
            tnb.className = 'tnb step' + (pagenum + 2);
            placeholder[3].style.display = 'none';
            layerPopup[3].style.display = 'block';
            layerPopup[4].style.display = 'block';
            blackscreen[0].style.display = "block";
            $('#tnb_step_btn_right').removeClass('on')
            pagenum++;
        }
    }



}

// 결제창 function
function fnclose(){
    payment[0].style.display = "none";

}


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


/*
*   step2 시작.
*
*
*/

let peopleNum = 0;
let adultNum = 0;
let youthNum = 0;

function adultCount(parentDOM) { // CustomerType = 1
    const tempNum = parentDOM.getAttribute('data-count');
    if(!countChecking(tempNum, youthNum)) return
    adultNum = Number(tempNum);
    peopleNum = adultNum + youthNum;
    mouseBlockOrNone();
    selectedInit(parentDOM);
    sendPeopleNumInit();
    seatGuideInit();
    infoPaymentTicketInit();
}

function youthCount(parentDOM) { // CustomerType = 2
    const tempNum = parentDOM.getAttribute('data-count');
    if(!countChecking(tempNum, adultNum)) return
    youthNum = Number(tempNum);
    peopleNum = adultNum + youthNum;
    mouseBlockOrNone();
    selectedInit(parentDOM)
    sendPeopleNumInit();
    seatGuideInit();
    infoPaymentTicketInit();
}

function countChecking(tempNum, target){
    if (Number(tempNum) + target > 6) {
        alert('6명이 초과되었습니다.');
        return false;
    }else if(Number(tempNum) + target < getSelectedSeatCount()){
        alert('현재 선택된 좌석보다 인원을 적게 설정할 수 없습니다.');
        return false;
    }
    return true;
}
function selectedInit(parentDOM) {
    for (let li of parentDOM.parentElement.children) li.classList.remove('selected');
    parentDOM.classList.add('selected');
}

function mouseBlockOrNone() {
    const mouseBlock = qs('.mouse_block');
    if (peopleNum == 0) mouseBlock.style.display = 'block';
    else mouseBlock.style.display = 'none';
}

function sendPeopleNumInit() {
    let adultNumText = `일반 ${adultNum}명`;
    let youthNumText = ` 청소년 ${youthNum}명`;

    if (adultNum == 0) {
        adultNumText = '';
        youthNumText.trim();
    }
    if (youthNum == 0) {
        youthNumText = '';
    }
    qs('#ticket_tnb .number .data').innerText = (adultNumText + youthNumText).replace('명 ', '명 , ');
}

function seatGuideInit() {
    if (peopleNum == 0) qs('.info.seat').style.display = 'none';
    else qs('.info.seat').style.display = 'block';
}


// seat 생성 부분 시작
const alpha = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];


function createSeat(rowVal, colVal, emptyRow, emptyCol) {
    showSeat(rowVal, colVal, emptyRow, emptyCol);
    seatInit();
}

function showSeat(rowVal, colVal, emptyRow, emptyCol) {
    let sHtml = "";
    let st_row = convertNumber(rowVal) + 1; //seatHTML L == 12
    let st_col = colVal;    //seatHtml st_col 19

    let st_row_empty = emptyRow.replaceAll(' ', '').toUpperCase().trim().split(','); //database G == 7
    let cnt = 0;
    st_row_empty.forEach(e_r => st_row_empty[cnt++] = String(convertNumber(e_r) + 1))

    let st_col_empty = emptyCol.replaceAll(' ', '').trim().split(',');  //database

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
    const allSeat = qsAll('#seats .seat');
    allSeat.forEach(seat => {
        seat.onclick = function () {
            // selected 추가
            if (seat.classList.contains('selected')) seat.classList.remove('selected');
            else seat.classList.add('selected');

            if(isOverSelected()) {
                seat.classList.remove('selected');
                alert('선택한 인원 수 보다 많이 좌석을 선택하실 수 없습니다.');
                return false;
            }

            sendAllSelectedSeatData(allSeat);
            infoPaymentTicketInit()
        }
    });
}

function infoPaymentTicketInit(){ // 하단 가격 전달
    const paymentDivList = qsAll('.payment-ticket > div');
    const paymentDivListExceptAmount = qsAll('.payment-ticket > div:not(div:last-child)');
    const adultPriceDiv = qs('.payment-adult');
    const youthPriceDiv = qs('.payment-youth');
    const amountPriceDiv = qs('.payment-final');
    const amountPrice = qs('.payment-final .price');
    const adultPriceQuantity = qs('.payment-adult .quantity');
    const youthPriceQuantity = qs('.payment-youth .quantity');
    const selectedSeatCount = getSelectedSeatCount();

    // none, block 조건 주입 ( style 초기화 )
    paymentDivList.forEach(div => div.style.display = 'none');
    if(peopleNum == 0) return false; // 선택된 인원이 없으면 실행안함


    // 인원별 가격 ( quantity 초기화 )
    let adultQuantity = 0; let youthQuantity = 0;
    for(let i = 1 ; i <= selectedSeatCount; i++){
        if(i <= adultNum) adultQuantity++;
        else if(i - adultNum <= youthNum) youthQuantity++;
    }
    adultPriceQuantity.innerText = adultQuantity;
    youthPriceQuantity.innerText = youthQuantity;

    if(adultQuantity !== 0) adultPriceDiv.style.display = 'block';
    if(youthQuantity !== 0) youthPriceDiv.style.display = 'block';
    if(adultQuantity !== 0 || youthQuantity !== 0) amountPriceDiv.style.display = 'block';

    let amount = 0;
    paymentDivListExceptAmount.forEach(div => {
        amount += div.querySelector('.price').innerText.replaceAll(',','') * div.querySelector('.quantity').innerText;
    })

    amountPrice.innerText = wonByComma(amount);
}


function wonByComma(won){ // 정규식을 안쓰고 콤마 생성
    let returnText = '';
    const wonString = String(won);
    let numComma = Math.floor(wonString.length / 3);
    if(wonString.length % 3 === 0) numComma--;

    if(numComma > 0) {
        const tempLength = wonString.length + numComma
        let count = wonString.length - 1; let repeat = 0;
        const array = [];
        for(let i = tempLength -1 ; i >= 0; i--){
            if(repeat === 3){
                array[i] = ',';
                repeat = 0;
                continue;
            }
            array[i] = wonString[count--];
            repeat++;
        }
        array.forEach(char => returnText += char);
        return returnText;
    }else{
        return won;
    }
}


function getSelectedSeatCount(){ // 현재 선택된 좌석들의 갯수를 불러온다 ( 자주쓰임 )
    let count = 0;
    qsAll('#seats .seat').forEach(seat => {
        if(seat.classList.contains('selected')) count ++;
    })
    return count;
}

function isOverSelected() {
    return getSelectedSeatCount() > Number(peopleNum);
}

function sendAllSelectedSeatData(seatList){
    const seatDataInit = qs('.seat_no .data');
    const seat_data = [];
    if(peopleNum === 0) seatDataInit.innerText = data;

    seatList.forEach(seat => {
        if(seat.classList.contains('selected')) seat_data.push(seat.id);
    })
    // 데이터를 오름차순 정렬후 사이 사이를 ,로 구분하여 보낸다
    seatDataInit.innerText = seat_data.sort().join(',');

}

function seatHtmlReadAndCreate() {
    return fetch('/api/seatHtml/read/' + hcode)
        .then(response => response.json())
        .then(data => {
            createSeat(data.stRow, data.stCol, data.rowEmpty, data.colEmpty);
        })
}

function seatRead() {
    const allSeat = qsAll('#seats .seat');
    fetch("/api/seat/read/" + hcode)
        .then(response => response.json())
        .then(data => {
            data.forEach(seatDTO => {
                for (let seat of allSeat) {
                    if (seatDTO.stNum === seat.id) {
                        if (seatDTO.disabled) seat.classList.add('disabled');
                        break;
                    }
                }
            })
        });
}

function convertAlpha(number) {
    return alpha[Number(number)];
}

function convertNumber(alph) {
    return Number(alpha.indexOf(alph));
}

// seat 생성 부분 종료

/*

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
*/


// step2 인원 선택시 활성화 (어른)
/* Step 2 에 쓰이는 JS */
//
// function adult_clickInit(){
//     const adult_click = document.getElementsByClassName('adult_click');
//
// }
// for (let i = 0; i <= adult_click.length - 1; i++) {
//     adult_click[i].addEventListener("click", function () {
//         for (let j = 0; j <= adult_click.length - 1; j++) {
//             adult_click[j].className = "adult_click";
//             let dimmed = document.getElementById("dimmed");
//             dimmed.classList.remove("dimmed");
//             this.classList.add("selected");
//             if (i == 0) {
//                 dimmed.classList.add("dimmed");
//                 if (youthNum != 0) {
//                     dimmed.className = 'section section-seat';
//                 }
//             }
//
//         }
//     })
//
// }
//
// // 청소년
//
// let youthNum = 0;
// let youth_click = document.getElementsByClassName("youth_click");
// for (let i = 0; i <= youth_click.length - 1; i++) {
//     youth_click[i].addEventListener("click", function () {
//         for (let j = 0; j <= youth_click.length - 1; j++) {
//             youth_click[j].className = "youth_click";
//             let dimmed = document.getElementById("dimmed");
//             dimmed.classList.remove("dimmed");
//             this.classList.add("selected");
//             if (i == 0) {
//                 dimmed.classList.add("dimmed");
//                 if (adultNum != 0) {
//                     dimmed.className = 'section section-seat';
//                 }
//             }
//         }
//     })
//
// }



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


function qs(selector, doc = document) {
    return doc.querySelector(selector);
}

function qsAll(selector, doc = document) {
    return doc.querySelectorAll(selector);
}