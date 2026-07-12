!main.
+!main <-
    generic/print("Hello World on agent", MyName);
    !mynextgoal
.
+!mynextgoal <-
    generic/print( "Hello World again on agent", MyName );
    !mynextgoal
.
+!special-goal(X) <-
    generic/print( "special goal with value", X, "triggered on agent", MyName )
.