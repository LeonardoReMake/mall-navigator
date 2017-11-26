package ru.navigator.mall_navigator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.navigator.mall_navigator.dao.MallDao;
import ru.navigator.mall_navigator.dao.PointDao;
import ru.navigator.mall_navigator.dao.ShopDao;
import ru.navigator.mall_navigator.model.Mall;
import ru.navigator.mall_navigator.model.Point;
import ru.navigator.mall_navigator.model.Shop;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations={"classpath:applicationContext.xml"} )
public class DataLoder {
    private static final Logger LOG = LoggerFactory.getLogger(DataLoder.class);

    @Resource
    private MallDao mallDao;

    @Resource
    private ShopDao shopDao;

    @Resource
    private PointDao pointDao;

    @Test
    @Transactional
    @Rollback(value = false)
    public void loadTestData() {
        Mall mall = new Mall();
        mall.setName("Авиапарк");

        double[] x = {1., 2., 3., 4.};
        double[] y = {1., 2., 3., 4.};

        Point point1 = new Point(x[0], y[0], mall);
        Point point2 = new Point(x[1], y[0], mall);
        Point point3 = new Point(x[2], y[1], mall);
        Point point4 = new Point(x[0], y[1], mall);
        Point point5 = new Point(x[1], y[2], mall);
        Point point6 = new Point(x[3], y[3], mall);

        Set<Point> points = new HashSet<>();

        points.add(point1);
        points.add(point2);
        points.add(point3);
        points.add(point4);
        points.add(point5);
        points.add(point6);

        for (int i = 0; i < 10; i++) {
            points.add(new Point((new Random().nextDouble()), (new Random().nextDouble()), mall));
        }

        mall.setPoints(points);

        Shop shop1 = new Shop("Nike", mall, point1);
        Shop shop2 = new Shop("Adidas", mall, point2);
        Shop shop3 = new Shop("H&M", mall, point3);
        Shop shop4 = new Shop("Reserved", mall, point4);
        Shop shop5 = new Shop("New Yorker", mall, point5);
        Shop shop6 = new Shop("Reebok", mall, point6);

//        pointDao.create(point1);
//        pointDao.create(point2);
//        pointDao.create(point3);
//        pointDao.create(point4);
//        pointDao.create(point5);
//        pointDao.create(point6);

        shopDao.create(shop1);
        shopDao.create(shop2);
        shopDao.create(shop3);
        shopDao.create(shop4);
        shopDao.create(shop5);
        shopDao.create(shop6);

        mallDao.create(mall);

    }

}
