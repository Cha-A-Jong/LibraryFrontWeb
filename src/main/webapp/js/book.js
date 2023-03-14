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
    fetch(`http://localhost:8081/LibraryFrontWeb_war_exploded/api/book/deleteBook/${id}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (response.ok) {
                alert(`Book with ID ${id} deleted successfully!`);
                location.reload();
            } else {
                alert(`Error deleting book with ID ${id}`);
            }
        })
        .catch(error => {
            console.error(`Error deleting book with ID ${id}:`, error);
        });
}









