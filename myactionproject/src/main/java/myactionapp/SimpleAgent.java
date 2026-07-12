package myactionapp;

import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.lightjason.agentspeak.action.binding.IAgentAction;
import org.lightjason.agentspeak.action.binding.IAgentActionFilter;
import org.lightjason.agentspeak.action.binding.IAgentActionName;
import org.lightjason.agentspeak.agent.IBaseAgent;
import org.lightjason.agentspeak.configuration.IAgentConfiguration;

import javax.annotation.Nonnull;
import java.io.Serial;

@IAgentAction
public class SimpleAgent extends IBaseAgent<SimpleAgent>
{

    @Serial
    private static final long serialVersionUID = -630112057837402267L;

    /**
     * ctor
     *
     * @param p_configuration agent configuration
     */
    public SimpleAgent(@NonNullDecl IAgentConfiguration<SimpleAgent> p_configuration) {
        super(p_configuration);
    }

    @Nonnull
    @IAgentActionFilter
    @IAgentActionName( name = "my/object-action" )
    private Number calculate( @Nonnull final Number p_value )
    {
        return p_value.intValue() ^ this.hashCode();
    }
}
