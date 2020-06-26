document.addEventListener("DOMContentLoaded", () => {
	loadArticles();
    document.getElementsByName('mainForm')[0].addEventListener('submit', event => {
    	event.preventDefault();
    	storeArticle(document.mainForm.content.value);
    	return false;
    });
});

const setCsrfHeader = (req)  => {
	const token = document.querySelector('meta[name="_csrf"]').getAttribute('content');
	const header = document.querySelector("meta[name='_csrf_header']").getAttribute("content");
	req.setRequestHeader(header,token);
};

const addComment = (artId, content) =>{
    const req = new XMLHttpRequest();
    req.addEventListener('load', loadArticles);
    req.open("POST", "./api/comments");
	setCsrfHeader(req);
    req.setRequestHeader('Content-Type', 'application/json');
    const newArticle = {    		
    	content: content,
    	id: artId
    };
    req.send(JSON.stringify(newArticle));
}

const storeArticle = (_content) => {
    const req = new XMLHttpRequest();
    req.addEventListener('load', loadArticles);
    req.open("POST", "./api/articles");
	setCsrfHeader(req);
    req.setRequestHeader('Content-Type', 'application/json');
    const newArticle = {
    	content: _content
    };
    req.send(JSON.stringify(newArticle));
};

const loadArticles = () => {
    const req = new XMLHttpRequest();
    req.addEventListener('load', () => {
        const tableBody = document.getElementById('article-list');
        tableBody.innerHTML = '';
        const articles = JSON.parse(req.responseText);
        articles.forEach(article => createRow(tableBody, article));
    });
    req.open("GET", "./api/articles");
    req.send();
};

const loadComments = () => {
    const req = new XMLHttpRequest();
    req.addEventListener('load', () => {
        const tableBody = document.getElementById('article-list');
        tableBody.innerHTML = '';
        const comments = JSON.parse(req.responseText);
        comments.forEach(comment => createRow(tableBody, comment));
    });
    req.open("GET", "./api/comments");
    req.send();
};

const deleteArticle = (_content)=> {
	const req = new XMLHttpRequest();
	req.addEventListener('load', loadArticles);
    req.open("DELETE", "./api/articles");
	setCsrfHeader(req);
    req.setRequestHeader('Content-Type', 'application/json');
    req.send(_content);
};

const deleteComment = (id)=> {
	const req = new XMLHttpRequest();
	req.addEventListener('load', loadArticles);
    req.open("DELETE", "./api/comments");
	setCsrfHeader(req);
    req.setRequestHeader('Content-Type', 'application/json');
    req.send(id);
};

const updateArticle = (artId, _content) => {    
	const req = new XMLHttpRequest();
	req.addEventListener('load', loadArticles);
	req.open("PUT", "./api/articles");
	setCsrfHeader(req);
	req.setRequestHeader('Content-Type', 'application/json');
	const updateArticle = {
			id:artId,
			content: _content
    };
	req.send(JSON.stringify(updateArticle));
	
};

const updateComment = (artId, _content) => {    
	const req = new XMLHttpRequest();
	req.addEventListener('load', loadArticles);
	req.open("PUT", "./api/comments");
	setCsrfHeader(req);
	req.setRequestHeader('Content-Type', 'application/json');
	const updateComment = {
			id:artId,
			content: _content
    };
	req.send(JSON.stringify(updateComment));
	
};

const createRow = (tableBody, article) => {
    const nameCell = document.createElement('td');
    nameCell.innerText = article.author.name;
    const contentCell = document.createElement('td');
    const contentParagraph = document.createElement('p');
    contentParagraph.innerText = article.content;
    const commentsTable = document.createElement('table');
    article.comments.forEach(comment => {
    	const commentRow = document.createElement('tr');
    	const commentAuthorCell = document.createElement('td');
    	commentAuthorCell.innerText = comment.author.name;
    	const commentContentCell = document.createElement('td');
    	commentContentCell.innerText = comment.content;
		const updateButton = document.createElement('button');
		updateButton.innerText = "UPDATE COMMENT"
		updateButton.data = JSON.stringify(comment)
		updateButton.addEventListener('click', function () {
			const updateDiv = document.createElement('div');
			const updateField = document.createElement('input');
			const updateButton = document.createElement('button');
			updateButton.innerText = 'save update';
			updateField.value = comment.content;
			updateButton.addEventListener('click', function(){
				
				updateComment(comment.id, updateField.value)
			});
			updateDiv.append(updateField, updateButton);
			commentContentCell.append(updateDiv);
		}); 	
		const deleteButton = document.createElement('button');
    	deleteButton.innerText = "DELETE COMMENT";
		deleteButton.data = JSON.stringify(comment);
    	deleteButton.addEventListener('click', function () {
    		deleteComment(this.data);
		});
    	commentRow.append(commentAuthorCell, commentContentCell, updateButton, deleteButton);
    	commentsTable.append(commentRow);
    	});
    const commentDiv = document.createElement('div');
    const commentField = document.createElement('input');
    const commentButton = document.createElement('button');
    commentButton.innerText = 'NEW COMMENT';
    commentButton.addEventListener('click', function () {
    	addComment(article.id, commentField.value);
    });    
    commentDiv.append(commentField, commentButton);
    contentCell.append(contentParagraph, commentsTable, commentDiv);

    const deleteButton = document.createElement('button');
    deleteButton.innerText = "DELETE ARTICLE";
	deleteButton.data = JSON.stringify(article);
    deleteButton.addEventListener('click', function () {
    	deleteArticle(this.data);
    });
	const updateButton = document.createElement('button');
	updateButton.innerText = "UPDATE ARTICLE"
	updateButton.data = JSON.stringify(article)
	updateButton.addEventListener('click', function () {
		const updateDiv = document.createElement('div');
		const updateField = document.createElement('input');
		const updateButton = document.createElement('button');
		updateButton.innerText = 'SAVE UPDATE';
		updateField.value = article.content;
		updateButton.addEventListener('click', function(){
			updateArticle(article.id, updateField.value)
		});
		updateDiv.append(updateField, updateButton);
		contentCell.append(updateDiv);
	}); 
	const articleRow = document.createElement('tr');
    articleRow.append(nameCell, contentCell, updateButton, deleteButton);
    tableBody.append(articleRow);    
};
