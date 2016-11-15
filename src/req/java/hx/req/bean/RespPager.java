package hx.req.bean;

/**
 * Created by rose on 16-8-3.
 */

public class RespPager<T> {

    public Pager<T> pager;

    public Pager<T> getPager() {
        return pager;
    }

    public void setPager(Pager<T> pager) {
        this.pager = pager;
    }
}
