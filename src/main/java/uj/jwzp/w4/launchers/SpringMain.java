package uj.jwzp.w4.launchers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import uj.jwzp.w4.logic.CSVMovieFinder;
import uj.jwzp.w4.logic.MovieLister;
import uj.jwzp.w4.model.Movie;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

//@RequiredArgsConstructor
//class DynamicBeanExample {
//    private final String beanId;
//
//    @Override
//    public String toString() {
//        return "DynamicBeanExample{" +
//                "beanId='" + beanId + '\'' +
//                '}';
//    }
//}
//
//@Component
//class SingleDynamicBeanProcessor implements BeanFactoryPostProcessor {
//    String fileName = "movies.txt";
//
//    public void setFileName(String fileName) {
//        this.fileName = fileName;
//        System.out.println(fileName + " setter");
//    }
//
//    public String getFileName() {
//        return fileName;
//    }
//
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        System.out.println(fileName+" tutaj post");
//        final BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) beanFactory;
//        final BeanDefinition dynamicBean = BeanDefinitionBuilder
//                .rootBeanDefinition(DynamicBeanExample.class)
//                .setScope(SCOPE_PROTOTYPE)
//                .addConstructorArgValue(fileName)
//                .getBeanDefinition();
//
//        beanDefinitionRegistry.registerBeanDefinition("dynamicBean", dynamicBean);
//    }
//}
@Slf4j
public class SpringMain {

//    public static void main(String[] args) {//dynamiczne tworzenie beana
//        //w zadaniu 4.2 na 3 sposoby wybór zapisu albo profile albo conditionale? albo dynamicznie
//
//        ApplicationContext ctx = new AnnotationConfigApplicationContext("uj.jwzp.w4");
//        SingleDynamicBeanProcessor singleDynamicBeanProcessor = (SingleDynamicBeanProcessor) ctx.getBean("singleDynamicBeanProcessor");
//        System.out.println(args[0]);
//        singleDynamicBeanProcessor.setFileName(args[0]);
//        System.out.println(singleDynamicBeanProcessor.getFileName());
////        singleDynamicBeanProcessor.postProcessBeanFactory(new DefaultListableBeanFactory());
//        DynamicBeanExample dynamicBeanExample = (DynamicBeanExample) ctx.getBean("dynamicBean");
//
//        System.out.println(dynamicBeanExample);
//    }

    public static void main(String[] args) {//dynamiczne tworzenie beana
        //w zadaniu 4.2 na 3 sposoby wybór zapisu albo profile albo conditionale? albo dynamicznie

        ApplicationContext ctx = new AnnotationConfigApplicationContext("uj.jwzp.w4.logic");//uj.jwzp.w4.logic

        CSVMovieFinder movieFinder = (CSVMovieFinder) ctx.getBean("csvMovieFinder",  args[0]);
        MovieLister lister = (MovieLister) ctx.getBean("movieLister", movieFinder);

        lister.moviesDirectedBy("Hoffman").stream()
                .map(Movie::toString)
                .forEach(log::info);

    }
}
