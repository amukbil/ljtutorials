!main.
+!main <-

    I = math/statistic/randomsimple() * 12 + 4;
    R = string/random( "abcdefghijklmnopqrstuvwxyz", I );

    N = my/custom-action( R );
    //N = 5;

    L = my/object-action( N );
    //L=5;

    generic/print( "agent uses string", R, "gets from standalone action", N, "and from object action", L )
.
