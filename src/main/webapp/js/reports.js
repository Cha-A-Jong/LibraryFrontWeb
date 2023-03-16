// getQuarterReport function
function getQuarterReport() {
    $.ajax({
        url: "http://localhost:8081/LibraryFrontWeb_war_exploded/api/reports/quarter",
        type: "GET",
        success: function(response) {
            response.sort(function(a, b) {
                return new Date(a.borrow_date) - new Date(b.borrow_date);
            });
            var reportData = document.getElementById('report-data');
            reportData.innerHTML = '';
            var ul = document.createElement('ul');
            response.forEach(function(item) {
                var li = document.createElement('li');
                li.innerHTML = 'ID: ' + item.id + ' Borrow Date: ' + item.borrow_date;
                ul.appendChild(li);
            });
            reportData.appendChild(ul);
        },
        error: function(xhr, status, error) {
            console.log("Error: " + error); // handle error
        }
    });
}

// getYearReport function
function getYearReport() {
    $.ajax({
        url: "http://localhost:8081/LibraryFrontWeb_war_exploded/api/reports/year",
        type: "GET",
        success: function(response) {
            response.sort(function(a, b) {
                return new Date(a.borrow_date) - new Date(b.borrow_date);
            });
            var reportData = document.getElementById('report-data');
            reportData.innerHTML = '';
            var ul = document.createElement('ul');
            response.forEach(function(item) {
                var li = document.createElement('li');
                li.innerHTML = 'ID: ' + item.id + ', Borrow Date: ' + item.borrow_date;
                ul.appendChild(li);
            });
            reportData.appendChild(ul);
        },
        error: function(xhr, status, error) {
            console.log("Error: " + error); // handle error
        }
    });
}

$(document).ready(function() {
    // Attach a click event to the button with ID quarter-btn
    $("#quarter-btn").click(getQuarterReport);

    // Attach a click event to the button with ID year-btn
    $("#year-btn").click(getYearReport);
});
