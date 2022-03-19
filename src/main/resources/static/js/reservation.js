// 스케쥴 불러오기
let mcode;
let tcode;

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
            movie_click[i].classList.add("selected");
            // mcode 저장
            mcode = movie_click[i].value;
        }
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

    fordate.innerHTML += `<li data-index="${i}" date="${init}" class="day passday checkdate dimmed"><a href="#" onclick="return false;"><span class="dayweek">${newdayArray[i]}</span><span class="day">${dateArray[i]}</span><span class="sreader"></span></a></li>`
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

window.onload = function(){
    theaterInit();
}


function theaterInit() {
    const remove = function(){ console.log('이벤트 초기화')};
    const theaterClick = document.querySelectorAll('.theaterClick');
    const dateScheduleExisted = document.querySelectorAll('.findSchedule');


    theaterClick.forEach(theater => {
        theater.addEventListener('click', function () {
            tcode = theater.value;
            toFirstDateStatus();
            transferTheaterName(theater);
            extraFunction(theaterClick);
            theater.classList.add('selected');
            dimmedOrNot();

            dateScheduleExistedInit();
        })
    });
}

function toFirstDateStatus() {
    const checkDate = document.querySelectorAll(".checkdate");

    checkDate.forEach(d => {
        d.classList.add('dimmed');
        d.classList.remove('selected');
    })
}

