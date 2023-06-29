function plus(){
    count = document.frm.amount; // name이 amount인 요소 
    sell_price = document.frm.sell_price;   // name이 sell_price인 요소
    sum = document.frm_sum.sum;
    count.value++; 
    sum.value = count.value * sell_price.value;
}

function minus(){
    count = document.frm.amount; // name이 amount인 요소 
    sell_price = document.frm.sell_price;   // name이 sell_price인 요소
    sum = document.frm_sum.sum;
    if(count.value > 1){
        count.value--; 
        sum.value = count.value * sell_price.value;
    }else{
        count.value = 1;
        alert("최소 수량은 1개입니다.");
    }
}
