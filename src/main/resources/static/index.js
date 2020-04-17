
document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('not-working-cors-button').addEventListener('click', function() {
		var req = new XMLHttpRequest();
		req.addEventListener('load', function() {
			document.getElementById('main-text').innerText = this.responseText;
		});
		req.open("GET", "https://www.mlcuch.com/");
		req.send();
	});
	document.getElementById('another-button').addEventListener('click', function() {
		var req = new XMLHttpRequest();
		req.addEventListener('load', function() {
			document.getElementById('main-text').innerText = this.responseText;
		});
		req.open("GET", "https://www.mlcuch.com/testing.txt");
		req.send();
	});
});
