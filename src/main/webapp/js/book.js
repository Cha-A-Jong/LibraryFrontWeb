// Define endpoint URLs
const BOOK_LIST_URL = 'http://localhost:8081/LibraryFrontWeb_war_exploded/api/book/bookList';
const ADD_BOOK_URL = 'http://localhost:8081/LibraryFrontWeb_war_exploded/api/book/addBook';
const DELETE_BOOK_URL = 'http://localhost:8081/LibraryFrontWeb_war_exploded/api/book/deleteBook';
const UPDATE_BOOK_URL = 'http://localhost:8081/LibraryFrontWeb_war_exploded/api/book/update';

// Function to send GET request and display list of books
function displayBookList() {
    fetch(BOOK_LIST_URL)
        .then(response => response.json())
        .then(books => {
            let bookListHtml = '<ul>';
            for (const book of books) {
                bookListHtml += `<li>${book.title} by ${book.author} (${book.genre})`
                bookListHtml += `<button onclick="displayEditForm(${book.id})">Edit</button>`;
                bookListHtml += `<button onclick="deleteBook(${book.id})">Delete</button></li>`;
            }
            bookListHtml += '</ul>';
            document.getElementById('bookList').innerHTML = bookListHtml;
        });
}

// Function to send POST request to add a book
function addBook() {
    const title = document.getElementById('title').value;
    const author = document.getElementById('author').value;
    const genre = document.getElementById('genre').value;
    fetch(ADD_BOOK_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({title, author, genre})
    })
        .then(() => displayBookList());
}

// Function to send DELETE request to delete a book
function deleteBook(id) {
    fetch(`${DELETE_BOOK_URL}/${id}`, {
        method: 'DELETE'
    })
        .then(() => displayBookList());
}

// Function to display edit form for a book
function displayEditForm(id) {
    fetch(`${BOOK_LIST_URL}/${id}`)
        .then(response => response.json())
        .then(book => {
            document.getElementById('editForm').innerHTML = `
        <input type="hidden" id="bookId" value="${book.id}">
        <label for="title">Title:</label>
        <input type="text" id="title" value="${book.title}">
        <label for="author">Author:</label>
        <input type="text" id="author" value="${book.author}">
        <label for="genre">Genre:</label>
        <input type="text" id="genre" value="${book.genre}">
        <button onclick="updateBook()">Save</button>
      `;
        });
}

// Function to send PUT request to update a book
function updateBook() {
    const id = document.getElementById('bookId').value;
    const title = document.getElementById('title').value;
    const author = document.getElementById('author').value;
    const genre = document.getElementById('genre').value;
    fetch(`${UPDATE_BOOK_URL}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({title, author, genre})
    })
        .then(() => {
            document.getElementById('editForm').innerHTML = '';
            displayBookList();
        });
}


// Display initial list of books
displayBookList();
