function validateForm() {
    var firstname = document.forms.mainForm.firstname.value;
    var lastname = document.forms.mainForm.lastname.value;
    if (!firstname || !lastname) {
        window.alert('Jmeno a prijmeni musi byt vyplneno');
        return false;
    }
    return true;
}

document.addEventListener("DOMContentLoaded", function() {
    document.getElementById('another-button').addEventListener('click', function() {
        window.alert('Button clicked');
        document.getElementById('main-text').innerText =
            `Welcome ${document.forms.mainForm.firstname.value} ${document.forms.mainForm.lastname.value}`;

        var req = new XMLHttpRequest();
        req.addEventListener('load', function() {
            document.getElementById('side-text').innerText = this.responseText;
        });
        req.open("GET", "https://www.mlcuch.com/testing.txt");
        req.send();
    });
});
