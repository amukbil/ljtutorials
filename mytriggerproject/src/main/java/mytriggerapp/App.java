package mytriggerapp;

import org.lightjason.agentspeak.language.CLiteral;
import org.lightjason.agentspeak.language.CRawTerm;
import org.lightjason.agentspeak.language.instantiable.plan.trigger.CTrigger;
import org.lightjason.agentspeak.language.instantiable.plan.trigger.ITrigger;

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

    public static void main( final String[] p_args )
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
                    new SimpleAgentGenerator( l_stream )
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


                                if ( Math.random() < 0.5 )
                                    i.trigger(
                                            CTrigger.from(

                                                    ITrigger.EType.ADDGOAL,

                                                    CLiteral.from(
                                                            "special-goal",
                                                            CRawTerm.from( 100 * Math.random() )
                                                    )
                                            )
                                    );
                            }
                            catch ( final Exception l_exception )
                            {
                                l_exception.printStackTrace();
                            }
                        } ) );
    }
}
