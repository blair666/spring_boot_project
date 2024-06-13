function compareQuotes() {
    const selectedQuotes = document.querySelectorAll('input[name="quoteIds"]:checked');
    if (selectedQuotes.length < 2) {
        alert("Please select at least two quotes to compare.");
        return;
    }
    document.getElementById('quoteForm').submit();
}