package com.trennble.closure;

/**
 * @ClasssName ClosureTest
 * @Description
 *  java闭包测试，主要是测试对匿名内部类访问外部变量的限制
 *  这里外部变量只能是final，是为了防止变量的地址发生改变而引起不可预测的错误
 *  这里引用的也是外部变量的地址，而不是值，所以外部可内部对变量引用指向的其他值的改变都是同步的
 *
 *  输出结果：
 *          10
 *          in
 *          10
 *          9
 *          out
 *          9
 *          8
 *          in
 *          8
 *          7
 *          out
 * @Author sycamore
 * @Date 2019-9-17 17:01
 * @Version 1.0
 **/
public class ClosureTest {

    private static class Holder{
        Integer num;
    }

    private interface Op{
        void op();
        void setHolder(Holder holder);
    }

    public static void main(String[] args) {
        Holder holder = new Holder();
        holder.num=10;
        Op op = new Op() {

            Holder holder;

            @Override
            public void op() {
                System.out.println("in");
                System.out.println(holder.num);
                holder.num--;
                System.out.println(holder.num);
                System.out.println("out");
            }

            public void setHolder(Holder holder){
                this.holder=holder;
            }
        };

        System.out.println(holder.num);
        op.setHolder(holder);
        op.op();
        System.out.println(holder.num);
        holder.num--;
        System.out.println(holder.num);
        op.op();
    }
}
