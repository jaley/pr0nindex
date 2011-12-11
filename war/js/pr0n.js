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
 * Add permalink and sharing for phrase.
 */
function addLinks(phrase, percentage) {

    $("#links").empty();
    var permalink_url = "http://pr0nindex.appspot.com/perma?p=" + escape(phrase);

    var twitter_script = document.createElement('script');
    twitter_script.type = 'text/javascript';
    twitter_script.src = 'http://platform.twitter.com/widgets.js'

    var twitter = $('<a/>')
        .addClass("twitter-share-button")
        .addClass("share-link")
        .attr('href', "http://twitter.com/share")
        .attr('data-url', permalink_url)
        .attr('data-counturl', "http://pr0nindex.appspot.com/")
        .attr('data-hashtags', "pr0nindex")
        .attr('data-text',  "\"" + phrase + "\" is " + percentage + "% pr0n!")
        .text("Tweet it!");

    var facebook_href = "//www.facebook.com/plugins/like.php?href="
        + escape(permalink_url)
        + "&amp;send=false&amp;layout=button_count&amp;width=450&amp;show_faces=false&amp;action=like&amp;colorscheme=light&amp;font=verdana&amp;height=21"

    var facebook = $('<iframe/>')
        .addClass("share-link")
        .attr('src', facebook_href)
        .attr('scrolling', "no")
        .attr('frameborder', "0")
        .attr('style', "border:none; overflow:hidden; height:30px;")
        .attr('allowTransparency', "true")
             
    $("#links")
        .append(
            $('<a/>')
                .attr('href', permalink_url)
                .text("Permalink"))
        .append($('<br/>'))
        .append($('<br/>'))
        .append(twitter)
        .append(twitter_script)
        .append($('<br/>'))
        .append(facebook);
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
            addLinks(data.phrase, (data.ratio * 100).toFixed(2));
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
