package com.xzp.dao;

import com.xzp.entity.Orders;

public interface XOrdersDAO {
    public int doCreate(Orders order);
    public boolean doDelete(int id);
    public Orders doSelect(int id);
}
