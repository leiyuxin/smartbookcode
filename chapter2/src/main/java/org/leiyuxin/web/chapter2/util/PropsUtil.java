package org.leiyuxin.web.chapter2.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 属性文件工具类
 */
public final class PropsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 加载属性文件
     * 最关键的是loadProps方法，我们只需要传递一个属性文件的名称，即可返回一个Properties对象
     * 然后在根据getString，getInt,getBoolean 这些方法由key 获取指定类型的value，同时也可指定defaultValue作为默认值
     * 在PropsUtil中使用CastUtil 类，该类是为了处理一些数据转型而做准备
     */
    public static Properties loadProps(String fileName) {
        Properties props = null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (is == null) {
                throw new FileNotFoundException(fileName + " file is not found");
            }
            props = new Properties();
            props.load(is);
        } catch (IOException e) {
            LOGGER.error("load properties file failure", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    LOGGER.error("close input stream failure", e);
                }
            }
        }
        return props;
    }

    /**
     * 获取字符型属性（默认值为空字符串）
     */
    public static String getString(Properties props, String key) {
        return getString(props, key, "");
    }

    /**
     * 获取字符型属性（可指定默认值）
     */
    public static String getString(Properties props, String key, String defaultValue) {
        String value = defaultValue;
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }

    /**
     * 获取数值型属性（默认值为 0）
     */
    public static int getInt(Properties props, String key) {
        return getInt(props, key, 0);
    }

    // 获取数值型属性（可指定默认值）
    public static int getInt(Properties props, String key, int defaultValue) {
        int value = defaultValue;
        if (props.containsKey(key)) {
            value = CastUtil.castInt(props.getProperty(key));
        }
        return value;
    }

    /**
     * 获取布尔型属性（默认值为 false）
     */
    public static boolean getBoolean(Properties props, String key) {
        return getBoolean(props, key, false);
    }

    /**
     * 获取布尔型属性（可指定默认值）
     */
    public static boolean getBoolean(Properties props, String key, boolean defaultValue) {
        boolean value = defaultValue;
        if (props.containsKey(key)) {
            value = CastUtil.castBoolean(props.getProperty(key));
        }
        return value;
    }
}
