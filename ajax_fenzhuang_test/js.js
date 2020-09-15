function get(url,data,success) {
    //新键异步对象
    var xml = new XMLHttpRequest();
    //设置请求头
    if (data){
        //之所以不一口气相加是因为两次相加的情况不一
        url += '?';
        url += data;
    }
    xml.open('get',url);
    //设置回调函数
    xml.onreadystatechange = function () {
        if (xml.status==200&&xml.readyState==4){
            console.log(xml.responseText);
            //设置回调函数
            success(xml.responseText);
        }
    }
    //发送
    xml.send(null);

}
function post(url,data,success) {
    //新键异步对象
    var xml = new XMLHttpRequest();
    //设置请求头
    xml.open('post',url);
    //有数据就设置请求行
    if (data){
        xml.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    }
    //设置回调函数
    xml.onreadystatechange = function () {
        if (xml.status==200&&xml.readyState==4){
            console.log(xml.responseText);
            success(xml.responseText);
        }
    }
    //发送
    xml.send(data);
}
function unified(url,type,data,succsee) {
    var xml = new XMLHttpRequest();
    //设置请求头
    if (type=='get'&&data){
        url += '?';
        url += data;
        //当是get方式时
        data = '';
    }

    xml.open(type,url);
    //设置回调函数
    if (type=='post'&&data){
        xml.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    }
    xml.onreadystatechange = function () {
        if (xml.status==200&&xml.readyState==4){
            console.log(xml.responseText);
            //设置回调函数
            succsee(xml.responseText);
        }
    }
    //发送
    xml.send(data);

}