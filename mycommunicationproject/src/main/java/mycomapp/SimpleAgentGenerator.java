package mycomapp;

import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.lightjason.agentspeak.common.CCommon;
import org.lightjason.agentspeak.generator.IBaseAgentGenerator;

import javax.annotation.Nullable;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleAgentGenerator extends IBaseAgentGenerator<SimpleAgent>
{

    private final Send m_send;

    private final AtomicLong m_counter = new AtomicLong();

    SimpleAgentGenerator(@NonNullDecl InputStream p_stream, @NonNullDecl final Send p_send ) throws Exception
    {
        super(

                p_stream,

                Stream.concat(

                        CCommon.actionsFromPackage(),

                        Stream.of( p_send )

                ).collect( Collectors.toSet() ),

                new VariableBuilder()
        );
        m_send = p_send;
    }

    @NonNullDecl
    @Override
    public final SimpleAgent generatesingle( @Nullable final Object... p_data )
    {
        return m_send.register(
                new SimpleAgent(

                        m_configuration, MessageFormat.format( "agent {0}", m_counter.getAndIncrement() )

                )
        );
    }

    final void unregister( final SimpleAgent p_agent )
    {
        m_send.unregister( p_agent );
    }
}
