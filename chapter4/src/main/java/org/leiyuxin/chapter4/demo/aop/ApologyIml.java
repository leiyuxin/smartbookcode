package org.leiyuxin.chapter4.demo.aop;

import org.leiyuxin.chapter4.demo.AOPBeforAfterInfoUtil;

public class ApologyIml implements  Apology {

	@Override
	public void saySorry(String name) {
		AOPBeforAfterInfoUtil.info(name);
	}

}
