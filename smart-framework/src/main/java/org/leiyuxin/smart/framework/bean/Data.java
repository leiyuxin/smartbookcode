package org.leiyuxin.smart.framework.bean;

/**
 * 返回数据对象
 *
 * @author leiyuxin
 * @since 1.0.0
 */
public class Data {

    /**
     * 模型数据
     */
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
