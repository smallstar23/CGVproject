const alpha = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
const area = document.getElementById('area');
const theater = document.getElementById('theater');
const hall = document.getElementById('hall');
const rowList = document.getElementById('st_row');
const colList = document.getElementById('st_col');
const empty_row = document.getElementById('empty_row');
const empty_col = document.getElementById('empty_col');
const seats = document.getElementById('seats');
let isNewSeatHtml = true;
let st_idx = '';

function areaCheck() {
    toFirstStatus(theater, hall, rowList, colList);
    seats.style.display = "none";
    if (area.value !== "none") {
        theater.disabled = false;
        findTheater(area.value);
    }
}

function theaterCheck() {
    toFirstStatus(hall, rowList, colList);

    if (theater.value !== "none" && !theater.disabled) {
        hall.disabled = false;
        findHall(theater.value);
    }
}

function check() {
    // 초기화
    toFirstStatus(rowList, colList);

    if (hall.value !== "none" && !hall.disabled) {
        isSeatHtmlExist(hall.value);
        // 존재한다면 기존에 좌석배치도를 불러오고
        // 존재하지 않는다면 새로운 좌석배치도를 불러옴
    }
}

function toFirstStatus(...DOM) {
    DOM.forEach(dom => {
        dom.disabled = true;
        dom.value = 'none';
    });
    toDisabledByTrueOrFalse(true, empty_row, empty_col);
    seats.style.display = 'none';
    st_idx = '';
}

function toDisabledByTrueOrFalse(isTrue, ...DOM) {
    if (isTrue) DOM.forEach(dom => dom.disabled = true);
    else DOM.forEach(dom => dom.disabled = false);
}

function createSeat() {
    showSeat(rowList.value, colList.value, empty_row.value, empty_col.value);
    seatInit();
}

function showSeat(rowVal, colVal, em_rowVal, em_colVal) {
    let sHtml = "";
    let st_row = convertNumber(rowVal) + 1; //seatHTML L == 12
    let st_col = colVal;    //seatHtml st_col 19

    let st_row_empty = em_rowVal.replaceAll(' ', '').toUpperCase().trim().split(','); //database G == 7
    let cnt = 0;
    st_row_empty.forEach(e_r => st_row_empty[cnt++] = String(convertNumber(e_r) + 1))

    let st_col_empty = em_colVal.replaceAll(' ', '').trim().split(',');  //database

    for (let row = 0; row < st_row; row++) {
        // 가로 좌석 생성
        sHtml += `<div id="${convertAlpha(row)}" class="row_div">`;
        sHtml += `<div class="row-text">${convertAlpha(row)}</div>`;
        for (let col = 0; col < st_col; col++) {
            // 세로 좌석 생성
            sHtml += `<div class="seat" id="${convertAlpha(row) + Number(col + 1)}">${col + 1}</div>`
            // 세로 통로 생성 부분
            st_col_empty.forEach(function (emptyCol) {
                if (emptyCol == col + 1) {
                    sHtml += `<div class="empty"></div>`;
                }
            })
        }
        sHtml += `</div>`;
        // 가로 통로 생성 부분
        st_row_empty.forEach(emptyRow => {
            if (emptyRow == row + 1) {
                sHtml += `<div class="empty"></div>`;
            }
        })
    }
    // 대상 id를 가지고 있는 컴포넌트
    document.getElementById('seats').innerHTML = sHtml;
}

function seatInit() {
    const allSeat = document.getElementsByClassName('seat');
    for (let i = 0; i < allSeat.length; i++) {
        let seat = allSeat[i];

        seat.addEventListener('click', function () {
            if (seat.classList.contains('disabled')) seat.classList.remove('disabled');
            else seat.classList.add('disabled');
        })
    }
}

function convertAlpha(number) {
    return alpha[Number(number)];
}

function convertNumber(alph) {
    return Number(alpha.indexOf(alph));
}

