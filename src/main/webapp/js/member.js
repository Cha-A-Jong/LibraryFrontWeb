function populateMemberTable(members) {
    console.log(members);
    var table = document.getElementById("member-table").getElementsByTagName("tbody")[0];
    for (var i = 0; i < members.length; i++) {
        var member = members[i];
        var row = table.insertRow(i);

        var idCell = row.insertCell(0);
        idCell.innerHTML = member.id;

        var firstnameCell = row.insertCell(1);
        firstnameCell.innerHTML = member.firstname;

        var lastnameCell = row.insertCell(2);
        lastnameCell.innerHTML = member.lastname;

        var dateofbirthCell = row.insertCell(3);
        dateofbirthCell.innerHTML = member.dateofbirth;

        var librarynumberCell = row.insertCell(4);
        librarynumberCell.innerHTML = member.librarynumber;

        var cbbidnumberCell = row.insertCell(5);
        cbbidnumberCell.innerHTML = member.cbbidnumber;

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
    var firstname = document.getElementById("firstname").value;
    var lastname = document.getElementById("lastname").value;
    var dateofbirth = document.getElementById("dateofbirth").value;
    var librarynumber = document.getElementById("librarynumber").value;
    var cbbidnumber = document.getElementById("cbbidnumber").value;
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
        "firstname": firstname,
        "lastname": lastname,
        "dateofbirth": dateofbirth,
        "librarynumber": librarynumber,
        "cbbidnumber": cbbidnumber,
        "borrowReceipt": {"id": borrowReceipt},
        "books": books
    }));

}
var form = document.getElementById("member-form");
form.addEventListener("submit", function (event) {
    event.preventDefault();
    submitMemberForm();
});

