package org.leiyuxin.smart.framework.helper;

import java.lang.reflect.Field;
import java.util.Map;
import org.leiyuxin.smart.framework.annotation.Inject;
import org.leiyuxin.smart.framework.util.ArrayUtil;
import org.leiyuxin.smart.framework.util.CollectionUtil;
import org.leiyuxin.smart.framework.util.ReflectionUtil;

/**
 * 依赖注入助手类
 *
 * @author leiyuxin
 * @since 1.0.0
 */
public final class IocHelper {

    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)) {
                    for (Field beanField : beanFields) {
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null) {
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
