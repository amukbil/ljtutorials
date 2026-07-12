package mycomapp;

import org.lightjason.agentspeak.agent.IAgent;
import org.lightjason.agentspeak.language.execution.IVariableBuilder;
import org.lightjason.agentspeak.language.instantiable.IInstantiable;
import org.lightjason.agentspeak.language.variable.CConstant;
import org.lightjason.agentspeak.language.variable.IVariable;

import javax.annotation.Nonnull;
import java.util.stream.Stream;

public class VariableBuilder implements IVariableBuilder
{
    @Nonnull
    @Override
    public final Stream<IVariable<?>> apply(@Nonnull final IAgent<?> p_agent,
                                            @Nonnull final IInstantiable p_runningcontext )
    {
        return Stream.of(
                new CConstant<>( "MyName", p_agent.<SimpleAgent>raw().name() )
        );
    }
}