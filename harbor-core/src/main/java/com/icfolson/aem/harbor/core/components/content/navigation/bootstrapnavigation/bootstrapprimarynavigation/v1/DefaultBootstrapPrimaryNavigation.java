package com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.bootstrapprimarynavigation.v1;

import com.icfolson.aem.harbor.api.components.content.tree.TreeComponent;
import com.icfolson.aem.harbor.core.components.content.navigation.primarynavigation.v1.DefaultPrimaryNavigation;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import java.util.Optional;

@Model(adaptables = Resource.class, adapters = {TreeComponent.class}, resourceType = DefaultBootstrapPrimaryNavigation.RESOURCE_TYPE)
public class DefaultBootstrapPrimaryNavigation extends DefaultPrimaryNavigation {

    public static final String RESOURCE_TYPE = "harbor/components/content/navigation/bootstrapnavigation/bootstrapprimarynavigation/v1/bootstrapprimarynavigation";

    @Override
    public Optional<Integer> getDepth() {
        return Optional.of(2);
    }

}
