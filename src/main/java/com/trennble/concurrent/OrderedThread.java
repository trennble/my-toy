package com.trennble.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClasssName OrderedThread
 * @Description TODO
 * @Author sycamore
 * @Date 2018-11-6 21:54
 * @Version 1.0
 **/
public class OrderedThread {

	private int index=0;
	public static void main(String[] args) throws InterruptedException {

		OrderedThread orderedThread = new OrderedThread();
		List<Thread> threadList=new ArrayList<>();
		for (int i=0;i<10000;i++){
			Thread t=new Thread(()->{
				for (int j=0;j<10000;j++){
					synchronized (OrderedThread.class){
						orderedThread.index++;
					}
				}
			});
			t.start();
			threadList.add(t);
		}
		threadList.forEach(item-> {
			try {
				item.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		System.out.println(orderedThread.index);
	}
}
