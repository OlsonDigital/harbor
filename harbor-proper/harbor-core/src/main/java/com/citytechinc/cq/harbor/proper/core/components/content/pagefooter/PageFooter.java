package com.citytechinc.cq.harbor.proper.core.components.content.pagefooter;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.harbor.proper.api.constants.dom.Elements;
import com.citytechinc.cq.harbor.proper.api.constants.ontology.Roles;
import com.citytechinc.cq.harbor.proper.core.components.content.container.Container;
import com.citytechinc.cq.harbor.proper.core.constants.groups.ComponentGroups;
import com.google.common.base.Optional;

@Component(value = "Page Footer", group = ComponentGroups.HARBOR_SCAFFOLDING, resourceSuperType = Container.RESOURCE_TYPE)
public class PageFooter extends Container {

	@Override
	public Optional<String> getRoleOptional() {
		return Optional.fromNullable(Roles.PAGE_FOOTER);
	}

	@Override
	public String getContainerElement() {
		return Elements.FOOTER;
	}

    @Override
    public boolean isSection() {
        return false;
    }

}
