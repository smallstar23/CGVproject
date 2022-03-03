

$(document).ready(function() {

$(".btn_ticket_print").on({
    click: function(){
        window.open("/user/ticket/home_ticket","ticket print","width=200,height:500,top:100,left=100");
    }
})

    // 예매가이드 팝업
    let guide=document.getElementsByClassName('button-guide');
    let guideClose=document.getElementsByClassName('guideClose');
    let popup =document.getElementsByClassName('popup_guide');
    let blackscreen=document.getElementsByClassName("blackscreen");


    guide[0].addEventListener('click', function(){
        console.log("가이드!");
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
            let movieName=document.getElementsByClassName('movieName')[i].getAttribute('title');
            console.log("영화 선택!");
            console.log(movieName);
            movieTitle[0].style.display='block';
            placeholder[1].style.display='none';
            let movieSel=document.getElementById("movie_sel");
            movieSel.innerText=movieName;
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
        }

    });

    // 레이어팝업 닫기
    btn_ok[3].addEventListener('click',function(){
        layerPopup[3].style.display='none';
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

    // 인원 선택시 활성화 (어른)
    let adultNum=0;
    let adult_click=document.getElementsByClassName("adult_click");
    for(let i=0; i<=adult_click.length-1;i++){
        adult_click[i].addEventListener("click",function(){
            for(let j=0; j<=adult_click.length-1;j++){
                adult_click[j].className="adult_click";
                let dimmed=document.getElementById("dimmed");
                dimmed.className="section section-seat";
                this.className="selected adult_click";
                adultNum=i;
                if(i==0){
                    dimmed.className='section section-seat dimmed';
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
                youth_click[j].className="adult_click";
                let dimmed=document.getElementById("dimmed");
                dimmed.className="section section-seat";
                this.className="selected youth_click";
                youthNum=i;
                if(i==0){
                    dimmed.className='section section-seat dimmed';
                    if(adultNum!=0){
                        dimmed.className='section section-seat';
                    }
                }
            }
        })

    }



    //일단 모든 좌석 거리두기, 예약 삭제
    let seat=document.getElementsByClassName("seat");
    for(let i=0; i<=seat.length-1;i++){
        console.log(i);
        seat[i].className="seat";
    }


})