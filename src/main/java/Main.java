import pl.insert.config.ApplicationContext;
import pl.insert.config.Configuration;
import pl.insert.services.Service;
import pl.insert.services.ServiceImpl;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ApplicationContext(Configuration.class);

        ServiceImpl service = context.getBean("mojaInnaNazwa", ServiceImpl.class);

        System.out.println(service.getUserDao());

    }
}
