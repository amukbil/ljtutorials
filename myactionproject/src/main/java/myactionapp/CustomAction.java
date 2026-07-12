package myactionapp;

import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.lightjason.agentspeak.action.IBaseAction;
import org.lightjason.agentspeak.common.CPath;
import org.lightjason.agentspeak.common.IPath;
import org.lightjason.agentspeak.language.CRawTerm;
import org.lightjason.agentspeak.language.ITerm;
import org.lightjason.agentspeak.language.execution.IContext;
import org.lightjason.agentspeak.language.fuzzy.CFuzzyValue;
import org.lightjason.agentspeak.language.fuzzy.IFuzzyValue;

import java.io.Serial;
import java.text.MessageFormat;
import java.util.List;

public class CustomAction extends IBaseAction
{

    @Serial
    private static final long serialVersionUID = -6860156961503377199L;

    @NonNullDecl
    @Override
    public IPath name()
    {
        return CPath.from( "my/custom-action" );
    }

    @Override
    public int minimalArgumentNumber()
    {
        return 1;
    }

    @NonNullDecl
    @Override
    public IFuzzyValue<Boolean> execute(boolean p_parallel, @NonNullDecl IContext p_context, @NonNullDecl List<ITerm> p_argument, @NonNullDecl List<ITerm> p_return)
    {

        System.out.println(
                MessageFormat.format(
                        "agent {0} calls custom-action with parameter {1}",
                        p_context.agent().hashCode(),
                        p_argument.get( 0 ).<String>raw()
                )
        );

        p_return.add(
                CRawTerm.from(
                        p_argument.get( 0 ).<String>raw().length()
                )
        );

        return CFuzzyValue.from( true );
    }
}
