package org.leiyuxin.chapter4.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AOPBeforAfterInfoUtil {
	private AOPBeforAfterInfoUtil() {
		throw new IllegalStateException("Utility class");
	}

	private static final Logger logger = LoggerFactory.getLogger(AOPBeforAfterInfoUtil.class);

	public static void before(String arg) {
		logger.info("Before{}", arg);
	}

	public static void after(String arg) {
		logger.info("After{}", arg);
	}

	public static void info(String msg) {
		logger.info("info message{}",msg);

	}
	public static void info(String msg ,Exception e) {
		logger.info(msg, e);
	}
}
