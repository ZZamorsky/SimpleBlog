    
    document.addEventListener("DOMContentLoaded", () => {
	loadArticles();
    document.getElementsByName('mainForm')[0].addEventListener('submit', event => {
    	event.preventDefault();
    	storeArticle(document.mainForm.content.value);
    	return false;
    });
});

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

const deleteArticle = (_content)=> {
	const req = new XMLHttpRequest();
	req.addEventListener('load', loadArticles);
    req.open("DELETE", "./api/articles");
    req.setRequestHeader('Content-Type', 'application/json');
    req.send(_content);
};

const editArticle = (id, _content) => {    
	const req = new XMLHttpRequest();
	req.addEventListener('load', loadArticles);
	req.open("UPDATE", "./api/articles");
	req.setRequestHeader('Content-Type', 'application/json');
	const updateArticle = {
			id:id
			content: _content,
    };
	req.send(JSON.stringify(updateArticle));
	
};

const createRow = (tableBody, article) => {
    const nameCell = document.createElement('td');
    nameCell.innerText = article.author.name;
    const idCell = document.createElement('td');
    idCell.innerText = article.id;
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
    	commentRow.append(commentAuthorCell, commentContentCell);
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
    articleRow.append(nameCell, contentCell, idCell,  deleteCell, updateCell);
    tableBody.append(articleRow);
    
};
