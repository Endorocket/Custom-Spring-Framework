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

                        Configuration configurationInstance = (Configuration) instance;


                        if (method.getReturnType().isAssignableFrom(clazz)) {
                            R returnBean = (R) method.invoke(configurationInstance);

                            //////


                            Method[] beanMethods = returnBean.getClass().getMethods();

                            for (Method beanMethod : beanMethods) {
                                if (beanMethod.getName().contains("set")) {
                                    getFieldName(beanMethod);
                                }
                            }


                            for (Field field : fields) {

                                if (field.isAnnotationPresent(Autowired.class)) {

                                    System.out.println(field.getType());
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

    private String getFieldName(Method setterMethod) {
        String substring = setterMethod.getName().substring(3);
        String firstLetter = substring.substring(0, 1).toLowerCase();
        String fieldName = firstLetter + substring.substring(1);

        return fieldName;
    }
}
