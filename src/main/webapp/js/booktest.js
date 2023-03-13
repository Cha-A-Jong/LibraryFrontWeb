function populateBookTable(books){
    var table = document.getElementById("book-table").getElementsByTagName("tbody")[0];
    for (var i= 0; i < books.length; i++){
        var book = books[i];
        var row = table.insertRow(i);

        var idCell = row.insertCell(0);
        idCell.innerHTML = book.id;

        var isbnCell = row.insertCell(1);
        isbnCell.innerHTML = book.isbn;

        var titleCell = row.insertCell(2);
        titleCell.innerHTML = book.title;

        var subtitleCell = row.insertCell(3)
        subtitleCell.innerHTML = book.subtitle;

        //Add a new column for the delete button
        var deleteCell = row.insertCell(4);
        var deleButton = document.createElement("button");
        deleButton.innerHTML = "Delete";
        deleButton.onclick = function (){
            deleteBook(book.id);
        };
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
    var member = [document.getElementById("member-id").value];

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

    var form = document.getElementById("book-form");
    form.addEventListener("submit", function (event) {
        event.preventDefault();
        submitBookForm();
    });


    function deleteBook(id) {
        var xhr = new XMLHttpRequest();
        xhr.open("DELETE", "http://localhost:8081/LibraryFrontWeb_war_exploded/api/book/deleteBook");
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onload = function () {
            if (xhr.status === 200 || xhr.status === 204) {
                alert("Book deleted successfully!");
                location.reload();
            } else {
                alert("Error deleting book");
            }
        };
        xhr.send(JSON.stringify(id));
    }
}
