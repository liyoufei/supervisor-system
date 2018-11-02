
var uid = randomString(4);
var webSocket = new WebSocket("ws://localhost:8080/socketServer?uid="+uid);

webSocket.onopen = onOpen;

//生成随机四位ID
function randomString(len) {
    len = len || 32;
    var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
    var maxPos = $chars.length;
    var pwd = '';
    for (i = 0; i < len; i++) {
        pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return pwd;
}

//发送客户端数据至服务端
function sendData() {
    var cpu = Math.floor(Math.random()*(80)+20);
    var message = "client:"+uid+",data:"+cpu;

    webSocket.send(message);
}

function onOpen() {
    setInterval(sendData,5000);
}