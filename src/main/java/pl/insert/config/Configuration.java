package pl.insert.config;

import pl.insert.annotations.Bean;
import pl.insert.services.Service;
import pl.insert.services.ServiceImpl;

public class Configuration {

    public Configuration() {
    }

    @Bean(name = "mojaNazwa")
    public Service cosTamcosTam() {

        System.out.println("pl.insert.config.Configuration: cosTamcosTam()");

        return new ServiceImpl();
    }

    @Bean(name="mojaInnaNazwa")
    public Service inneCosTamCosTam() {

        System.out.println("pl.insert.config.Configuration: inneCosTamCosTam()");

        return new ServiceImpl();
    }
}
