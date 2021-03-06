document.addEventListener("DOMContentLoaded", () => {
    loadUsers();
    loadRoles();
    const butt1 = document.getElementById('update-butt');
    butt1.onclick = function() {
    	updateUser(
        			document.mainForm.ID.value, 	
        			document.mainForm.username.value, 
        			document.mainForm.password.value,
        			Array.from(document.mainForm.roles.selectedOptions).map(o => o.value)		
        	);
        };

    document.getElementsByName('mainForm')[0].addEventListener('submit', event => {
    	event.preventDefault();
    	storeUser(
    			document.mainForm.username.value, 
    			document.mainForm.password.value,
    			Array.from(document.mainForm.roles.selectedOptions).map(o => o.value)
		);
    	return false;

    });


});

const setCsrfHeader = (req)  => {
	const token = document.querySelector('meta[name="_csrf"]').getAttribute('content');
	const header = document.querySelector("meta[name='_csrf_header']").getAttribute("content");
	req.setRequestHeader(header,token);
};

const deleteUser = (id) => {
	const req = new XMLHttpRequest();
	console.log(id)
	req.addEventListener('load', loadUsers);
	req.open("DELETE", "./api/users/"+ id);
	setCsrfHeader(req);
	req.send();
	
};

const storeUser = (userName, password, roleIds) => {
    if(userName === ""){
        alert("Name Cannot Be Empty");}
    else if(password === ""){
    	alert("Password Cannot Be Empty");}
    else {
    const req = new XMLHttpRequest();
	req.addEventListener('load', loadUsers);
	req.open("POST", "./api/users");
	setCsrfHeader(req);
	req.setRequestHeader('Content-Type', 'application/json');
	const newUser = {
			name: userName,
			password: password,
			roles: roleIds.map(roleId => ({ id: roleId }))};
    
    req.send(JSON.stringify(newUser));}
    
};

const updateUser = (id, userName, password, roleIds) => {
    if(id === ""){
        alert("ID is mandatory and cannot Be Empty");}
    else {
    const req = new XMLHttpRequest();
	req.addEventListener('load', loadUsers);
	req.open("PUT", "./api/users");
	setCsrfHeader(req);
	req.setRequestHeader('Content-Type', 'application/json');
	const newUser = {
			id: id,
			name: userName,
			password: password,
			roles: roleIds.map(roleId => ({ id: roleId }))};
    
    req.send(JSON.stringify(newUser));}
    
};

const loadRoles = () => {
    const req = new XMLHttpRequest();
    req.addEventListener('load', () => {
        const rolesSelect = document.getElementById('role-selector');
        rolesSelect.innerHTML = '';
        const roles = JSON.parse(req.responseText);
        roles.forEach(role => {
        	const roleOption = document.createElement('option');
        	roleOption.value = role.id;
        	roleOption.innerText = role.name;
        	rolesSelect.append(roleOption);        	
        });
    });
    req.open("GET", "./api/roles");
    req.send();
};

const loadUsers = () => {
	const req = new XMLHttpRequest();
    req.addEventListener('load', () => {
        const tableBody = document.getElementById('user-table');
        tableBody.innerHTML = '';
        const users = JSON.parse(req.responseText);
        users.forEach(user => createRow(tableBody, user.id, user.name));
    });
    req.open("GET", "./api/users");
    req.send();
};

const createRow = (tableBody, id, name) => {
    const idCell = document.createElement('td');
    idCell.innerText = id;
    const nameCell = document.createElement('td');
    nameCell.innerText = name;
    const userRow = document.createElement('tr');
    const deleteCell = document.createElement('td');
    deleteCell.addEventListener('click', function () {
    	deleteUser(id);
    });    
    const deleteCellLink = document.createElement('a');
    deleteCellLink.innerText = "delete";
    deleteCellLink.href = "#";    
    deleteCell.append(deleteCellLink);
    userRow.append(idCell, nameCell, deleteCell);

    tableBody.append(userRow);
};
