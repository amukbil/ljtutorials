package mycomapp;

import org.lightjason.agentspeak.action.IBaseAction;
import org.lightjason.agentspeak.agent.IAgent;
import org.lightjason.agentspeak.common.CPath;
import org.lightjason.agentspeak.common.IPath;
import org.lightjason.agentspeak.language.CLiteral;
import org.lightjason.agentspeak.language.CRawTerm;
import org.lightjason.agentspeak.language.ITerm;
import org.lightjason.agentspeak.language.execution.IContext;
import org.lightjason.agentspeak.language.fuzzy.CFuzzyValue;
import org.lightjason.agentspeak.language.fuzzy.IFuzzyValue;
import org.lightjason.agentspeak.language.instantiable.plan.trigger.CTrigger;
import org.lightjason.agentspeak.language.instantiable.plan.trigger.ITrigger;

import javax.annotation.Nonnull;
import java.io.Serial;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

final class Send extends IBaseAction
{

    @Serial
    private static final long serialVersionUID = 345248543347975973L;

    private final transient Map<String, SimpleAgent> m_agents = new ConcurrentHashMap<>();

    @Nonnull
    @Override
    public final IPath name()
    {
        return CPath.from( "message/send" );
    }

    @Override
    public final int minimalArgumentNumber()
    {
        return 2;
    }

    @Override
    public final IFuzzyValue<Boolean> execute(final boolean p_parallel, @Nonnull final IContext p_context,
                                              @Nonnull final List<ITerm> p_argument, @Nonnull final List<ITerm> p_return )
    {

        final IAgent<?> l_receiver = m_agents.get( p_argument.get( 0 ).<String>raw() );

        if ( l_receiver == null )
            return CFuzzyValue.from( false );

        l_receiver.trigger(
                CTrigger.from(
                        ITrigger.EType.ADDGOAL,


                        CLiteral.from(
                                "message/receive",

                                CLiteral.from(
                                        "message",


                                        p_argument
                                                .subList( 1, p_argument.size() )
                                                .stream()
                                                .map( i -> CRawTerm.from( i.raw() ) )
                                ),



                                CLiteral.from(
                                        "from",
                                        CRawTerm.from(
                                                p_context.agent().<SimpleAgent>raw().name()
                                        )
                                )
                        )
                )
        );
        return CFuzzyValue.from( true );
    }

    @Nonnull
    final SimpleAgent register( @Nonnull final SimpleAgent p_agent )
    {
        m_agents.put( p_agent.name(), p_agent );
        return p_agent;
    }

    @Nonnull
    final SimpleAgent unregister( @Nonnull final SimpleAgent p_agent )
    {
        m_agents.remove( p_agent.name() );
        return p_agent;
    }
}