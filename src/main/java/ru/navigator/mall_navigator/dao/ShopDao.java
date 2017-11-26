package ru.navigator.mall_navigator.dao;

import net.sf.autodao.AutoDAO;
import net.sf.autodao.Finder;
import net.sf.autodao.Named;
import ru.navigator.mall_navigator.model.Shop;

@AutoDAO
public interface ShopDao {

    @Finder(query = "form Shop where name = :name")
    Shop findShopForName(@Named(":name") String name);

}
