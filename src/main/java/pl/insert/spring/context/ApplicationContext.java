package pl.insert.spring.context;

import pl.insert.spring.annotations.Autowired;
import pl.insert.spring.annotations.Bean;
import pl.insert.spring.annotations.Qualifier;
import pl.insert.spring.annotations.Transactional;
import pl.insert.spring.dynamic_proxy_pattern.BeanFactory;
import pl.insert.spring.exceptions.NoSuchBeanDefinitionException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ApplicationContext {

    private Class<?> configurationClazz;

    public ApplicationContext(Class<?> configurationClazz) {
        this.configurationClazz = configurationClazz;
    }

    public <R> R getBean(String name, Class<R> clazz) {

        Method[] methods = configurationClazz.getMethods();

        for (Method method : methods) {

            if (method.isAnnotationPresent(Bean.class)) {
                Bean beanAnnotation = method.getAnnotation(Bean.class);

                if (beanAnnotation.name().equals(name)) {

                    try {
                        Constructor<?> constructor = configurationClazz.getConstructor();
                        Object configuration = constructor.newInstance();

                        if (method.getReturnType().isAssignableFrom(clazz)) {

                            R returnBean = createInstance(method, clazz, configuration);

                            injectFields(returnBean);

                            return returnBean;
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

        R returnObjectWithoutProxy = (R) creationMethod.invoke(configuration);

        boolean transactionalImpl = checkTransactional(returnObjectWithoutProxy.getClass());

        if (transactionalImpl) {

            return BeanFactory.newInstance(returnObjectWithoutProxy, clazz);
        }

        return returnObjectWithoutProxy;
    }

    private boolean checkTransactional(Class<?> clazz) {

        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Transactional.class)) {
                return true;
            }
        }

        return false;
    }

    private <R> void injectFields(R sourceObject) throws IllegalAccessException {

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
}
