package hx.ui.components;

/**
 * Created by rose on 16-8-12.
 */
public abstract class AWithRefresh extends ABase {

    public abstract IRefreshCallback _registerRefreshCallback();
    //acting more like boolean, if null, then there would be no refresh widget.


}
