document.addEventListener("DOMContentLoaded", function() {
    loadUsers();
    document.getElementById('another-button').addEventListener('click', function() {
        storeUser(document.mainForm.firstname.value, document.mainForm.lastname.value);
    });
});

function storeUser(firstName, lastName) {
    var req = new XMLHttpRequest();
    req.addEventListener('load', loadUsers);
    req.open("POST", "./api/users/");
    req.setRequestHeader('Content-Type', 'application/json');
    req.send(JSON.stringify({
        name: `${firstName} ${lastName}`
    }));
}

function loadUsers() {
    var req = new XMLHttpRequest();
    req.addEventListener('load', function() {
        var tableBody = document.getElementById('user-table');
        tableBody.innerHTML = '';
        var users = JSON.parse(this.responseText);
        users.forEach(function(user) {
            createRow(tableBody, user.id, user.name);
        });
    });
    req.open("GET", "./api/users/");
    req.send();
}

function createRow(tableBody, id, name) {
    var idCell = document.createElement('td');
    idCell.innerText = id;
    var nameCell = document.createElement('td');
    nameCell.innerText = name;
    var userRow = document.createElement('tr');
    userRow.append(idCell, nameCell);
    tableBody.append(userRow);
}
