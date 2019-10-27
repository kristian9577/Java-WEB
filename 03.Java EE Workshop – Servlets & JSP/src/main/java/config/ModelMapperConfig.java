package config;

import org.modelmapper.ModelMapper;


import javax.enterprise.inject.Produces;


public class ModelMapperConfig {
    @Produces
    public ModelMapper mapper() {
        return new ModelMapper();
    }
}
