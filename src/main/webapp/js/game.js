$(function () {
    $(document).ready(reload()); 
    
    function reload() {
        showRoundsPlayed(); 
        showAllRoundsPlayed();
    }
    
    $("#playRound").click(function () {
        playRound();
    });

    function playRound() {
        $.ajax({
            type: 'GET',
            url: "http://localhost:8080/rockPaperScissorsGame/game/playRound",
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
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
            url: "http://localhost:8080/rockPaperScissorsGame/game/showRoundsPlayed",
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
    
    $("#restartGame").click(function () {
        restartGame();
    });    
    
    function restartGame() {
        $.ajax({
            type: 'GET',
            url: "http://localhost:8080/rockPaperScissorsGame/game/restartGame",
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
                showRoundsPlayed();
            },
            error: function (e) {
                console.log("There was an error with your request...");
                console.log("error: " + JSON.stringify(e));
            }
        });
    }  
    
    $("#showAllRoundsPlayed").click(function () {
        showAllRoundsPlayed();
    });    
    
    function showAllRoundsPlayed() {
        $.ajax({
            type: 'GET',
            url: "http://localhost:8080/rockPaperScissorsGame/game/showAllRoundsPlayed",
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
                console.log("data: " + data);
                var winPlayer1Counter = data.PLAYER1_WIN;
                var winPlayer2Counter = data.PLAYER2_WIN;
                var drawCounter = data.DRAW;
                var totalCounter = winPlayer1Counter + winPlayer2Counter + drawCounter;
                $("#total-rounds-played").val(totalCounter.toString());
                $("#total-wins-player1").val(winPlayer1Counter.toString());
                $("#total-wins-player2").val(winPlayer2Counter.toString());
                $("#total-draws").val(drawCounter.toString());
            },
            error: function (e) {
                console.log("There was an error with your request...");
                console.log("error: " + JSON.stringify(e));
            }
        });
    }  
});