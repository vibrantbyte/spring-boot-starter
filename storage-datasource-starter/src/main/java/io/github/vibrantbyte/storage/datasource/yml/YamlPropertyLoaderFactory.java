package io.github.vibrantbyte.storage.datasource.yml;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.util.List;

/**
 * @author vibrant byte
 */
public class YamlPropertyLoaderFactory extends DefaultPropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        if (resource == null) {
            super.createPropertySource(name, resource);
        }
        List<PropertySource<?>> propertySourceList = new YamlPropertySourceLoader().load(resource.getResource().getFilename(), resource.getResource());
        for (PropertySource<?> item : propertySourceList){
            if (null != item && item.getSource() != null)
            {
                return propertySourceList.get(0);
            }
        }
        throw new IOException("data source not initial，please check your config!");
    }
}
