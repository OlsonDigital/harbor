package com.icfolson.aem.harbor.core.components.content.tabs;

import java.util.ArrayList;
import java.util.List;

import com.icfolson.aem.library.api.page.PageDecorator;
import org.apache.sling.api.resource.Resource;

import com.icfolson.aem.library.api.components.annotations.AutoInstantiate;
import com.icfolson.aem.library.api.node.ComponentNode;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.harbor.api.constants.bootstrap.Bootstrap;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.harbor.core.util.ComponentUtils;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.day.cq.wcm.api.Page;
import com.google.common.base.Predicate;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Component(
    value = "Tabs",
    group = ComponentGroups.HARBOR_SCAFFOLDING,
    actions = { "text: Tabs", "-", "edit", "-", "copymove", "delete", "-", "insert" },
    actionConfigs = {
        @ActionConfig(xtype = "tbseparator"),
        @ActionConfig(text = "Add Tab", handler = "function(){Harbor.Components.Tabs.addTab(this)}")
    })
@AutoInstantiate(instanceName = "tabs")
@Model(adaptables = Resource.class)
public class Tabs extends AbstractComponent {
    private List<Tab> tabs;

    @Inject
    private PageDecorator currentPage;

    public static String constructUniqueId(Page page, Resource r) {
        return ComponentUtils.getUniqueIdentifierForResourceInPage(page, r);
    }

    public String getName() {
        return this.getResource().getName();
    }

    @DialogField(fieldLabel = "Tab Type")
    @Selection(options = {
            @Option(text = "Tabs", value = "tabs"),
            @Option(text = "Pills", value = "pills")
    })
    public String getTabType() {
        return get("tabType", Bootstrap.TAB_NAVIGATION_TYPE);
    }

    public List<Tab> getTabs() {
        if (tabs == null) {
            tabs = new ArrayList<Tab>();

            for (ComponentNode currentComponentNode : getComponentNodes(new TabPredicate())) {
                tabs.add(currentComponentNode.getResource().adaptTo(Tab.class));
            }
        }
        return tabs;
    }

    public Boolean getHasTabs() {
        return !this.getTabs().isEmpty();
    }

    public String getUniqueId() {
        return constructUniqueId(currentPage, getResource());
    }

}

final class TabPredicate implements Predicate<ComponentNode> {

    @Override
    public boolean apply(ComponentNode input) {
        boolean isTab = false;
        if (input != null && input.getResource() != null) {
            final String resourceType = input.getResource().getResourceType();
            isTab = Tab.TYPE.equals(resourceType) || "wcm/msm/components/ghost".equals(resourceType);
        }
        return isTab;
    }
}