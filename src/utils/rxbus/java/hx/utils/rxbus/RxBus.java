package hx.utils.rxbus;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by rose on 16-8-23.
 */

public class RxBus {

    private static RxBus _THIS = null;
    /**
     * PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
     */

    private Subject<Object, Object> rxbus = new SerializedSubject<>(PublishSubject.create());

    public static synchronized RxBus getInstance() {
        if (_THIS == null) _THIS = new RxBus();
        return _THIS;
    }

    public void post(Object o) {
        rxbus.onNext(o);
    }
    @Deprecated
    public Observable<Object> toObserverable() {
        return rxbus;
    }

    public <T> Observable<T> toObservable(final Class<T> eventType) {
        return rxbus.filter(o -> eventType.isInstance(o)).cast(eventType);
    }

    public <T> void post(Class<T> clz, String tag){
        rxbus.onNext(RxBusObj.newInstance(clz, tag));
    }
    public <T> Observable<T> toObservable(final Class<T> eventType, final String tag) {
        return rxbus
                .observeOn(AndroidSchedulers.mainThread())
                .filter(o -> {
                    if (!(o instanceof RxBusObj)) return false;
                    RxBusObj ro = (RxBusObj) o;
                    return eventType.isInstance(ro.getObj()) && tag != null && tag.equals(ro.getTag());
                }).map(o -> {
                    RxBusObj ro = (RxBusObj) o;
                    return (T) ro.getObj();
                });
    }

    /**
     * 判断是否有订阅者
     */
    public boolean hasObservers() {
        return rxbus.hasObservers();
    }


    public static class RxBusObj {

        private String tag;
        private Object obj;

        public RxBusObj(Object obj, String tag) {
            this.tag = tag;
            this.obj = obj;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public Object getObj() {
            return obj;
        }

        public void setObj(Object obj) {
            this.obj = obj;
        }

        public static RxBusObj newInstance(Object obj, String tag) {
            return new RxBusObj(obj, tag);
        }
    }
}
