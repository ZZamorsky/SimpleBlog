
document.addEventListener('DOMContentLoaded', function() {
//	document.getElementById('another-button').addEventListener('click', function() {
//		window.alert('Button clicked');
//	});
//	document.getElementById('another-button').addEventListener('click', function() {
//		document.getElementById('main-text').innerText = 
//			`Welcome ${document.forms.mainForm.firstname.value} ${document.forms.mainForm.lastname.value}`;
//		//  "Welcome " + document.forms.mainForm.firstname.value + " " + document.forms.mainForm.lastname.value;
//	});
	document.getElementById('another-button').addEventListener('click', function() {
		var req = new XMLHttpRequest();
		req.addEventListener('load', function() {
			document.getElementById('main-text').innerText = this.responseText;
		});
		req.open("GET", "https://www.mlcuch.com/testing.txt");
		req.send();
	});
});


function validateForm() {
	//                            .[form-name].[field-name].[attribute-name]
	var firstname = document.forms.mainForm   .firstname   .value;
	var lastname = document.forms.mainForm.lastname.value;
	
	if (!firstname || !lastname) {
		window.alert('Jmeno a prijmeni musi byt vyplneno');
		return false;
	}
	
	return true;
}

