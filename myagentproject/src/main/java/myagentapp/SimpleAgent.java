package myagentapp;

import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.lightjason.agentspeak.agent.IBaseAgent;
import org.lightjason.agentspeak.configuration.IAgentConfiguration;

import java.io.Serial;

public class SimpleAgent extends IBaseAgent<SimpleAgent>
{

    @Serial
    private static final long serialVersionUID = 4770877689615721055L;

    /**
     * ctor
     *
     * @param p_configuration agent configuration
     */
    public SimpleAgent(@NonNullDecl IAgentConfiguration<SimpleAgent> p_configuration) {
        super(p_configuration);
    }
}
