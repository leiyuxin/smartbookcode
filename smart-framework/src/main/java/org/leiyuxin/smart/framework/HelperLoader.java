package org.leiyuxin.smart.framework;

import org.leiyuxin.smart.framework.helper.AopHelper;
import org.leiyuxin.smart.framework.helper.BeanHelper;
import org.leiyuxin.smart.framework.helper.ClassHelper;
import org.leiyuxin.smart.framework.helper.ControllerHelper;
import org.leiyuxin.smart.framework.helper.IocHelper;
import org.leiyuxin.smart.framework.util.ClassUtil;

/**
 * 加载相应的 Helper 类
 *
 * @author leiyuxin
 * @since 1.0.0
 */
public final class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
            ClassHelper.class,
            BeanHelper.class,
        //    AopHelper.class,
            IocHelper.class,
            ControllerHelper.class
        };
       /* for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }*/
    }
}