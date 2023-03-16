function getMembership() {
    var membershipType = $("#membershipType").val();

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/Library/api/memberships/getMembership",
        data: {
            membershipType: membershipType
        },
        success: function (response) {
            $("#fee").text(response.fee);
            $("#privilege").text(response.privilege);
        },
        error: function () {
            alert("An error occurred.");
        }
    });
}


