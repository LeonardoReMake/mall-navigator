package ru.navigator.mall_navigator.dao;

import net.sf.autodao.AutoDAO;
import net.sf.autodao.Dao;
import net.sf.autodao.Finder;
import net.sf.autodao.Named;
import ru.navigator.mall_navigator.model.Mall;
import ru.navigator.mall_navigator.model.Point;

import java.util.List;

@AutoDAO
public interface PointDao extends Dao<Point, Long> {

    @Finder(query = "from Point where mall = :mall")
    List<Point> findPointsByMall(@Named("mall") Mall mall);

}
