package potato.demo.rxjava;

/**
 * Created by Administrator on 2016/11/11 0011.
 */

//结果接收者
interface ObGeter<IN> {
    void onGet(IN t);
}

//结果发送者
interface ObSender<OUT> {

    void send(final ObGeter<? super OUT> t);

}

//转换接口
interface Transformer<IN, OUT> {
    OUT transform(IN in);
}


public class Ob<T> {

    ObSender<T> mObSender;

    public Ob(ObSender<T> obSender) {
        this.mObSender = obSender;
    }

    public static <TYPE> Ob<TYPE> create(ObSender<TYPE> obSender) {
        return new Ob<TYPE>(obSender);
    }

    public final <T2> Ob<T2> lift(final Transformer<T, T2> f1) {

        //新建Ob2和发送者sender2,一旦有接受者订阅，就先触发ob2的sender2的send
        return new Ob<T2>(new ObSender<T2>() {

            @Override
            public void send(final ObGeter<? super T2> obGet2) {
                // 触发了ob2的sender2的send。
                // 不会直接调用get2的onGet，因为也没有数据可以处理，需要向ob1要数据。
                // 所以先建立个get1，然后触发ob1的sender1向get1发送数据。
                // 在get1拿到数据后，转换数据类型后，将数据t1转为t2，然后给obGet2.onGet(t2);
                // get2就是调用者的数据接收者了。

                //请求者1，用于请求ob1。
                ObGeter<? super T> geter1 = new ObGeter<T>() {
                    @Override
                    public void onGet(T t1) {
                        //转换类型，发送给get2
                        T2 t2 = f1.transform(t1);
                        obGet2.onGet(t2);
                    }
                };

                //用请求者1请求ob1
                mObSender.send(geter1);
            }
        });


    }

    public void subscribe(ObGeter<? super T> obGet) {
        mObSender.send(obGet);
    }

    public static void main(String[] args) {
        System.out.print("start ");
        //模仿rxjava的代码
        Ob
                .create(new ObSender<Integer>() {
                    @Override
                    public void send(ObGeter<? super Integer> suber) {
                        suber.onGet(1);
                    }
                })
                .lift(new Transformer<Integer, String>() {
                    @Override
                    public String transform(Integer in) {
                        return in + 1 + "";
                    }
                })
                .subscribe(new ObGeter<String>() {
                    @Override
                    public void onGet(final String s) {
                        System.out.print(s);
                    }
                });

    }

}
