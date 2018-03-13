package org.leiyuxin.chapter4.ThreadLocal;

public class SequnenceA implements Sequence {
	private static int number = 0;

	@Override
	public synchronized int getNumber() {
		number = number + 1;
		return number;
	}

	public static void main(String[] args) {
		Sequence sequnence = new SequnenceA();
		ClientThread thread1 = new ClientThread(sequnence);
		ClientThread thread2 = new ClientThread(sequnence);
		ClientThread thread3 = new ClientThread(sequnence);

		thread1.start();
		thread2.start();
		thread3.start();
	}
}
