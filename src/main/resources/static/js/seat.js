const alpha = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
const area = document.getElementById('area');
const theater = document.getElementById('theater');
const hall = document.getElementById('hall');
const rowList = document.getElementById('st_row');
const colList = document.getElementById('st_col');
const empty_row = document.getElementById('empty_row');
const empty_col = document.getElementById('empty_col');
const seats = document.getElementById('seats');

function areaCheck(){
    if (area.value !== "none") {
        theater.disabled = false;
        findTheater(area.value);
    }
}
function check() {
    // 초기화
    theater.disabled = true;
    hall.disabled = true;
    rowList.disabled = true;
    colList.disabled = true;
    empty_row.disabled = true;
    empty_col.disabled = true;
    seats.style.display = "none";

    // 1단계 검사
    if (area.value !== "none") {
        theater.disabled = false;
    }

    // 2단계 검사
    if (theater.value !== "none" && !theater.disabled) hall.disabled = false;

    // 마지막 검사
    if (hall.value !== "none" && !hall.disabled) {
        findHall(theater.value);
        // 초기화
        rowList.disabled = false;
        colList.disabled = false;
        empty_row.disabled = false;
        empty_col.disabled = false;
        seats.style.display = "block";
        rowList.value = 'H';
        colList.value = 8;
        empty_row.value = 0;
        empty_col.value = 0;

        isSeatHtmlExist(hall.value);
        //동작 실행
        showSeat(rowList.value, colList.value, empty_row.value, empty_col.value);
        seatInit();
    }
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
            if (seat.classList.contains('active')) seat.classList.remove('disabled');
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
    seatHtmlCreate();
}
function isSeatHtmlExist(hcode){
    fetch('/api/seatHtml/exist/'+hcode)
        .then(response => response.json()).then(data => {
           if(data) alert('seatHtml에 이미 존재합니다 update');
           else alert('seatHtml에 존재하지 않습니다. create');
        });
}
function seatHtmlCreate(){
    //SeatHtml의 데이터
    let _hcode = hall.value;
    let _st_row = rowList.value
    let _st_col = colList.value;
    let _row_empty = empty_row.value.replaceAll(' ', '').toUpperCase().trim();
    let _col_empty = empty_col.value.replaceAll(' ', '').trim();

    fetch('/api/seatHtml/create', {
        method:"POST",
        headers: {
            'Content-Type': 'application/json',
        },
        body : JSON.stringify({
            hcode : _hcode,
            stRow : _st_row,
            stCol : _st_col,
            rowEmpty : _row_empty,
            colEmpty : _col_empty
        })
    });
}
function findTheater(areacode){
    theater.innerHTML = `<option value="none">극장 선택</option>`
    fetch('/api/findTheater/'+areacode)
        .then(response => response.json())
        .then(data => {
            data.forEach(theaterDTO => {
                theater.innerHTML += `<option value="${theaterDTO.tcode}">${theaterDTO.tname}</option>`
            });
        })
}

function findHall(tcode){
    fetch('/api/findHall/' + tcode)
        .then(response => response.json())
        .then(data => console.log(data));
}