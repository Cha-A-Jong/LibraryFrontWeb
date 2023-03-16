function getMembership() {
    // Get the selected membership type
    const membershipType = document.getElementById("membershipType").value;

    // Make an AJAX request to retrieve the membership details
    $.ajax({
        url: "http://localhost:8081/LibraryFrontWeb_war_exploded/api/memberships/getMembership",
        type: "POST",
        data: { type: membershipType },
        success: function(response) {
            // Update the fee and privilege elements with the returned data
            $("#fee").text(response.fee);
            $("#privilege").text(response.privilege);
        },
        error: function(error) {
            console.log(error);
        }
    });
}



