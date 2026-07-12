package myenvapp;

import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.lightjason.agentspeak.common.CCommon;
import org.lightjason.agentspeak.generator.IBaseAgentGenerator;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleAgentGenerator extends IBaseAgentGenerator<SimpleAgent>
{

    private final Environment m_environment;

    SimpleAgentGenerator(@NonNullDecl InputStream p_stream,
                         @Nonnull Environment p_environment) throws Exception
    {
        super(

                p_stream,
                Stream.concat(

                        CCommon.actionsFromPackage(),

                        CCommon.actionsFromAgentClass( SimpleAgent.class )

                ).collect( Collectors.toSet() ),

                new VariableBuilder(p_environment)
        );

        m_environment = p_environment;
    }

    @NullableDecl
    @Override
    public SimpleAgent generatesingle(@NullableDecl Object... p_data)
    {
        final SimpleAgent l_agent = new SimpleAgent(m_configuration, m_environment);
        int l_position = (int) ( Math.random() * m_environment.size() );
        while ( !m_environment.initialset( l_agent, l_position ) )
            l_position = (int) ( Math.random() * m_environment.size() );
        return l_agent;
    }
}
