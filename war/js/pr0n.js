/**
 * Do ajax call for pr0n check and write result to resElem.
 */
function doSearch(inputElem, resElem) {
    resElem.empty();
    
    var p = inputElem.val();
    $.ajax({
        url: '/pindex',
        dataType: 'json',
        data: {p: p},
        success: function(data) {
            $(resElem)
                .append('<span/>')
                .addClass('result')
                .text(data.phrase + ", " + data.ratio);
        }
    });
}

// Startup stuff
$(function() {
    var searchField = $("#search_field");
    var phraseField = $("#phrase_field");
    var resElem = $("#results");

    searchField.submit(function() {
        doSearch(phraseField, resElem);
        return false;
    });
});