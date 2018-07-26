package com.thingworx.sdk.simplething;

import com.thingworx.metadata.PropertyDefinition;
import com.thingworx.types.BaseTypes;
import com.thingworx.types.collections.AspectCollection;
import com.thingworx.types.constants.Aspects;
import com.thingworx.types.constants.DataChangeType;
import com.thingworx.types.primitives.BooleanPrimitive;
import com.thingworx.types.primitives.IntegerPrimitive;
import com.thingworx.types.primitives.NumberPrimitive;
import com.thingworx.types.primitives.StringPrimitive;

import java.util.ArrayList;
import java.util.List;

public class BindProperties {
    private List<PropertyDefinition> propertyDefinitions = new ArrayList<>();

    public void add(String name, String description, BaseTypes baseTypes, AspectCollection aspectCollection) {
        PropertyDefinition p = new PropertyDefinition(name, description, baseTypes);
        p.setAspects(aspectCollection);
        propertyDefinitions.add(p);
    }

    public  List<PropertyDefinition> getPropertyDefinition() {
        return propertyDefinitions;
    }
}
