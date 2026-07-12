package myagentapp;

import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.lightjason.agentspeak.common.CCommon;
import org.lightjason.agentspeak.generator.IBaseAgentGenerator;

import java.io.InputStream;
import java.util.stream.Collectors;

public class SimpleAgentGenerator extends IBaseAgentGenerator<SimpleAgent>
{

    SimpleAgentGenerator(@NonNullDecl InputStream p_stream ) throws Exception {
        super(p_stream, CCommon.actionsFromPackage().collect( Collectors.toSet() ));
    }

    @NullableDecl
    @Override
    public SimpleAgent generatesingle(@NullableDecl Object... p_data) {
        return new SimpleAgent(m_configuration);
    }
}
