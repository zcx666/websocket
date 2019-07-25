<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://cdn.bootcss.com/sockjs-client/1.1.4/sockjs.min.js"></script>
<script src="https://cdn.bootcss.com/stomp.js/2.3.3/stomp.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<body>

<script>
     $(function(){
       connect();
   });
   
   function connect(){
      var sock=new SockJS("http://localhost:8080/websocket");
      var headers={username:'admin',password:'admin'};
      var str=JSON.stringify({'message':'HELLO POLLY'});
      
      var stompClient=Stomp.over(sock);
      stompClient.connect(headers,function(frame){
          
          stompClient.subscribe('/user/queue/revice',function(message){
               var context=message.body;
               var odj=JSON.parse(context);
               console.log(odj.message);
             
          });
      });
   }
</script>
</body>
</html>