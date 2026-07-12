package myenvapp;

import org.lightjason.agentspeak.agent.IAgent;
import org.lightjason.agentspeak.language.execution.IVariableBuilder;
import org.lightjason.agentspeak.language.instantiable.IInstantiable;
import org.lightjason.agentspeak.language.variable.CConstant;
import org.lightjason.agentspeak.language.variable.IVariable;

import javax.annotation.Nonnull;
import java.text.MessageFormat;
import java.util.stream.Stream;

public class VariableBuilder implements IVariableBuilder
{

    private final Environment m_environment;

    VariableBuilder( final Environment p_environment )
    {
        m_environment = p_environment;
    }

    @Nonnull
    @Override
    public final Stream<IVariable<?>> apply( @Nonnull final IAgent<?> p_agent,
                                             @Nonnull final IInstantiable p_runningcontext )
    {
        return Stream.of(
                new CConstant<>( "MyPosition", p_agent.<SimpleAgent>raw().position() ),
                new CConstant<>( "MyName", MessageFormat.format( "{0}", p_agent.hashCode() ) ),
                new CConstant<>( "EnvSize", m_environment.length() )
        );
    }
}
