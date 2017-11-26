package ru.navigator.mall_navigator.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestHandler;
import ru.navigator.mall_navigator.dao.MallDao;
import ru.navigator.mall_navigator.dao.PointDao;
import ru.navigator.mall_navigator.dao.ShopDao;
import ru.navigator.mall_navigator.model.Mall;
import ru.navigator.mall_navigator.model.Point;
import ru.navigator.mall_navigator.model.Shop;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class GetPathHandler implements HttpRequestHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GetPathHandler.class);

    @Resource
    private MallDao mallDao;

    @Resource
    private ShopDao shopDao;

    @Resource
    private PointDao pointDao;

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("New request...");
        Map<String, String[]> attributes = request.getParameterMap();

        if (attributes == null || attributes.isEmpty()) {
            LOG.error("Attributes map is empty");
            return;
        }

        if (isParametersMissing(attributes)) {
            return;
        }

        Long mallId = Long.parseLong(attributes.get("mallId")[0]);
        Long shopFromId = Long.parseLong(attributes.get("shopFromId")[0]);
        Long shopToId = Long.parseLong(attributes.get("shopToId")[0]);

        LOG.info("Mall id = {}, shopFrom id = {}, shopTo id = {}", new Long[]{mallId, shopFromId, shopToId});

        Mall currentMall = mallDao.get(mallId);
        Shop shopFrom = shopDao.get(shopFromId);
        Shop shopTo = shopDao.get(shopToId);

        LOG.info("Mall name = {}, shop to name = {}, shop from name = {}",
                new String[]{currentMall.getName(), shopFrom.getName(), shopTo.getName()});

        List<Long> pathIds = getPath(currentMall)
                .stream()
                .map(Point::getId)
                .collect(Collectors.toList());

        response.getWriter().write(getStringPath(pathIds));
    }

    private String getStringPath(List<Long> pathIds) {
        return pathIds.toString();
    }

    private boolean isParametersMissing(Map<String, String[]> attributes) {
        if (!attributes.containsKey("mallId")) {
            LOG.error("Missing mallId parameter");
            return true;
        }

        if (!attributes.containsKey("shopFromId")) {
            LOG.error("Missing shopFromId parameter");
            return true;
        }

        if (!attributes.containsKey("shopToId")) {
            LOG.error("Missing shopToId parameter");
            return true;
        }

        return false;
    }

    private List<Point> getPath(Mall mall) {
        List<Point> path = new ArrayList<>();
        List<Point> allPoints = pointDao.findPointsByMall(mall);
        for (int i = 0; i < new Random().nextInt(allPoints.size() - 1); i++) {
            path.add(allPoints.get(new Random().nextInt(allPoints.size() - 1)));
        }

        return path;
    }
}
