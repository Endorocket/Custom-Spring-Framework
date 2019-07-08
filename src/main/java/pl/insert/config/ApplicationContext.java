package pl.insert.config;

import pl.insert.annotations.Autowired;
import pl.insert.annotations.Bean;

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
                        Object instance = constructor.newInstance();

                        if (method.getReturnType().isAssignableFrom(clazz)) {
                            R returnBean = (R) method.invoke(instance);

                            //////

                            Field[] declaredFields = returnBean.getClass().getDeclaredFields();

                            for (Field field : declaredFields) {
                                if (field.isAnnotationPresent(Autowired.class)) {
                                    field.setAccessible(true);

                                    Class<?> fieldType = field.getType();
                                    Constructor<?> fieldTypeConstructor = fieldType.getConstructor();
                                    Object o = fieldTypeConstructor.newInstance();

                                    field.set(returnBean, o);


                                    System.out.println(field.getName());
                                    System.out.println(field.getDeclaringClass());
                                }
                            }


                            //////

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

        return null;
    }
}
