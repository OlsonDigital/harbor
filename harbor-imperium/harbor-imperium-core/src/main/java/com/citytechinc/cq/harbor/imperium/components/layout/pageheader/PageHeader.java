package com.citytechinc.cq.harbor.imperium.components.layout.pageheader;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.harbor.imperium.components.layout.container.Container;
import com.citytechinc.cq.harbor.proper.api.constants.dom.Elements;
import com.citytechinc.cq.harbor.proper.api.constants.ontology.Roles;
import com.google.common.base.Optional;

@Component(value = "Page Header", group = "Harbor Layout", resourceSuperType = Container.RESOURCE_TYPE, path = "layout", layout = "rollover")
@AutoInstantiate(instanceName = "contentContainer")
public class PageHeader extends Container {

	@Override
	public Optional<String> getRoleOptional() {
		return Optional.fromNullable(Roles.PAGE_HEADER);
	}

	@Override
	public String getContainerElement() {
		return Elements.HEADER;
	}

	@Override
	public String getContainerName() {
		return "Header Container";
	}

    @Override
    public boolean isSection() {
        return false;
    }
}