!main.
+!main <-

    R = string/random( "abcdefghijklmnopqrstuvwxyz", 12 );


     message/send("agent 0", R)
.
+!message/receive(  message( Message ), from( AgentName )  ) <-

    generic/print( MyName, " received message [", Message, "] from [", AgentName, "]")
.