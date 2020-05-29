document.addEventListener("DOMContentLoaded", () => {
	loadArticles();
	const updateArt = document.getElementById('update-art');
    updateArt.onclick = function() {
    	updateArticle(
    			document.updateForm.articleId.value, 	
        		document.updateForm.content.value
        		);
    	};
    const updateCom = document.getElementById('update-com');
    updateCom.onclick = function() {
    	updateComment(
    			document.updateForm.articleId.value, 	
        		document.updateForm.content.value
    			);
    	};
    const comment = document.getElementById('comment-butt');
    comment.onclick = function() {
    	addComment(
    			document.updateForm.articleId.value, 	
        		document.updateForm.content.value
        		);
    	};
    document.getElementsByName('mainForm')[0].addEventListener('submit', event => {
    	event.preventDefault();
    	storeArticle(document.mainForm.content.value);
    	return false;
    });
});

const addComment = (artId, content) =>{
    const req = new XMLHttpRequest();
    req.addEventListener('load', loadArticles);
    req.open("POST", "./api/comments");
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
    req.setRequestHeader('Content-Type', 'application/json');
    req.send(_content);
};

const deleteComment = (id)=> {
	const req = new XMLHttpRequest();
	req.addEventListener('load', loadArticles);
    req.open("DELETE", "./api/comments");
    req.setRequestHeader('Content-Type', 'application/json');
    req.send(id);
};

const updateArticle = (artId, _content) => {    
	const req = new XMLHttpRequest();
	req.addEventListener('load', loadArticles);
	req.open("PUT", "./api/articles");
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
    const idCell = document.createElement('td');
    idCell.innerText = article.id;
    const contentParagraph = document.createElement('p');
    contentParagraph.innerText = article.content;
    const commentsTable = document.createElement('table');
    article.comments.forEach(comment => {
    	const commentRow = document.createElement('tr');
    	const commentAuthorCell = document.createElement('td');
    	commentAuthorCell.innerText = comment.author.name;
    	const commentContentCell = document.createElement('td');
    	commentContentCell.innerText = comment.content;
    	const commentIdCell = document.createElement('td');
    	commentIdCell.innerText = comment.id;
        const deleteCell = document.createElement('td');
        deleteCell.data = JSON.stringify(comment);
        deleteCell.addEventListener('click', function () {
        	deleteComment(this.data);
        });    
        const deleteCellLink = document.createElement('a');
        deleteCellLink.innerText = "delete";
        deleteCellLink.href = "#";    
        deleteCell.append(deleteCellLink);
    	commentRow.append(commentAuthorCell, commentContentCell, commentIdCell, deleteCell);
    	commentsTable.append(commentRow);
    	})
    contentCell.append(contentParagraph, commentsTable);
    const deleteCell = document.createElement('td');
    deleteCell.data = JSON.stringify(article);
    deleteCell.addEventListener('click', function () {
    	deleteArticle(this.data);
    });    
    const deleteCellLink = document.createElement('a');
    deleteCellLink.innerText = "delete";
    deleteCellLink.href = "#";    
    deleteCell.append(deleteCellLink);
    const articleRow = document.createElement('tr');
    articleRow.append(nameCell, contentCell, idCell, deleteCell);
    tableBody.append(articleRow);    
};
