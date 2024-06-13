document.getElementById('searchForm').addEventListener('submit', function (event) {
    event.preventDefault();
    const zipCode = document.getElementById('zipCode').value;
    fetch(`/vendors/search?zipCode=${zipCode}`)
        .then(response => response.json())
        .then(data => {
            const resultsBody = document.getElementById('resultsBody');
            resultsBody.innerHTML = '';
            data.forEach(vendor => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${vendor.name}</td>
                    <td>${vendor.category}</td>
                    <td>${vendor.rating}</td>
                    <td>${vendor.price}</td>
                    <td>
                        <button class="btn btn-info" onclick="viewMoreInfo(${vendor.id})">More Info</button>
                        <button class="btn btn-primary" onclick="requestQuote(${vendor.id})">Get Quote</button>
                    </td>
                `;
                resultsBody.appendChild(row);
            });
        });
});

function viewMoreInfo(vendorId) {
    // Logic to show more info about the vendor
    window.location.href = `/vendors/${vendorId}`;
}

function requestQuote(vendorId) {
    fetch(`/quotes/request?vendorId=${vendorId}`, { method: 'POST' })
        .then(response => response.json())
        .then(data => {
            alert('Quote requested successfully');
        })
        .catch(error => {
            console.error('Error requesting quote:', error);
        });
}
