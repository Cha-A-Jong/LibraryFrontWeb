function populateMemberTable(members) {
    console.log(members);
    var table = document.getElementById("member-table").getElementsByTagName("tbody")[0];
    for (var i = 0; i < members.length; i++) {
        var member = members[i];
        var row = table.insertRow(i);

        var idCell = row.insertCell(0);
        idCell.innerHTML = member.id;

        var firstnameCell = row.insertCell(1);
        firstnameCell.innerHTML = member.firstName;

        var lastnameCell = row.insertCell(2);
        lastnameCell.innerHTML = member.lastName;

        var dateofbirthCell = row.insertCell(3);
        dateofbirthCell.innerHTML = member.date_of_birth;

        var librarynumberCell = row.insertCell(4);
        librarynumberCell.innerHTML = member.library_number;

        var cbbidnumberCell = row.insertCell(5);
        cbbidnumberCell.innerHTML = member.cbb_id_number;

        //Add a new column for the delete button
        var deleteCell = row.insertCell(6);
        var deleButton = document.createElement("button");
        deleButton.innerHTML = "Delete";
        deleButton.onclick = (function() {
            return function() {
                console.log("Delete button clicked for member id: ", member.id);
                deleteMember(member.id);
            }
        })(member.id);

        deleteCell.appendChild(deleButton);
    }

    if (members.length === 0) {
        var row = table.insertRow(0);
        var cell = row.insertCell(0);
        cell.colSpan = 7;
        cell.innerHTML = "No members found";
    }
}

function getMembers() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8081/LibraryFrontWeb_war_exploded/api/member/memberList");
    xhr.onload = function () {
        if (xhr.status == 200) {
            var members = JSON.parse(xhr.responseText);
            populateMemberTable(members);
        } else {
            alert("Error loading members");
        }
    };
    xhr.send();
}
window.onload = function() {
    getMembers();
};


function submitMemberForm() {
    var form = document.getElementById("member-form");
    var firstName = document.getElementById("firstname").value;
    var lastName = document.getElementById("lastname").value;
    var date_of_birth = document.getElementById("dateofbirth").value;
    var library_number = document.getElementById("librarynumber").value;
    var cbb_id_number = document.getElementById("cbbidnumber").value;
    var borrowReceipt = document.getElementById("borrow-receipt-id").value;
    var books = [{"id": document.getElementById("book-id").value}];

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8081/LibraryFrontWeb_war_exploded/api/member/addMember");
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onload = function () {
        if (xhr.status == 200) {
            alert("Member added successfully!");
            form.reset();
        } else {
            alert("Error adding member");
        }
    };

    xhr.send(JSON.stringify({
        "firstName": firstName,
        "lastName": lastName,
        "date_of_birth": date_of_birth,
        "library_number": library_number,
        "cbb_id_number": cbb_id_number,
        "borrowReceipt": {"id": borrowReceipt},
        "books": books
    }));

}
var form = document.getElementById("member-form");
form.addEventListener("submit", function (event) {
    event.preventDefault();
    submitMemberForm();
});

