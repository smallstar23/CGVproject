const won = document.getElementsByClassName('won');
const monToThurWeekTableRow = document.getElementById('monToThur');
const friToSunWeekTableRow = document.getElementById('friToSun');
const tcode = document.getElementById('tcode');

addAction();

function submit(){
    priceCreate();
}
function priceCreate() {
    const adultPrice_week = monToThurWeekTableRow.querySelectorAll('.adultPrice > .won');
    const stuPrice_week = monToThurWeekTableRow.querySelectorAll('.stuPrice > .won');
    const adultPrice_holiday = friToSunWeekTableRow.querySelectorAll('.adultPrice > .won');
    const stuPrice_holiday = friToSunWeekTableRow.querySelectorAll('.stuPrice > .won');

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
        })
            .then(response => response.json())
            .then(data => data)
            .catch(error => console.log(error));
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
    alert('등록이 완료되었습니다');
    location.href = '/admin/price?tcode='+tcode.value;
}

function addAction() {
    for (let i in won) {
        won[i].style.width = '75px';
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

