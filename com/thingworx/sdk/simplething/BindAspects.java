package com.thingworx.sdk.simplething;

import com.thingworx.types.collections.AspectCollection;
import com.thingworx.types.constants.Aspects;
import com.thingworx.types.constants.DataChangeType;
import com.thingworx.types.primitives.BooleanPrimitive;
import com.thingworx.types.primitives.IntegerPrimitive;
import com.thingworx.types.primitives.StringPrimitive;

public class BindAspects {
    private AspectCollection aspects = new AspectCollection();

    public BindAspects setDataChangeType(DataChangeType dataChangeType) {
        aspects.put(Aspects.ASPECT_DATACHANGETYPE, new StringPrimitive(dataChangeType.name()));
        return this;
    }
    public BindAspects setPushType(DataChangeType dataChangeType) {
        aspects.put("pushType", new StringPrimitive(dataChangeType.name()));
        return this;
    }

    public BindAspects setDefaultValue(boolean defaultValue) {
        aspects.put(Aspects.ASPECT_DEFAULTVALUE, new BooleanPrimitive(defaultValue));
        return this;
    }

    public BindAspects setDefaultValue(int defaultValue) {
        aspects.put(Aspects.ASPECT_DEFAULTVALUE, new IntegerPrimitive(defaultValue));
        return this;
    }

    public BindAspects setDefaultValue(String defaultValue) {
        aspects.put(Aspects.ASPECT_DEFAULTVALUE, new StringPrimitive(defaultValue));
        return this;
    }

    public AspectCollection getAspects(){
        return aspects;
    }
}
