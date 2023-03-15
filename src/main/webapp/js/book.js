function populateBookTable(books) {
    var table = document.getElementById("book-table").getElementsByTagName("tbody")[0];
    for (var i = 0; i < books.length; i++) {
        var book = books[i];
        var row = table.insertRow(i);

        var idCell = row.insertCell(0);
        idCell.innerHTML = book.id;

        var isbnCell = row.insertCell(1);
        isbnCell.innerHTML = book.isbn;

        var titleCell = row.insertCell(2);
        titleCell.innerHTML = book.title;

        var subtitleCell = row.insertCell(3);
        subtitleCell.innerHTML = book.subtitle;

        // Add a new column for the edit button
        var editCell = row.insertCell(4);
        var editButton = document.createElement("button");
        editButton.innerHTML = "Edit";
        editButton.onclick = (function() {
            return function() {
                console.log("Edit button clicked for book id: ", book.id);
                // Show a form with the book details pre-filled
                var editForm = document.getElementById("edit-book-form");
                editForm.elements["id"].value = book.id;
                editForm.elements["isbn"].value = book.isbn;
                editForm.elements["title"].value = book.title;
                editForm.elements["subtitle"].value = book.subtitle;
                // Remove the event listener if it exists
                editForm.removeEventListener("submit", handleEditFormSubmit);
                // Add event listener to the submit button to update the book
                editForm.addEventListener("submit", handleEditFormSubmit);
                editForm.style.display = "block";
            }
        })(book.id);

        editCell.appendChild(editButton);

        // Add a new column for the delete button
        var deleteCell = row.insertCell(5);
        var deleteButton = document.createElement("button");
        deleteButton.innerHTML = "Delete";
        deleteButton.onclick = (function() {
            return function() {
                console.log("Delete button clicked for book id: ", book.id);
                deleteBook(book.id);
            }
        })(book.id);

        deleteCell.appendChild(deleteButton);
    }
}
function handleEditFormSubmit(event) {
    event.preventDefault();
    var editForm = document.getElementById("edit-book-form");
    updateBook({
        "id": editForm.elements["id"].value,
        "isbn": editForm.elements["isbn"].value,
        "title": editForm.elements["title"].value,
        "subtitle": editForm.elements["subtitle"].value
    });
    editForm.style.display = "none";
}

function getBooks() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8081/LibraryFrontWeb_war_exploded/api/book/bookList");
    xhr.onload = function () {
        if (xhr.status == 200) {
            var books = JSON.parse(xhr.responseText);
            populateBookTable(books);
        } else {
            alert("Error loading books");
        }
    };
    xhr.send();
}
getBooks();

function submitBookForm() {
    var form = document.getElementById("book-form");
    var isbn = document.getElementById("isbn").value;
    var title = document.getElementById("title").value;
    var subtitle = document.getElementById("subtitle").value;
    var author = document.getElementById("author-id").value;
    var genre = document.getElementById("genre-id").value;
    var borrowReceipt = document.getElementById("borrow-receipt-id").value;
    var members = [{"id": document.getElementById("member-id").value}];

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8081/LibraryFrontWeb_war_exploded/api/book/addBook");
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onload = function () {
        if (xhr.status == 200) {
            alert("Book added successfully!");
            form.reset();
        } else {
            alert("Error adding book");
        }
    };

    xhr.send(JSON.stringify({
        "isbn": isbn,
        "title": title,
        "subtitle": subtitle,
        "author": {"id": author},
        "genre": {"id": genre},
        "borrowReceipt": {"id": borrowReceipt},
        "members": members
    }));

}
var form = document.getElementById("book-form");
form.addEventListener("submit", function (event) {
    event.preventDefault();
    submitBookForm();
});

function deleteBook(bookId) {
    var xhr = new XMLHttpRequest();
    xhr.open("DELETE", "http://localhost:8081/LibraryFrontWeb_war_exploded/api/book/deleteBook/" + bookId);
    xhr.onload = function () {
        if (xhr.status === 200 || xhr.status === 204) {
            alert("Book deleted successfully!");
            location.reload();
        } else {
            alert("Error deleting book");
        }
    };
    console.log("DELETE request URL: ", xhr.responseURL);
    console.log("DELETE request payload: ", bookId);
    xhr.send(JSON.stringify({id: bookId}));
    console.log("DELETE request sent.");
}


function updateBook() {
    const title = document.getElementById("update-title").value;
    const author = document.getElementById("update-author").value;
    const publisher = document.getElementById("update-publisher").value;
    const category = document.getElementById("update-category").value;
    const id = document.getElementById("update-id").value;

    const book = {
        title: title,
        author: author,
        publisher: publisher,
        category: category
    };

    fetch(`http://localhost:8081/LibraryFrontWeb_war_exploded/api/book/update/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(book)
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            display_books();
        })
        .catch(error => console.error(error));
}

