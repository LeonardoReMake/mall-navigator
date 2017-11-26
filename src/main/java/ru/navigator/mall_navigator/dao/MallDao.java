package ru.navigator.mall_navigator.dao;

import net.sf.autodao.AutoDAO;
import net.sf.autodao.Dao;
import net.sf.autodao.Finder;
import net.sf.autodao.Named;
import ru.navigator.mall_navigator.model.Mall;

@AutoDAO
public interface MallDao {

    @Finder(query = "from Mall where name = :name")
    Mall findMallByName(@Named("name") String name);

}
