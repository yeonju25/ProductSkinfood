function loginCheck(){
    if(document.frm.id.value.length == 0){
        alert("아이디를 써주세요.");
        frm.id.focus();
        return false;
    }
    if(document.frm.pass.value == ""){
        alert("비밀번호를 입력해주세요.");
        frm.pass.focus();
        return false;
    }
    
    return true;
}