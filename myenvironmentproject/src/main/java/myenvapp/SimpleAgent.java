package myenvapp;

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
    private static final long serialVersionUID = 2576458946199593936L;

    private final transient Environment m_environment;

    /**
     * ctor
     *
     * @param p_configuration agent configuration
     */
    public SimpleAgent(@NonNullDecl IAgentConfiguration<SimpleAgent> p_configuration,
                       @Nonnull Environment p_environment)
    {
        super(p_configuration);
        m_environment = p_environment;
    }

    @IAgentActionFilter
    @IAgentActionName( name = "env/move" )
    private void envmove( @Nonnull final Number p_value )
    {
        m_environment.move( this, p_value );
    }

    final int position()
    {
        return m_environment.position( this );
    }
}
