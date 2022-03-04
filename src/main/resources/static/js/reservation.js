$(document).ready(function () {

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

    // 영화 선택시 하단에 정보 전달하는 부분인데 포스터를 어떻게 할지 더 고민해야함, 영화 id값으로 포스터를 찾아서 전달해야하나 고민중

    let placeholder = document.getElementsByClassName('placeholder');
    let movie_click = document.getElementsByClassName('movieClick');
    let movieTitle = document.getElementsByClassName('movie_title');

    for (let i = 0; i <= movie_click.length - 1; i++) {
        movie_click[i].addEventListener('click', function () {
            for (let j = 0; j <= movie_click.length - 1; j++) {
                //영화 클릭시 블랙 변경
                this.classList.add("selected");
                movie_click[j].classList.remove("selected");
            }

            // 영화명 전달
            let movieName = document.getElementsByClassName('movieName')[i].getAttribute('title');
            console.log("영화 선택!");
            console.log(movieName);
            movieTitle[0].style.display = 'block';
            placeholder[1].style.display = 'none';
            let movieSel = document.getElementById("movie_sel");
            movieSel.innerText = movieName;

            // 포스터넣기
            if (movieName == "극장판주술회전0") {
                let moviePoster = document.getElementById("movie_poster");
                moviePoster.style.display = 'block';
                moviePoster.src = '/img/85603.jpg';
            }
            ;
            if (movieName == "언차티드") {
                let moviePoster = document.getElementById("movie_poster");
                moviePoster.style.display = 'block';
                moviePoster.src = '/img/85624.jpg';
            }
            ;
            if (movieName == "더배트맨") {
                let moviePoster = document.getElementById("movie_poster");
                moviePoster.style.display = 'block';
                moviePoster.src = '/img/85603.jpg';
            }

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


    for (let i = 0; i <= theaterClick.length - 1; i++) {
        theaterClick[i].addEventListener('click', function () {
            let theaterName = theaterSelect[i].innerText;
            placeholder[2].style.display = 'none';
            sendTheaterName[0].setAttribute('href', '/theaters');
            sendTheaterName[0].setAttribute('title', theaterName);
            sendTheaterName[0].innerText = theaterName + "CGV > ";
            for (let x = 0; x <= infoTheater.length - 1; x++) {
                infoTheater[x].style.display = 'block';
            }
            for (let j = 0; j <= theaterClick.length - 1; j++) {
                theaterClick[i].classList.add("selected");
                theaterClick[j].classList.remove("selected");

            }
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

    // 이부분 먹이면 겹치는 html 부분이 생겨서 스크립트 오류로 다음 기능들도 실행이 안됨....
    // 지역 선택 부분
    // for(let i=0; i<=theaterAreaClick.length; i++){
    //     areaSelect[i].addEventListener('click', function(){
    //         for(let j=0; j<=theaterAreaClick.length; j++){
    //             theaterAreaClick[i].classList.add("selected");
    //             theaterAreaClick[j].classList.remove("selected");
    //         }
    //     })
    // }


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
    console.log(fordate);
    for (let i = 0; i < howmany; i++) {
        //2022.03.17(목)형식으로
        let init = year+"."+month+"."+dateArray[i]+"("+newdayArray[i]+")";
        fordate.innerHTML += `<li data-index="1" date="${init}" class="day passday"><a href="#" onclick="return false;"><span class="dayweek">${newdayArray[i]}</span><span class="day">${dateArray[i]}</span><span class="sreader"></span></a></li>`
    }


    // 날짜 클릭시 날짜 전달하기
    let passday = document.getElementsByClassName("passday");
    for (let i = 0; i <= passday.length - 1; i++) {
        passday[i].addEventListener('click', function () {
            for (let j = 0; j <= passday.length - 1; j++) {
                this.classList.add("selected");
                let clickDate = this.getAttribute("date");
                let sendDate = document.getElementsByClassName("sendDate")[0].innerText = clickDate;
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
        console.log("스크롤!")
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


    // step 4 마지막 출력 버튼 클릭시 활성화됨
    $(".btn_ticket_print").on({
        click: function () {
            window.open("/user/ticket/home_ticket", "ticket print", "width=100,height:500,top:100,left=100");
        }
    })

})