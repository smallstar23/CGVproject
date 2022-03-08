let acode = "none";
let tcode = "none";
const th = document.getElementById('theater');


// 지역코드 따라 데이터 불러오기 .. 아직 안됨
function setAcode(areacode){
    if(areacode === "none") th.disabled = true;
    else th.disabled = false;
    acode = areacode;
    console.log(acode);
   $.ajax({
        url: "/areacode", // 클라이언트가 요청을 보낼 서버의 URL 주소
        data: { acode: acode },                // HTTP 요청과 함께 서버로 보낼 데이터
        type: "POST",                             // HTTP 요청 방식(GET, POST)
       dataType: "json"                         // 서버에서 보내줄 데이터의 타입
    })
        // HTTP 요청이 성공하면 요청한 데이터가 done() 메소드로 전달됨.
        .done(function(res) {
            $("#theater").append(`<option value='16'>강변CGV</option>`);

        })
        // HTTP 요청이 실패하면 오류와 상태에 관한 정보가 fail() 메소드로 전달됨.
        .fail(function(xhr, status, errorThrown) {
            console.log(xhr)
            console.log(status)
        })
        // HTTP 요청이 성공하거나 실패하는 것에 상관없이 언제나 always() 메소드가 실행됨.
        .always(function(xhr, status) {
            console.log("ajax실행함")
        });
}

function setTcode(theaterCode){
    tcode = theaterCode;
}

function login() {
    let useridVal=document.getElementById("userid").value;
    let userpwVal=document.getElementById("userpw").value;
    if (useridVal=="admin" && userpwVal=="1111") {
        alert("환영합니다!");
        postToUrl("/admin/main/post", {'tcode' : tcode} ,"post");
    }else if(acode === "none"){
        alert('지역을 선택해주세요');
    }else if(tcode === "none"){
        alert('극장을 선택해주세요');
    }
    else {
        alert("아이디 및 비밀번호를 확인해주세요")
    }
}

function postToUrl(path, params, method) {
    let form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    for(let key in params) {
        let hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", key);
        hiddenField.setAttribute("value", params[key]);
        form.appendChild(hiddenField);
        // <form action=path method=post>
        //<input type="hidden" name = "tcode" value = "hi">
        //<input type="hidden" name = "tcode" value = "hi">
    }
    document.body.appendChild(form);
    form.submit();
}