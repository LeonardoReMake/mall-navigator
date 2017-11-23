package ru.navigator.mall-navigator.web;

import org.apache.wicket.Page;
import org.apache.wicket.bean.validation.BeanValidationConfiguration;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.file.IResourceFinder;
import org.apache.wicket.util.file.Path;
import org.apache.wicket.util.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Collections;
import java.util.List;

public class WicketApplication extends WebApplication implements ApplicationContextAware {
    private static final Logger LOG = LoggerFactory.getLogger(WicketApplication.class);

    private ApplicationContext applicationContext;

    @Value("${resourceLocation}")
    private String resourceLocation;

    @Value("${reloadResources}")
    private boolean reloadResources;

    @Override
    public Class<? extends Page> getHomePage() {
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        LOG.info("set application context");
    }

    @Override
    public void init() {
        super.init();

        getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext,true));

        getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
        getMarkupSettings().setDefaultMarkupEncoding("UTF-8");

        if (reloadResources) {
            Path path = new Path(resourceLocation);
            List<IResourceFinder> finders = getResourceSettings().getResourceFinders();
            finders.add(path);
            Collections.swap(finders,0,finders.size()-1 );  // load from source tree first

            getResourceSettings().setResourcePollFrequency(Duration.ONE_SECOND);
            getResourceSettings().setDefaultCacheDuration(Duration.ONE_SECOND);
        }

        new BeanValidationConfiguration().configure(this);

    }

}
