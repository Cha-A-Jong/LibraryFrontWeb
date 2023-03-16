function getReport() {
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const report = JSON.parse(xhr.responseText);
            const reportDiv = document.getElementById("report");
            let reportHTML = "<ul>";
            for (let i = 0; i < report.length; i++) {
                const book = report[i];
                reportHTML += `<li>${book.title} (${book.isbn})</li>`;
            }
            reportHTML += "</ul>";
            reportDiv.innerHTML = reportHTML;
        }
    };
    xhr.open("GET", "http://localhost:8081/LibraryFrontWeb_war_exploded/api/reports/quarter");
    xhr.send();
}
