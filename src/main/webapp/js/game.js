$(function () {
    $("#playRound").click(function () {
        playRound();
    });

    function playRound() {
        $.ajax({
            type: 'GET',
            url: "http://localhost:8080/rockPaperScissorsGame-1.0-SNAPSHOT/game/playRound",
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
                console.log("Result: " + data);
                showRoundsPlayed();
            },
            error: function (e) {
                console.log("There was an error with your request...");
                console.log("error: " + JSON.stringify(e));
            }
        });
    }

    function showRoundsPlayed() {
        $.ajax({
            type: 'GET',
            url: "http://localhost:8080/rockPaperScissorsGame-1.0-SNAPSHOT/game/showRoundsPlayed",
            contentType: "application/json",
            dataType: 'json',            
            success: function (data) {
                populateDataTable(data);
            },
            error: function (e) {
                console.log("There was an error with your request...");
                console.log("error: " + JSON.stringify(e));
            }
        });
    }

    // populate the data table with JSON data
    function populateDataTable(data) {
        var table = $("#rounds-played tbody");
        table.empty();
        var count = 0;
        data.forEach(function(round) {
            count++;
            table.append("<tr><td>"+count+"</td><td>"+round.player1+"</td><td>"
                    +round.player2+"</td><td>"+round.result+"</td></tr>");
        });
        $("#number-rounds-played").val(count.toString());
    }
});