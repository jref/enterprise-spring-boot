(function ($) {
    var url = 'ws://' + window.location.host + '/bamboo-websocket';
    var sock = new WebSocket(url);
    var stompCliet = Stomp.over(sock);

    sock.onopen = function () {
        console.log('Opening web socket');
        sendMessage();
    };

    sock.onmessage = function (e) {
        console.log('Received message: ', e.data);
        setTimeout(function () {
            sendMessage()
        }, 2000);
    };

    sock.onclose = function () {
        console.log('Closing web socket');
    };

    function sendMessage() {
        console.log('Sending message: Hello!');
        sock.send("/bamboo-app/hello", "Hello!");
    }
})(jQuery);