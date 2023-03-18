function getIndividualMembership() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8081/LibraryFrontWeb_war_exploded/api/memberships/getMembership?membershipType=INDIVIDUAL_MEMBERSHIP", true);
    xhr.onload = function () {
        if (this.status === 200) {
            const response = JSON.parse(this.responseText);
            const table = "<table><tr><th>Membership Type</th><th>Fee</th><th>Privilege</th></tr>" +
                "<tr><td>" + response.membershipType + "</td><td>" + response.fee + "</td><td>" + response.privilege + "</td></tr></table>";
            const result = document.getElementById("result");
            result.innerHTML = table;
        }
    }
    xhr.send();
}

function getFamilyMembership() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8081/LibraryFrontWeb_war_exploded/api/memberships/getMembership?membershipType=FAMILY_MEMBERSHIP", true);
    xhr.onload = function () {
        if (this.status === 200) {
            const response = JSON.parse(this.responseText);
            const table = "<table><tr><th>Membership Type</th><th>Fee</th><th>Privilege</th></tr>" +
                "<tr><td>" + response.membershipType + "</td><td>" + response.fee + "</td><td>" + response.privilege + "</td></tr></table>";
            const result = document.getElementById("result");
            result.innerHTML = table;
        }
    }
    xhr.send();
}

function getStudentMembership() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8081/LibraryFrontWeb_war_exploded/api/memberships/getMembership?membershipType=STUDENT_MEMBERSHIP", true);
    xhr.onload = function () {
        if (this.status === 200) {
            const response = JSON.parse(this.responseText);
            const table = "<table><tr><th>Membership Type</th><th>Fee</th><th>Privilege</th></tr>" +
                "<tr><td>" + response.membershipType + "</td><td>" + response.fee + "</td><td>" + response.privilege + "</td></tr></table>";
            const result = document.getElementById("result");
            result.innerHTML = table;
        }
    }
    xhr.send();
}





