MAX_PENIS_LENGTH = 25.0;

/**
 * Add a repeat function to String
 */
String.prototype.repeat = function(n) {
    return new Array(n+1).join(this);
}

/**
 * Guess.
 */
function makePenis(length) {
    return "8=" + "=".repeat(length) + "D";
}

/**
 * Make the summary text for the results.
 */
function makeLabel(phrase, ratio) {
    return "The phrase \"" + phrase + "\"" +
        " is " + (ratio * 100).toFixed(2) + "% pr0n!";
}

/**
 * Do ajax call for pr0n check and write result to resElem.
 */
function doSearch(inputElem, gauge, label) {
    var p = inputElem.val();
    $.ajax({
        url: '/pindex',
        dataType: 'json',
        data: {p: p},
        success: function(data) {
            $(gauge).text(makePenis(Math.round(data.ratio * MAX_PENIS_LENGTH)));
            $(label).text(makeLabel(data.phrase, data.ratio));
        }
    });
}

// Startup stuff
$(function() {
    $("#loading").hide();

    $("#loading").ajaxStart(function(){
        $(this).show();
    });

    $("#loading").ajaxStop(function(){
        $(this).fadeOut('slow');
    });

    var searchField = $("#search_field");
    var phraseField = $("#phrase_field");
    var gauge = $("#willy");
    var label = $("#text_results");

    searchField.submit(function() {
        doSearch(phraseField, gauge, label);
        return false;
    });
});
