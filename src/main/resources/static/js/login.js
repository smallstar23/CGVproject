let acode = "none";
let tcode = "none";
const th = document.getElementById('theater');

function setAcode(areacode){
    if(areacode === "none") th.disabled = true;
    else th.disabled = false;
    acode = areacode;
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