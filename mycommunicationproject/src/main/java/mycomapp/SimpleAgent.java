package mycomapp;

import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.lightjason.agentspeak.agent.IBaseAgent;
import org.lightjason.agentspeak.configuration.IAgentConfiguration;

import javax.annotation.Nonnull;
import java.io.Serial;

public class SimpleAgent extends IBaseAgent<SimpleAgent>
{

    @Serial
    private static final long serialVersionUID = 2242858691435486592L;

    private final String m_name;

    /**
     * ctor
     *
     * @param p_configuration agent configuration
     */
    public SimpleAgent(@NonNullDecl IAgentConfiguration<SimpleAgent> p_configuration, @Nonnull final String p_name )
    {
        super(p_configuration);
        m_name = p_name;
    }

    @Nonnull
    final String name()
    {
        return m_name;
    }
}
