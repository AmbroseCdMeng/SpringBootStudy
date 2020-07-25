let stompClient = null;

function connect() {
    let socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, frame =>
        // 连接成功后，订阅的地址为 /user/queue/chat
        // 该地址比服务端配置多了 /user 前缀，这是因为 SimpMessagingTemplate 类中自动添加了路径前缀
        stompClient.subscribe('/user/queue/chat', chat => showGreeting(JSON.parse(chat.body)))
    )
}

function sendMsg() {
    // 聊天消息发送路径 /app/chat
    stompClient.send('/app/chat', {}, JSON.stringify({'content': $('#content').val(), 'to': $('#to').val()}));
    showGreeting({from: '我', content: $('#content').val()})
}

function showGreeting(message) {
    $('#chatsContent').append(`<div>${message.from}:${message.content}</div>`)
}

$(function () {
    connect();
    $('#send').click(() => sendMsg());
})
