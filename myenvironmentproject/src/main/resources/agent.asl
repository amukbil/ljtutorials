!main.
+!main <-
    NewPosition = MyPosition + 1;
    NewPosition = NewPosition % EnvSize;
    !!move( NewPosition )
.
+!move(X) <-
    generic/print( "agent", MyName, "is on position", MyPosition, "move to ", X );
    env/move( X );
    X++;
    X = X % EnvSize;
    !move( X )
.
-!move(X) <-
    Y = MyPosition - 1;
    Y = Y < 0 ? EnvSize + Y : Y;
    generic/print( "agent", MyName, "cannot move from", MyPosition, "to ", X, "try to move to", Y );
    !move(Y)
.
+!other/agent-position/changed( from(X), to(Y) ) <-
    generic/print( "agent", MyName, "get information that other agent has moved from", X, "to", Y )
.