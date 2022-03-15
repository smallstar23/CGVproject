const won = document.querySelectorAll('.won');
const monToThurWeekTableRow = document.getElementById('monToThur');
const friToSunWeekTableRow = document.getElementById('friToSun');
const tcode = document.getElementById('tcode');

//create에서만 사용
const adultPrice_week = monToThurWeekTableRow.querySelectorAll('.adultPrice > .won');
const stuPrice_week = monToThurWeekTableRow.querySelectorAll('.stuPrice > .won');
const adultPrice_holiday = friToSunWeekTableRow.querySelectorAll('.adultPrice > .won');
const stuPrice_holiday = friToSunWeekTableRow.querySelectorAll('.stuPrice > .won');
//create에서만 사용

const adultPrice = document.querySelectorAll('.adult');
const studentPrice = document.querySelectorAll('.student');
const delBtn = document.getElementById('_delete');
let isNewData = true;

// 처음 실행
window.onload = function(){
    addActionListener();
    isExist(); // isNewData의 초기화 결정
    read();
}
// 처음 실행

function submit(){

    if(!isNewData) {
        alert('관람가격 수정을 진행합니다.');
        priceUpdate();
        alert('생성 및 업데이트가 완료되었습니다.');
        location.href = '/admin/price?tcode='+tcode.value;
    }
    else {
        alert('관람가격 설정을 진행합니다.');
        priceCreate();
        alert('생성 및 업데이트가 완료되었습니다.');
        location.href = "/admin/price?tcode="+tcode.value;
    }

}
function _delete(){
    if(confirm('해당 관람가격 설정을 삭제하시겠습니까?')){
        //tcode만 넘겨주면 된다.
        fetch('/api/price/delete/'+tcode.value);
        alert('삭제가 완료되었습니다');
        location.href = '/admin/price';
    }
    else alert('취소되었습니다');
}
function priceCreate() {
    // 평일
    for (let time = 0; time < 3; time++) { // 0 = 모닝, 1 = 브런치, 2 = 일반
        let timeRange = ''; let startTime = ''; let endTime = '';
        if (time === 0) {
            timeRange = '모닝(06:00 ~)';
            startTime = '06:00';
            endTime = '10:00';
        }
        if (time === 1) {
            timeRange = '브런치(10:01 ~)';
            startTime = '10:01'
            endTime = '13:00';
        }
        if (time === 2) {
            timeRange = '일반(13:01 ~)';
            startTime = '13:01';
            endTime = '23:00';
        }

        fetch('/api/price/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                week: '월~목',
                slot: timeRange,
                startTime: startTime,
                endTime: endTime,
                adultPrice: adultPrice_week[time].value,
                stuPrice: stuPrice_week[time].value,
                tcode: tcode.value
            })
        });
    }

    // 휴일
    for (let time = 0; time < 3; time++) {
        let timeRange = ''; let startTime = ''; let endTime = '';
        if (time === 0) {
            timeRange = '모닝(06:00 ~)';
            startTime = '06:00';
            endTime = '10:00';
        }
        if (time === 1) {
            timeRange = '브런치(10:01 ~)';
            startTime = '10:01'
            endTime = '13:00';
        }
        if (time === 2) {
            timeRange = '일반(13:01 ~)';
            startTime = '13:01';
            endTime = '23:00';
        }
        fetch('/api/price/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                week: '금~일',
                slot: timeRange,
                startTime: startTime,
                endTime: endTime,
                adultPrice: adultPrice_holiday[time].value,
                stuPrice: stuPrice_holiday[time].value,
                tcode: tcode.value
            })
        })
            .then(response => response.json())
            .then(data => data)
            .catch(error => console.log(error));
    }

}
function priceUpdate(){

    for (let i = 0; i < 6; i++) {
        fetch('/api/price/update', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                pcode: adultPrice[i].id,
                adultPrice: adultPrice[i].value,
                stuPrice: studentPrice[i].value,
            })
        });
    }

}

function read(){
        let cnt = 0;
        fetch('/api/price/read?tcode='+tcode.value)
            .then(response => response.json())
            .then(data => data.forEach(priceDTO => {
                adultPrice[cnt].id = priceDTO.pcode;
                adultPrice[cnt].value = priceDTO.adultPrice;
                studentPrice[cnt++].value = priceDTO.stuPrice;
            }));
}
function isExist(){
    fetch('/api/price/exist?tcode=' + tcode.value)
        .then(response => response.json())
        .then(data =>
            isNewData = !data)
}

function addActionListener() {
    for (let i = 0 ; i < 12; i++) {
        won[i].addEventListener('blur', function () {
            won[i].value = stringWithoutCommas(won[i].value);
            won[i].value = numberWithCommas(won[i].value);
        })
    }
}

function stringWithoutCommas(x) {
    return x.toString().replace(',', '');
}

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

