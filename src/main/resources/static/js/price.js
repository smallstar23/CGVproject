const updateBtn = document.getElementById('updateBtn');
const area = document.getElementById('area');
const theater = document.getElementById('theater');
const theaterPrice = document.getElementById('theaterPrice');
const areacode = document.getElementById('areacode');
const tcode = document.getElementById('tcode')

init();

function init(){
    area.value = areacode.value;
    areaCheck();
    theater.value = tcode.value;
    theaterCheck();
}

function areaCheck() { // 지역 선택을 했을때
    toFirstStatus(theater);
    setDisplay('none', updateBtn, theaterPrice);
    if (area.value !== "none") {
        theater.disabled = false;
        findTheater(area.value);
    }
}

function theaterCheck() { // 극장 선택을 했을 때
    setDisplay('none' , updateBtn, theaterPrice);
    if (theater.value !== "none" && !theater.disabled) {
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