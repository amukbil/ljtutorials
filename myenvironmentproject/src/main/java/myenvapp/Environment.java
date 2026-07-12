package myenvapp;

import org.lightjason.agentspeak.language.CLiteral;
import org.lightjason.agentspeak.language.CRawTerm;
import org.lightjason.agentspeak.language.instantiable.plan.trigger.CTrigger;
import org.lightjason.agentspeak.language.instantiable.plan.trigger.ITrigger;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReferenceArray;

final class Environment
{

    private final AtomicReferenceArray<SimpleAgent> m_position;

    private final Map<SimpleAgent, Integer> m_agentposition = new ConcurrentHashMap<>();

    private final int m_size;

    Environment( final int p_size )
    {
        m_size = p_size;
        m_position = new AtomicReferenceArray<>( new SimpleAgent[(int) ( m_size * 1.5 )] );
    }

    int length()
    {
        return m_size;
    }

    int size()
    {
        return m_position.length();
    }

    boolean initialset(@Nonnull final SimpleAgent p_agent, final int p_position)
    {

        if ( m_agentposition.size() >= m_size )
            return true;

        if ( ( p_position < 0 ) || ( p_position >= m_position.length() ) )
            return false;
        if ( m_position.compareAndSet( p_position, null, p_agent ) )
        {
            m_agentposition.put( p_agent, p_position );
            return true;
        }
        return false;
    }

    void move(@Nonnull final SimpleAgent p_agent, @Nonnull final Number p_value)
    {


        if ( ( p_value.intValue() < 0 ) || ( p_value.intValue() >= m_position.length() ) )
            throw new RuntimeException( "position index is incorrect" );

        if ( m_position.compareAndSet( p_value.intValue(), null, p_agent ) )
        {
            final int l_oldposition = m_agentposition.get( p_agent );

            m_position.set( l_oldposition, null );
            m_agentposition.put( p_agent, p_value.intValue() );

            final ITrigger l_trigger = CTrigger.from(
                    ITrigger.EType.ADDGOAL,
                    CLiteral.from(
                            "other/agent-position/changed",
                            CLiteral.from( "from", CRawTerm.from( l_oldposition ) ),
                            CLiteral.from( "to", CRawTerm.from( p_value.intValue() ) )
                    )
            );

            m_agentposition
                    .keySet()
                    .parallelStream()

                    .filter( i -> !i.equals( p_agent ) )
                    .forEach( i -> i.trigger( l_trigger ) );
        }
        else
            throw new RuntimeException( "position is not free" );
    }

    int position(@Nonnull final SimpleAgent p_agent)
    {
        return m_agentposition.get( p_agent );
    }
}
