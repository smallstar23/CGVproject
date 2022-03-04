

$(document).ready(function() {

    // step 1. 예매가이드 팝업
    let guide=document.getElementsByClassName('button-guide');
    let guideClose=document.getElementsByClassName('guideClose');
    let popup =document.getElementsByClassName('popup_guide');
    let blackscreen=document.getElementsByClassName("blackscreen");


    guide[0].addEventListener('click', function(){
        popup[0].style.display='block';
        blackscreen[0].style.display='block';

    });

    guideClose[1].addEventListener('click', popupClose);
    guideClose[0].addEventListener('click', popupClose);
    function popupClose(){
        console.log("popup close()!")
        popup[0].style.display='none';
        blackscreen[0].style.display='none';
    };

    // 영화 선택시 하단에 정보 전달하는 부분인데 포스터를 어떻게 할지 더 고민해야함, 영화 id값으로 포스터를 찾아서 전달해야하나 고민중
    let placeholder=document.getElementsByClassName('placeholder');
    let movie_click=document.getElementsByClassName('movieClick');
    let movieTitle=document.getElementsByClassName('movie_title');

    for(let i=0;i<=movie_click.length-1;i++){
        movie_click[i].addEventListener('click',function(){
            for(let j=0; j<=movie_click.length-1; j++){
            //영화 클릭시 블랙 변경
            this.classList.add("selected");
            movie_click[j].classList.remove("selected");
            }

            // 영화명 전달
            let movieName=document.getElementsByClassName('movieName')[i].getAttribute('title');
            console.log("영화 선택!");
            console.log(movieName);
            movieTitle[0].style.display='block';
            placeholder[1].style.display='none';
            let movieSel=document.getElementById("movie_sel");
            movieSel.innerText=movieName;

            // 포스터넣기
            if(movieName=="극장판주술회전0"){
                let moviePoster=document.getElementById("movie_poster");
                moviePoster.style.display='block';
                moviePoster.src='/img/85603.jpg';
            };
            if(movieName=="언차티드"){
                let moviePoster=document.getElementById("movie_poster");
                moviePoster.style.display='block';
                moviePoster.src='/img/85624.jpg';
            };
            if(movieName=="더배트맨"){
                let moviePoster=document.getElementById("movie_poster");
                moviePoster.style.display='block';
                moviePoster.src='/img/85603.jpg';
            }

        });
    }


    // 슬라이드 부분 구현
    let pagenum=0;
    let btnLeft=document.getElementById('btn-left');
    let btnRight=document.getElementById('tnb_step_btn_right');
    let step=document.getElementsByClassName('step');
    let tnb=document.getElementById("tnb");
    let layerPopup=document.getElementsByClassName("ft_layer_popup");
    let btn_ok=document.getElementsByClassName("btn_ok");

    btnRight.addEventListener('click',function(){
        console.log(pagenum);
        if(pagenum==2){
            tnb.style.display='none';
        }
        console.log("btn right()");
        step[pagenum].style.display='none';
        step[pagenum+1].style.display='block';
        tnb.className='tnb step'+(pagenum+2);
        pagenum++;
        if(pagenum==1){
            placeholder[3].style.display='none';
            layerPopup[3].style.display='block';
            layerPopup[4].style.display='block';
            blackscreen[0].style.display="block";
        }

    });

    // 레이어팝업 닫기
    btn_ok[3].addEventListener('click',function(){
        layerPopup[3].style.display='none';
        blackscreen[0].style.display="none";
    })
    btn_ok[4].addEventListener('click',function(){
        layerPopup[4].style.display='none';

    })

    btnLeft.addEventListener('click',function(){
        console.log(pagenum);
        console.log("btn left()");
        step[pagenum].style.display='none';
        step[pagenum-1].style.display='block';
        tnb.className='tnb step'+(pagenum);
        pagenum--;

    });


// 영화 스크롤 내려가기
    $(".movieScroll").scroll(function(){
        console.log("스크롤!")
        let topY=$(this).scrollTop();
        $(".Movieslider-y").css("top",topY/2);

    })
// 날짜 스크롤 내려가기
    $(".dateScroll").scroll(function(){
        let topY=$(this).scrollTop();
        $(".dateScroller-y").css("top",topY/2);

    })

    // step2 인원 선택시 활성화 (어른)
    let adultNum=0;
    let adult_click=document.getElementsByClassName("adult_click");

    for(let i=0; i<=adult_click.length-1;i++){
        adult_click[i].addEventListener("click",function(){
            for(let j=0; j<=adult_click.length-1;j++){
                adult_click[j].className="adult_click";
                let dimmed=document.getElementById("dimmed");
                dimmed.classList.remove("dimmed");
                this.classList.add("selected");
                if(i==0){
                    dimmed.classList.add("dimmed");
                    if(youthNum!=0){
                        dimmed.className='section section-seat';
                    }
                }

            }
        })

    }

    // 청소년

    let youthNum=0;
    let youth_click=document.getElementsByClassName("youth_click");
    for(let i=0; i<=youth_click.length-1;i++){
        youth_click[i].addEventListener("click",function(){
            for(let j=0; j<=youth_click.length-1;j++){
                youth_click[j].className="youth_click";
                let dimmed=document.getElementById("dimmed");
                dimmed.classList.remove("dimmed");
                this.classList.add("selected");
                if(i==0){
                    dimmed.classList.add("dimmed");
                    if(adultNum!=0){
                        dimmed.className='section section-seat';
                    }
                }
            }
        })

    }



    //step 3 간편결제 -> 카카오페이 설정시 활성화

    $("#last_pay_radio3").on({
        click: function (){
            console.log("카카오페이!!");
            $("#payCredit").css("display","none");
            $("#smartPayCon").css("display","block").on({
                click:function(){

                    $(".kakaopayDiv").css("display","block");

                }
            })


        }
    })
    // 포인트 사용(닫기 아직안됨!!!)
    $(".clickPoint").on({
        click: function(){
            if($(".payPoint").show()){
                $(".payPoint").hide();
            }
            console.log("포인트!!");
            $(".payPoint").show();
            }
    })


    // step 4 마지막 출력 버튼 클릭시 활성화됨
    $(".btn_ticket_print").on({
        click: function(){
            window.open("/user/ticket/home_ticket","ticket print","width=100,height:500,top:100,left=100");
        }
    })

})