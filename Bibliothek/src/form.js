/**
 * Created by Elisabeth on 14.03.2017.
 */
function validateForm() {
    var x = document.forms[""][""].value;
    if(firstname==""){
        x("Field must be filled out");
        return false;
    }
    if(lastname==""){
        x("Field must be filled out");
        return false;
    }
    if(password==""){
        x("Field must be filled out");
        return false;
    }
    if(password!=passwordrepet){
        x("Password and repeted password not equals");
        return false;
    }
}
