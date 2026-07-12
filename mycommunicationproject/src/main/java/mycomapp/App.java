package mycomapp;

import java.io.FileInputStream;
import java.util.Collections;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

final class App
{

    static
    {

        LogManager.getLogManager().reset();
    }

    private App()
    {
    }

    static void main(final String[] p_args)
    {
        if ( p_args.length < 2 )
            throw new RuntimeException( "arguments are not set: ASL script, number of agents" );


        final Set<SimpleAgent> l_agents;
        try
                (
                        final FileInputStream l_stream = new FileInputStream( p_args[0] );
                )
        {

            l_agents = Collections.unmodifiableSet(
                    new SimpleAgentGenerator( l_stream, new Send() )
                            .generatemultiple( Integer.parseInt( p_args[1] ) )
                            .collect( Collectors.toSet() )
            );
        }
        catch ( final Exception l_exception )
        {
            l_exception.printStackTrace();
            return;
        }

        IntStream
                .range(
                        0,
                        p_args.length < 3
                                ? Integer.MAX_VALUE
                                : Integer.parseInt( p_args[2] )
                )
                .forEach( j -> l_agents.parallelStream()
                        .forEach( i ->
                        {
                            try
                            {
                                i.call();
                            }
                            catch ( final Exception l_exception )
                            {
                                l_exception.printStackTrace();
                                throw new RuntimeException();
                            }
                        } ) );
    }
}
