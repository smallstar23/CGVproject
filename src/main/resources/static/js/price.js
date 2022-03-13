const updateBtn = document.getElementById('updateBtn');
const area = document.getElementById('area');
const theater = document.getElementById('theater');
const theaterPrice = document.getElementById('theaterPrice');
const areacode = document.getElementById('areacode');
const tcode = document.getElementById('tcode')


window.onload = function(){
    backToInit();
}

function backToInit(){

    if (areacode.value !== "none") {
        theater.disabled = false;
        findTheater(areacode.value);
        area.value = areacode.value;
    }
    if (tcode.value !== "none" && !theater.disabled) {
        read(tcode.value);
        for(let i = 0 ; i < 100000000; i++){} // 데이터 불러오기 시간지연용
        setDisplay('unset', updateBtn, theaterPrice);
        theater.value = tcode.value;
    }
}

function areaCheck() { // 지역 선택을 했을때
    toFirstStatus(theater);
    dataEmptify();
    setDisplay('none', updateBtn, theaterPrice);
    if (area.value !== "none") {
        theater.disabled = false;
        findTheater(area.value);
    }
}

function theaterCheck() { // 극장 선택을 했을 때
    setDisplay('none' , updateBtn, theaterPrice);
    dataEmptify();
    if (theater.value !== "none" && !theater.disabled) {
        read(theater.value);
        for(let i = 0 ; i < 100000000; i++){} // 데이터 불러오기 시간지연용
        setDisplay('unset', updateBtn, theaterPrice);
    }
}

function toFirstStatus(...DOM) {
    DOM.forEach(dom => {
        dom.disabled = true;
        dom.value = 'none';
    });
}
function setDisplay(noneOrUnset,...DOM) {
    DOM.forEach(dom => {
        dom.style.display = noneOrUnset;
    })
}
function findTheater(areacode) {
    theater.innerHTML = `<option value="none">극장 선택</option>`
    fetch('/api/findTheater/' + areacode)
        .then(response => response.json())
        .then(data => {
            data.forEach(theaterDTO => {
                theater.innerHTML += `<option value="${theaterDTO.tcode}">${theaterDTO.tname}</option>`
            });
        });
}

// select 관련한 스크립트

function goCreate(){
    location.href = '/admin/price/create?tcode='+theater.value;
}

function read(tcode){
    const adultPriceList = document.querySelectorAll('.adultPrice');
    const stuPriceList = document.querySelectorAll('.stuPrice');
    let cnt = 0;
    fetch('/api/price/read?tcode='+tcode)
        .then(response => response.json())
        .then(data => data.forEach(priceDTO => {
            adultPriceList[cnt].innerText = priceDTO.adultPrice+'원';
            stuPriceList[cnt++].innerText = priceDTO.stuPrice+'원';
        }));
}


function dataEmptify(){
    const adultPriceList = document.getElementsByClassName('adultPrice');
    const stuPriceList = document.getElementsByClassName('stuPrice');

    for(let i = 0 ; i < adultPriceList.length; i++){
        adultPriceList[i].innerText = '';
        stuPriceList[i].innerText = '';
    }
}