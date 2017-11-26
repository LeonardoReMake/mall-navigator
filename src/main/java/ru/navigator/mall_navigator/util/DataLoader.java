package ru.navigator.mall_navigator.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.navigator.mall_navigator.dao.MallDao;
import ru.navigator.mall_navigator.dao.PointDao;
import ru.navigator.mall_navigator.dao.ShopDao;
import ru.navigator.mall_navigator.model.Mall;
import ru.navigator.mall_navigator.model.Point;
import ru.navigator.mall_navigator.model.Shop;

import javax.annotation.Resource;

public class DataLoader {
    private static final Logger LOG = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private MallDao mallDao;

    @Resource
    private ShopDao shopDao;

    @Resource
    private PointDao pointDao;

    public DataLoader() {
        Mall mall = new Mall();
        mall.setName("Авиапарк");

        double[] x = {1., 2., 3., 4.};
        double[] y = {1., 2., 3., 4.};

        Point point1 = new Point(x[0], y[0]);
        Point point2 = new Point(x[1], y[0]);
        Point point3 = new Point(x[2], y[1]);
        Point point4 = new Point(x[0], y[1]);
        Point point5 = new Point(x[1], y[2]);
        Point point6 = new Point(x[3], y[3]);

        Shop shop1 = new Shop("Nike", mall, point1);
        Shop shop2 = new Shop("Adidas", mall, point2);
        Shop shop3 = new Shop("H&M", mall, point3);
        Shop shop4 = new Shop("Reserved", mall, point4);
        Shop shop5 = new Shop("New Yorker", mall, point5);
        Shop shop6 = new Shop("Reebok", mall, point6);

        pointDao.create(point1);
        pointDao.create(point2);
        pointDao.create(point3);
        pointDao.create(point4);
        pointDao.create(point5);
        pointDao.create(point6);

        shopDao.create(shop1);
        shopDao.create(shop2);
        shopDao.create(shop3);
        shopDao.create(shop4);
        shopDao.create(shop5);
        shopDao.create(shop6);

        mallDao.create(mall);
    }

}
