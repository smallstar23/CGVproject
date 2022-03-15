

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
    let movie_click = document.getElementsByClassName('movieClick');
    let movieTitle = document.getElementsByClassName('movie_title');

    for (let i = 0; i <= movie_click.length-1; i++) {
        movie_click[i].addEventListener('click', function () {
            for(let k=0; k<movie_click.length;k++){
                movie_click[k].classList.remove("selected");
                movie_click[i].classList.add("selected");
                // mcode 저장
                mcode=movie_click[i].value;
            }
            // 영화명 전달
            let movieName = document.getElementsByClassName('movieName')[i].getAttribute('title');
            movieTitle[0].style.display = 'block';
            placeholder[1].style.display = 'none';
            let movieSel = document.getElementById("movie_sel");
            movieSel.innerHTML = `<input style="background-color: #1d1d1c; color:#cccccc; font-weight: bold" name="movieName" id="movieName" value="${movieName}"></input>`;
            movieSel.setAttribute("href","/movies/detail-view/"+mcodeArray[i]);

            // 포스터 넣기
            // src 값을 찾아오기(타임리프로 받아서 array로 전달)
            let moviePoster = document.getElementById("movie_poster");
            moviePoster.style.display = 'block';
            moviePoster.src = url[i];

        });
    }


    // 영화관 선택 부분 구현

    let theaterAreaClick = document.getElementsByClassName("theaterAreaClick");
    let theaterClick = document.getElementsByClassName("theaterClick");
    let areaSelect = document.getElementsByClassName("areaSelect");
    let theaterSelect = document.getElementsByClassName("theaterSelect");
    let infoTheater = document.getElementsByClassName("infoTheater");
    let sendTheaterName = document.getElementsByClassName("sendTheaterName");
    let area_theater_list = document.getElementsByClassName("area_theater_list");


let schecodeArray=new Array();
let scdateArray=new Array();
    for (let i = 0; i <= theaterClick.length - 1; i++) {
        theaterClick[i].addEventListener('click', function () {
            let theaterName = theaterSelect[i].innerText;
            placeholder[2].style.display = 'none';
            // 영화관 tcode전달해서 같이 href에 적용하기
            sendTheaterName[0].setAttribute('href', '/theaters/'+tcodeArray[i]);
            sendTheaterName[0].setAttribute('title', theaterName);
            sendTheaterName[0].innerText = theaterName +"CGV >";
            // tcode 저장
            tcode=theaterClick[i].value;
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
                    'Content-Type': 'application/json',},
                body: JSON.stringify({
                    mcode:mcode,
                    tcode:tcode
                })
            }).then(response=>response.json())
                .then(data=> {
                    for(let i=0; i<=data.length-1; i++){
                        schecodeArray[i]=data[i].schecode;
                        scdateArray[i]=data[i].scdate;
                    }
                })

            //


            })
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




    // 날짜 부분 오늘 날짜 기준으로 howmany 값 조절하면 +howmany 만큼 날짜 뿌릴 수 있음, 아직 월 넘어가는건 못하겠음..
    // 배열이나 객체같은 것들은 보통 const로 자료형을 잡아주는게 좋습니다.
    // 배열의 요소값들이 바뀌는 것이지 배열자체의 위치값이 바뀌는것이 아닙니다. (이유)
    // 그리고 howmany 만큼이 아니라 전에꺼였으면 howmany + 1 이었음 ㅡㅡ
    let howmany = 25;
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

    const fordate = document.getElementById("fordate");
    for (let i = 0; i < howmany; i++) {
        //2022.03.17(목)형식으로
          // month+1 추가, 1일 되면 현재 월에 +1
        if(dateArray[i]==1){
            month=month+1;
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
        if(month<10){
            newmonth="0"+month;
        }
        // 1~9일일때는 0추가
        if(dateArray[i]<10){
            dateArray[i]="0"+dateArray[i]
        }

        let init = year+"-"+newmonth+"-"+dateArray[i]+"("+newdayArray[i]+")";

        fordate.innerHTML += `<li data-index="1" date="${init}" class="day passday checkdate"><a href="#" onclick="return false;"><span class="dayweek">${newdayArray[i]}</span><span class="day">${dateArray[i]}</span><span class="sreader"></span></a></li>`


    }
// 가져온 스케쥴 리스트중에서 날짜가 같으면 클릭할 수 있게 해주기
    console.log(scdateArray)
    const newscdateArray=new Array(); // 가져온 스케쥴리스트 중 날짜 리스트인데, 날짜부분만 뽑아서 새로 저장하는중.....진행중임
    for(let i=0; i<=scdateArray.length-1; i++){
        console.log(scdateArray[i])
        // newscdateArray[i]=scdateArray[i].split("T")[0];
        // console.log(newscdateArray[i]);
    }


    // 달력에서 가져온 checkdate의 값을 "(" 기준으로 나눈 후 앞부분만 새로 array에 저장함
    const checkdate=document.getElementsByClassName("checkdate");
    const newcheckdate=new Array();
    for(let i=0; i<=checkdate.length-1; i++){
        newcheckdate[i]=checkdate[i].getAttribute("date").split("(")[0];
    }
console.log(newcheckdate)




// 날짜 클릭시 날짜 전달하기
    let passday = document.getElementsByClassName("passday");
    for (let i = 0; i <= passday.length - 1; i++) {
        passday[i].addEventListener('click', function () {
            for (let j = 0; j <= passday.length - 1; j++) {
                this.classList.add("selected");
                let clickDate = this.getAttribute("date");
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