function submit() {

    // 조건 셀렉트가 다 활성화 되있는가.
    if (rowList.disabled) {
        alert('정보를 입력해주세요');
        return;
    }
    if (isNewSeatHtml) {
        if(confirm('좌석 배치도를 이대로 등록하시겠습니까?')) {
            seatHtmlCreate();
            seatCreate();
            toFirstStatus(theater, hall, rowList, colList);
        }
        else alert('좌석 배치도 등록을 취소하셨습니다');
    }
    else {
        if(confirm('좌석 배치도를 이대로 수정하시겠습니까?')) {
            seatHtmlUpdate();
            seatUpdate();
            toFirstStatus(theater, hall, rowList, colList);
        }
        else alert('좌석 배치도 수정을 취소하셨습니다.');
    }
}
function _delete(){
    // 조건 셀렉트가 다 활성화 되있는가.
    if (rowList.disabled) {
        alert('정보를 입력해주세요');
        return;
    }
    if(isNewSeatHtml) {
        alert('좌석배치도가 등록되어 있지 않습니다.');
    }else{
        if(confirm('정말로 해당 좌석배치도를 삭제하시겠습니까?')) {
            seatDelete(hall.value);
            seatHtmlDelete();
            toFirstStatus(theater, hall, rowList, colList);
        }
        else alert('좌석배치도 삭제가 취소되었습니다.');
    }
}
function isSeatHtmlExist(hcode) {
    fetch('/api/seatHtml/exist/' + hcode)
        .then(response => response.json())
        .then(data => {
            if (data) {
                isNewSeatHtml = false;
                alert('기존에 있던 좌석 배치도를 변경합니다.');
                toDisabledByTrueOrFalse(false, rowList, colList, empty_row, empty_col);
                seatHtmlRead(hcode);
                seats.style.display = 'block';
                seatRead(hcode);
                document.getElementById('submit').innerText = '수정'
                document.getElementById('delete').style.display = 'unset';
            } else {
                isNewSeatHtml = true;
                alert('새로운 좌석 배치도를 생성합니다.');
                toDisabledByTrueOrFalse(false, rowList, colList, empty_row, empty_col);
                rowList.value = 'H';
                colList.value = 8;
                empty_row.value = 0;
                empty_col.value = 0;
                seats.style.display = "block";
                createSeat();
                document.getElementById('submit').innerText = '등록'
                document.getElementById('delete').style.display = 'none';
            }
        });
}

function seatHtmlCreate() {
    fetch('/api/seatHtml/create', {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            hcode: hall.value,
            stRow: rowList.value,
            stCol: colList.value,
            rowEmpty: empty_row.value.replaceAll(' ', '').toUpperCase().trim(),
            colEmpty: empty_col.value.replaceAll(' ', '').trim()
        })
    }).catch(error => alert(error))
    alert('좌석 배치도가 등록되었습니다.');
}
function seatHtmlRead(hcode) {
    fetch('/api/seatHtml/read/' + hcode)
        .then(response => response.json())
        .then(data => {
            st_idx = data.stIdx;
            rowList.value = data.stRow;
            colList.value = data.stCol;
            empty_row.value = data.rowEmpty;
            empty_col.value = data.colEmpty;
            createSeat();
        })
}
function seatHtmlUpdate() {
    fetch('/api/seatHtml/update', {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            stIdx: st_idx,
            hcode: hall.value,
            stRow: rowList.value,
            stCol: colList.value,
            rowEmpty: empty_row.value,
            colEmpty: empty_col.value
        })
    });
    alert('좌석 배치도가 수정되었습니다.');
}

function seatHtmlDelete(){
    fetch('/api/seatHtml/delete/'+st_idx);
    alert('좌석 배치도가 삭제되었습니다');
}
// seat 부분
function seatCreate(){
    const allSeat = document.querySelectorAll('.seat');
    allSeat.forEach(seat => {
        let intDisabled = seat.classList.contains('disabled')? 1 : 0;
        SeatCreateAjax(hall.value, seat.id, intDisabled);
    })
}
function seatUpdate(){
    const allSeat = document.querySelectorAll('.seat');
    allSeat.forEach(seat => {
        let intDisabled = seat.classList.contains('disabled')? 1 : 0;
        seatUpdateAjax(hall.value, seat.id, intDisabled);
    })
}

function SeatCreateAjax(hcode, stNum, disabled){
    fetch('/api/seat/create', {
        method: "POST",
        headers : {
            'Content-Type' : 'application/json',
        },
        body: JSON.stringify({
            stNum: stNum,
            hcode: hcode,
            disabled: disabled,
        }),
    });
}
function seatUpdateAjax(hcode, stNum, disabled){
    fetch('/api/seat/update', {
        method:"POST",
        headers:{
            'Content-Type' : 'application/json',
        },
        body:JSON.stringify({
            stNum: stNum,
            hcode: hcode,
            disabled: disabled,
        })
    });
}

function seatRead(hcode){
    const allSeat = document.getElementsByClassName('seat');
    fetch("/api/seat/read/"+ hcode)
        .then(response => response.json())
        .then(data => {
            data.forEach(seatDTO => {
                for(let j = 0 ; j < allSeat.length; j++){
                    if(seatDTO.stNum === allSeat[j].id){
                        if(seatDTO.disabled) allSeat[j].classList.add('disabled');
                        break;

                    }
                }
            })
        });
}
function seatDelete(hcode){
    fetch("/api/seat/delete/"+hcode);
}

function findTheater(areacode) {
    theater.innerHTML = `<option value="none">극장 선택</option>`
    fetch('/api/findTheater/' + areacode)
        .then(response => response.json())
        .then(data => {
            data.forEach(theaterDTO => {
                theater.innerHTML += `<option value="${theaterDTO.tcode}">${theaterDTO.tname}</option>`
            });
        })
}

function findHall(tcode) {
    hall.innerHTML = `<option value="none">상영관 선택</option>`
    fetch('/api/findHall/' + tcode)
        .then(response => response.json())
        .then(data => data.forEach(hallDTO => {
            hall.innerHTML += `<option value="${hallDTO.hcode}">${hallDTO.hguan+'관'}</option>`
        }));
}

