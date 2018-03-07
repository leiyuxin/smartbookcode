package org.leiyuxin.web.chapter2.service;

import java.util.List;
import java.util.Map;
import org.leiyuxin.web.chapter2.helper.DatabaseHelper;
import org.leiyuxin.web.chapter2.model.Customer;

/**
 * 提供客户数据服务，在标准的MVC架构中是没有服务层的，我们将改层作为衔接控制器层与数据库层之间的桥梁，可以使用接口和实现类来表达
 * ，在简单情况下，无须使用接口，之间使用类，并非所有情况下都需要使用接口，要根据具体情况来做出选择。
 */
public class CustomerService {

    /**
     * 获取客户列表
     */
    public List<Customer> getCustomerList() {
        String sql = "SELECT * FROM customer";
        return DatabaseHelper.queryEntityList(Customer.class, sql);
    }

    /**
     * 获取客户
     */
    public Customer getCustomer(long id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        return DatabaseHelper.queryEntity(Customer.class, sql, id);
    }

    /**
     * 创建客户
     */
    public boolean createCustomer(Map<String, Object> fieldMap) {
        return DatabaseHelper.insertEntity(Customer.class, fieldMap);
    }

    /**
     * 更新客户
     */
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        return DatabaseHelper.updateEntity(Customer.class, id, fieldMap);
    }

    /**
     * 删除客户
     */
    public boolean deleteCustomer(long id) {
        return DatabaseHelper.deleteEntity(Customer.class, id);
    }
}
