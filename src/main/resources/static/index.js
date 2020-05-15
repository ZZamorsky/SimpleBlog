document.addEventListener("DOMContentLoaded", () => {
	loadArticles();
    document.getElementsByName('mainForm')[0].addEventListener('submit', event => {
    	event.preventDefault();
    	storeArticle(document.mainForm.content.value);
    	return false;
    });
});

const loadArticles = () => {
    const req = new XMLHttpRequest();
    req.addEventListener('load', () => {
        const tableBody = document.getElementById('article-list');
        tableBody.innerHTML = '';
        const articles = JSON.parse(req.responseText);
        articles.forEach(article => createRow(tableBody, article));
    });
    req.open("GET", "./api/articles/pageable");
    req.send();
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
    	commentRow.append(commentAuthorCell, commentContentCell);
    	commentsTable.append(commentRow);
    })
    contentCell.append(contentParagraph, commentsTable);
    const articleRow = document.createElement('tr');
    articleRow.append(nameCell, contentCell);
    
    tableBody.append(articleRow);
    
};

document.addEventListener("DOMContentLoaded", () => {
	loadArticles();
    });
;
