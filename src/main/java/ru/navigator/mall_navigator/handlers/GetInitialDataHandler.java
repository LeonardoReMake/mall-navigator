package ru.navigator.mall_navigator.handlers;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import ru.navigator.mall_navigator.dao.MallDao;
import ru.navigator.mall_navigator.dao.ShopDao;
import ru.navigator.mall_navigator.model.Mall;
import ru.navigator.mall_navigator.model.Shop;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GetInitialDataHandler implements HttpRequestHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GetInitialDataHandler.class);

    @Autowired
    private MallDao mallDao;

    @Autowired
    private ShopDao shopDao;

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("Initial data request...");

        List<String> mallNames = mallDao.findAll().stream().map(Mall::getName).collect(Collectors.toList());
        List<String> shopNames = shopDao.findAll().stream().map(Shop::getName).collect(Collectors.toList());

        String jsonResponse = new JSONObject()
                .put("code", "200")
                .put("malls", mallNames)
                .put("shops", shopNames).toString();

        LOG.info("Sending answer...");
        response.getWriter().write(jsonResponse);
    }
}
