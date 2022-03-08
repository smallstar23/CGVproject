window.onload = function(){
    const allSeat = document.getElementsByClassName('seat');
    for(let i = 0 ; i<allSeat.length; i++){
        let seat = allSeat[i];

        seat.addEventListener('click', function(){
            let classLength = seat.classList.length;
            let actived = false;

            for(let i = 0 ; i < classLength; i++){
                if(seat.classList.item(i) == 'active'){
                    actived = true;
                    break;
                }
            }

            if(actived){
                seat.classList.remove('active');
            }else{
                seat.classList.add('active');
            }


        })
    }
}

function showSeat(){

    const alpha = {
        'A' : '1','B' : '2','C' : '3','D' : '4','E' : '5','F' : '6','G' : '7','H' : '8','I' : '9','J' : '10','K' : '11','L' : '12','M' : '13','N' : '14','O' : '15','P' : '16','Q' : '17','R' : '18','S' : '19','T' : '20','U' : '21'
    }

    let sHtml = "";
    let st_row = alpha.L; //seatHTML L == 12
    let st_col = 19;    //seatHtml st_col 19
    let st_row_empty = [alpha.G, alpha.K]; //database G == 7
    let st_col_empty = [4, 15];  //database

    for(let row = 0; row<st_row; row++){
        // 가로 좌석 생성
        sHtml += `<div id="${convertAlpha(row)}" class="row_div">`;
        sHtml += `<div class="row-text">${convertAlpha(row)}</div>`;
        for(let col=0; col<st_col; col++){
            // 세로 좌석 생성
            sHtml += `<div class="seat" id="${convertAlpha(row) + Number(col+1)}">${col+1}</div>`
            // 세로 통로 생성 부분
            st_col_empty.forEach(function(emptyCol){
                if(emptyCol == col+1){
                    sHtml += `<div class="empty"></div>`;
                }
            })
        }
        sHtml += `</div>`;
        // 가로 통로 생성 부분
        st_row_empty.forEach(emptyRow => {
            if(emptyRow == row+1){
                sHtml +=`<div class="empty"></div>`;
            }
        })
    }
    // 대상 id를 가지고 있는 컴포넌트
    document.getElementById('seats').innerHTML = sHtml;

}

function convertAlpha(number){
    let arr = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U'];
    return arr[Number(number)];
}

function getReserved(){
    const data = ['A12','B13','G5','A10']; //database
    data.forEach(seatId => {
        document.getElementById(seatId).classList.add('reserved');
    })
}