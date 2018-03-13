package org.leiyuxin.chapter4.ThreadLocal;

import org.leiyuxin.chapter4.demo.AOPBeforAfterInfoUtil;

public class ClientThread extends Thread {
	private Sequence sequence;
	public ClientThread(Sequence sequence) {
		this.sequence = sequence;
	}
	@Override
	public void run() {
		for(int i=0 ; i <3; i++) {
			AOPBeforAfterInfoUtil.info(Thread.currentThread().getName()+"=> "+ sequence.getNumber());
		}
	}
}
