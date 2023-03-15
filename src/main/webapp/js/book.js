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
                // Submit the form to update the book
                editForm.onsubmit = function(event) {
                    event.preventDefault();
                    updateBook({
                        "id": book.id,
                        "isbn": editForm.elements["isbn"].value,
                        "title": editForm.elements["title"].value,
                        "subtitle": editForm.elements["subtitle"].value
                    });
                };
                editForm.style.display = "block";
            }
        })(book.id);

        editCell.appendChild(editButton);

        //Add a new column for the delete button
        var deleteCell = row.insertCell(4);
        var deleButton = document.createElement("button");
        deleButton.innerHTML = "Delete";
        deleButton.onclick = (function() {
            return function() {
                console.log("Delete button clicked for book id: ", book.id);
                deleteBook(book.id);
            }
        })(book.id);

        deleteCell.appendChild(deleButton);
    }
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

function deleteBook(id) {
    var xhr = new XMLHttpRequest();
    xhr.open("DELETE", "http://localhost:8081/LibraryFrontWeb_war_exploded/api/book/deleteBook/" + id);
    xhr.onload = function () {
        if (xhr.status === 200 || xhr.status === 204) {
            alert("Book deleted successfully!");
            location.reload();
        } else {
            alert("Error deleting book");
        }
    };
    console.log("DELETE request URL: ", xhr.responseURL);
    console.log("DELETE request payload: ", id);
    xhr.send(JSON.stringify({id: id}));
    console.log("DELETE request sent.");

}

// Update Book
function updateBook() {
    var book = {
        id: parseInt(document.getElementById('book-id').value),
        isbn: document.getElementById('isbn').value,
        title: document.getElementById('title').value,
        subtitle: document.getElementById('subtitle').value,
        genre: document.getElementById('genre-id').value,
        authorId: parseInt(document.getElementById('author-id').value),
        borrowReceiptId: parseInt(document.getElementById('borrow-receipt-id').value),
        memberId: parseInt(document.getElementById('member-id').value)
    };

    fetch(`${url}/books/${book.id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(book)
    })
        .then(res => res.json())
        .then(() => {
            getBooks();
            document.getElementById('book-form').reset();
            document.getElementById('add-btn').style.display = 'block';
            document.getElementById('update-btn').style.display = 'none';
        })
        .catch(err => console.log(err));
}