function transferTheaterName(theater) {
    const theaterTransferred = document.getElementsByClassName('sendTheaterName')[0];

    theaterTransferred.setAttribute('href', '/theaters/' + theater.value);
    theaterTransferred.setAttribute('title', theater.getAttribute('tname'));
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
    const regExp = '\\d{4}-\\d{2}-\\d{2}';
    const dateList = document.querySelectorAll('.checkdate');
    const result = findScheduleAjax(mcode, tcode);

    result.then(response => response.json())
        .then(data => {
            data.forEach(scheduleDTO => {
                const scdate = scheduleDTO.scdate.match(regExp)[0]; // 정규식 ex(2022-02-10)
                    dateList.forEach(date => {
                        if(date.getAttribute('date').match(regExp)[0] == scdate){
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

function dateScheduleExistedInit(){
    const dateScheduleExisted = document.querySelectorAll('.findSchedule');
    dateScheduleExisted.forEach(date => {
        date.onclick = function(){
            // addEventListener 같은경우 이벤트가 중복되어 바인딩 될수 있습니다. 그래서 결과가 중복으로 나올 수도 있구요.
            // 그래서 onclick이라는 이벤트 핸들러 프로퍼티를 사용했습니다.
            const result = findScheduleOnlyMcodeAjax(mcode, date.getAttribute('scdate'));
            result.then(response => response.json())
                .then(data => console.log(data));
        }
    })
}
function findScheduleOnlyMcodeAjax(mcode, scdate){
    if(mcode == null || scdate == null){
        alert('정보를 다시 입력하세요');
        return;
    }
    return fetch(`/api/findSchedule?mcode=${mcode}&scdate=${scdate}`)
}
function generateScheduleBoneHtml(scheduleDTO, tcode){
    const _hallDTO = scheduleDTO.hallDTO;
    const _movieDTO = scheduleDTO.movieDTO;
    if(_hallDTO.tcode !== tcode) return; // 해당 영화관의 스케줄이 아니므로 넘김

    let scheduleHtml = '';
    scheduleHtml += `<div class="theater" hcode="${_hallDTO.hcode}" mcode="${_movieDTO.mcode}">`;
    scheduleHtml += `<span class="title">
                        <span class="name">${_hallDTO.hname}</span>
                        <span class="floor">${_hallDTO.hguan + '관 ' + _hallDTO.location}</span>
                        <span class="seatcount">총 ${_hallDTO.seatSize + '석'}</span>
                    </span>`;
    scheduleHtml += `<ul>`
    /*
        알찬내용 여기에 들어가면 된다.
    */

    scheduleHtml += `</ul>`
    scheduleHtml += `</div>`
    return scheduleHtml
}

/*

for (let i = 0; i <= theaterClick.length - 1; i++) {
    theaterClick[i].addEventListener('click', function () {
        // 새로운 상영관을 클릭할때마다 달력부분 전체 다시 dimmed 되돌리고, selected 해제
        for (let a = 0; a <= checkdate.length - 1; a++) {
            checkdate[a].classList.add('dimmed')
            checkdate[a].classList.remove('selected')
            $("#selDate").val("");
        }
        // 상영관 하단 검은 부분으로 전달하기
        let theaterName = theaterSelect[i].innerText;
        placeholder[2].style.display = 'none';
        // 영화관 tcode전달해서 같이 href에 적용하기
        sendTheaterName[0].setAttribute('href', '/theaters/' + tcodeArray[i]);
        sendTheaterName[0].setAttribute('title', theaterName);
        sendTheaterName[0].innerText = theaterName + "CGV >";
        // tcode 저장
        tcode = theaterClick[i].value;
        for (let x = 0; x <= infoTheater.length - 1; x++) {
            infoTheater[x].style.display = 'block';
        }
        for (let j = 0; j <= theaterClick.length - 1; j++) {
            theaterClick[i].classList.add("selected");
            theaterClick[j].classList.remove("selected");

        }

        // 극장 클릭시에 영화, 극장 선택시 해당하는 스케쥴이 있는지 찾아오기
        fetch('/api/findSchedule', {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                mcode: mcode,
                tcode: tcode
            })
        }).then(response => response.json())
            .then(data => {
                for (let i = 0; i <= data.length - 1; i++) {
                    const schecodeArray = [];
                    const scdateArray = [];
                    scheduleList.innerHTML = "";
                    sendHallInfo[0].innerHTML = "";
                    schecodeArray[i] = data[i].schecode;
                    // 2022-03-16 형태로 string으로 저장해줌
                    scdateArray[i] = data[i].scdate.substring(0, 10);
                    for (let j = 0; j <= newcheckdate.length - 1; j++) {
                        // 데이터 날짜만큼 돌면서 달력과 같은 날짜가 존재할 경우 dimmed 제거
                        if (scdateArray[i] == newcheckdate[j]) {
                            checkdate[j].classList.remove('dimmed');
                            checkdate[j].classList.add('findSchedule');
                            // 스케쥴이 있는 경우 findSchedule 이라는 class를 추가하고 그 날짜를 클릭했을때 이벤트 추가
                            let findSchedule = document.getElementsByClassName('findSchedule');
                            for (let a = 0; a <= findSchedule.length - 1; a++) {
                                findSchedule[a].addEventListener('click', function () {

                                    // 가리고 있던 placeholder 제거
                                    schedultGuideText.classList.add('hidden')
                                    let scdate = findSchedule[a].getAttribute("date").split('(')[0];
                                    // 날짜에 해당하는 스케쥴 정보만 받아올 것
                                    if (scdate == scdateArray[i]) {
                                        scheduleList.innerHTML += ` <div class="theater">
                                        <span class="title">
                                        <span class="name">${data[i].hallDTO.hname}</span>
                                            <span class="floor">${data[i].hallDTO.hguan}관</span><span class="seatcount">(총 ${data[i].hallDTO.seatSize}석)</span></span>
                                        <ul>
                                            <li class="addSelected" value="${data[i].hallDTO.hguan}" onclick="screenTimeClickListener(${data[i].hallDTO.hcode})">
                                            <a class="button">
                                                <span class="time"><span>${data[i].scdate.substring(11, 16)}</span></span><span class="count">잔여좌석</span>
                                                <div class="sreader">종료시간 19:56</div>
                                                <span class="sreader mod"></span></a>
                                            </li>
                                         </ul>
                                    </div>
                                                `

                                        // 상단에 스케쥴 선택시에 같이 정보 전달하기
                                        let addSelected = document.getElementsByClassName('addSelected');
                                        for (let i = 0; i <= addSelected.length - 1; i++) {
                                            addSelected[i].addEventListener('click', function () {
                                                addSelected[i].classList.add('selected')

                                                sendHallInfo[0].innerHTML = `
                                                    <input style="background-color: #1d1d1c; color:#cccccc; font-weight: bold" value="${data[i].hallDTO.hguan}관">
                                                    `
                                            })
                                        }

                                    }
                                })
                            }

                        }
                    }
                }
            })
    })
}
*/


// 상영 시간을 클릭했을때

function screenTimeClickListener(hcode) {
    // 여기서 hall 코드 받아가면 됩니다.
    console.log(hcode);
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


// 날짜 클릭시 날짜 전달하기
let passday = document.getElementsByClassName("passday");
for (let i = 0; i <= passday.length - 1; i++) {
    passday[i].addEventListener('click', function () {
        for (let j = 0; j <= passday.length - 1; j++) {
            this.classList.add("selected");
            let clickDate = this.getAttribute("date");
            if (this.classList.contains('dimmed')) {
                this.classList.remove("selected");
                clickDate = "";
            }
            let sendDate = document.getElementsByClassName("sendDate")[0].innerHTML = `<input style="background-color: #1d1d1c; color:#cccccc; font-weight: bold" name="selDate" id="selDate" value="${clickDate}"></input>`;
            passday[j].classList.remove("selected");
            placeholder[2].style.display = 'none';
            for (let x = 0; x <= infoTheater.length - 1; x++) {
                infoTheater[x].style.display = 'block';
            }

        }

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
let adultNum = 0;
let adult_click = document.getElementsByClassName("adult_click");

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
