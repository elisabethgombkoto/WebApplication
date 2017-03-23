/**
 * Created by Elisabeth on 14.03.2017.
 */
function validateForm() {
    var f= document.forms[0];
    var fehler="";

    while(f.firstname.value=="") {
        var firstname = prompt("Bitte geben Sie Ihre Vorname ein!");
        if(firstname){
            f.firstname.value=firstname;
        }
    }

    while (f.lastname.value==""){
        var lastname = prompt("Bitte geben Sie Ihre Nachname ein!");
        if (lastname){
            f.lastname.value=lastname;
        }
    }

    while (f.email.value==""){
        var email = prompt("Bitte geben Sie Ihre Emailadresse ein!");
        if(email){
            f.email.value=email;
        }
    }

    if(!f.email.value.match(/^[A-Za-z][A-Za-z0-9_-]*(?:\.[A-Za-z][A-Za-z0-9_-]*)*@(?:[\w-]+\.)*\w[\w-]{0,20}\.[a-z]{2,6}$/)){
        var email=alert("Die Email ist ungültig.Der Namensteil einer gültigen E-Mail-Adresse soll nur mit einem Buchstaben beginnen dürfen. Danach darf eine beliebige Folge von Buchstaben, Zahlen, “_“ und “-“ auftreten. Ein “.“ darf immer nur einzeln (nicht gefolgt von anderen “.“) und nicht am Anfang oder Ende des Namensteils erscheinen.")
    }

    while (f.adress.value==""){
        var adress= prompt("Bitte geben Sie Ihre Adresse ein!");
        if(adress){
            f.adress.value=adress;
        }
    }
/*
das Passwort gültig ist (muss zwischen 6 und 11 Zeichen lang sein
und mit einem Buchstaben anfangen und darf nur Buchstaben,
Ziffern und “_“, keine Leer- oder Sonderzeichen enthalten) •
Passwort und dessen Bestätigung übereinstimmen
 */
    while (f.password.value=="") {
        var password = prompt("Bitte geben Sie Ihre Password");
        if (password) {
            f.password.value = password;
        }
    }
    while (f.password2.value=="") {
        var password2 = prompt("Bitte wiederholen Sie Ihre Password!");
        if (password2) {
            f.password.value = password2;
        }
    }
    if (f.password.value!=f.password2.value){
        var password = alert("Ihre Passwords stimmen nicht über ein!");
    }
    if(!(f.password.value.match(/^[A-Za-z][A-Za-z_0-9]{5,10}$/))){
        var password= alert("Das Passwort ist ungültig, es muss zwischen 6 und 11 Zeichen lang sein. Es muss mit einem Buchstaben anfangen, und darf nur Buchstaben, Ziffern und “_“enthalten!")
    }
    while (f.userid.value=="") {
        var userid = prompt("Bitte geben Sie Ihre UserID ein!");
        if (userid) {
            f.userid.value = userid;
        }
    }
/*
 die User-ID gültig ist
 (muss zwischen 5 und 12 Zeichen lang sein und darf nur Buchstaben und “_“,
  keine Leer- oder Sonderzeichen und keine Ziffern enthalten)
 */
    if(!(f.userid.value.match(/[A-Za-z_]{5,12}$/))){
        alert("Bitte geben Sie eine Userid in länge zwischen 5 und 12.")
    }


    if(!(f.gender[0].checked || f.gender[1].checked)){
        fehler+= "Geschlecht wurde nicht ausgewählt!"
    }
    if(!(f.over20.checked || f.over20plusDVD.checked || f.under20.checked || f.under20plusDVD.checked)){
        fehler+= "Jahresabonnementtyp wurde nicht ausgewählt!"
    }

    if(f.country.selectedIndex==0){
        fehler+="Statt";
    }
    if(fehler != ""){
        var fehlertext = "Die folgenden Felder wurden nicht vollständig ausgefüllt:?\n" ;
        fehlertext += fehler;
        alert(fehlertext);
     return false;
    }
    return true;
}

