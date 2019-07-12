package pl.insert.spring.context;

import pl.insert.spring.annotations.*;
import pl.insert.spring.dynamicproxypattern.BeanFactory;
import pl.insert.spring.exceptions.NoSuchBeanDefinitionException;

import javax.persistence.EntityManager;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

public class ApplicationContext {

    private Logger logger = Logger.getLogger(ApplicationContext.class.getName());

    private Class<?> configurationClazz;

    public ApplicationContext(Class<?> configurationClazz) {
        this.configurationClazz = configurationClazz;
    }

    public <R> R getBean(String name, Class<R> clazz) {

        logger.info("getBean: invoked, name: " + name);

        Method[] methods = configurationClazz.getMethods();

        for (Method method : methods) {

            if (method.isAnnotationPresent(Bean.class)) {
                Bean beanAnnotation = method.getAnnotation(Bean.class);

                if (beanAnnotation.name().equals(name)) {

                    try {
                        Constructor<?> constructor = configurationClazz.getConstructor();
                        Object configuration = constructor.newInstance();

                        if (method.getReturnType().isAssignableFrom(clazz)) {

                            return createInstance(method, clazz, configuration);
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        throw new NoSuchBeanDefinitionException("There is no bean called: " + name);
    }

    private <R> R createInstance(Method creationMethod, Class<R> clazz, Object configuration) throws IllegalAccessException, InvocationTargetException {

        logger.info("createInstance: invoked");

        R returnObject = (R) creationMethod.invoke(configuration);

        injectEntityManagerProxyIfNeeded(returnObject);

        injectFields(returnObject);

        boolean transactionalImpl = checkTransactional(returnObject.getClass());

        if (transactionalImpl) {

            logger.info("returning proxy");

            return BeanFactory.newInstance(returnObject, clazz);
        }

        logger.info("returning NOT proxy");

        return returnObject;
    }

    private boolean checkTransactional(Class<?> clazz) {

        logger.info("checkTransactional: invoked");

        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Transactional.class)) {
                return true;
            }
        }
        return false;
    }

    private void injectFields(Object sourceObject) throws IllegalAccessException {

        logger.info("injectFields: invoked");

        Field[] declaredFields = sourceObject.getClass().getDeclaredFields();

        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);

                if (field.isAnnotationPresent(Qualifier.class)) {
                    Qualifier qualifier = field.getAnnotation(Qualifier.class);
                    Object beanToInject = getBean(qualifier.name(), field.getType());
                    field.set(sourceObject, beanToInject);
                }
            }
        }
    }

    private void injectEntityManagerProxyIfNeeded(Object sourceObject) throws IllegalAccessException {

        logger.info("injectEntityManagerIfNeeded: invoked");

        Field[] declaredFields = sourceObject.getClass().getDeclaredFields();

        for (Field field : declaredFields) {

            if (field.isAnnotationPresent(PersistenceContext.class)
                    && field.getType().isAssignableFrom(EntityManager.class)) {

                String beanName;

                if (field.isAnnotationPresent(Qualifier.class)) {
                    beanName = field.getAnnotation(Qualifier.class).name();
                } else {
                    beanName = "entityManager";
                }

                field.setAccessible(true);

                EntityManager entityManager = getBean(beanName, EntityManager.class);
                field.set(sourceObject, entityManager);

                logger.info("ApplicationContext: injecting entity manager on: " + sourceObject.toString());
            }
        }
    }
}
