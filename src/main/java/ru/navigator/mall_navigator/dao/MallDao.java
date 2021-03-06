package ru.navigator.mall_navigator.dao;

import net.sf.autodao.AutoDAO;
import net.sf.autodao.Dao;
import net.sf.autodao.Finder;
import net.sf.autodao.Named;
import ru.navigator.mall_navigator.model.Mall;

import java.util.List;

@AutoDAO
public interface MallDao extends Dao<Mall, Long> {

    @Finder(query = "from Mall where name = :name")
    Mall findMallByName(@Named("name") String name);

    @Finder(query = "from Mall")
    List<Mall> findAll();

}
