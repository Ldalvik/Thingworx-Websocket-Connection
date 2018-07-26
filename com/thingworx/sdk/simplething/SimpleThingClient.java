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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thingworx.communications.client.ClientConfigurator;
import com.thingworx.communications.client.ConnectedThingClient;
import com.thingworx.communications.client.things.VirtualThing;

public class SimpleThingClient {
    public static void main(String[] args) throws Exception {
        BindProperties properties = new BindProperties();

        BindAspects aspects = new BindAspects()
                .setDataChangeType(DataChangeType.ALWAYS)
                .setDefaultValue("default value")
                .setPushType(DataChangeType.ALWAYS);
        properties.add("TestProp1", "Prop description", BaseTypes.STRING, aspects.getAspects());
        properties.add("TestProp2", "Prop description", BaseTypes.STRING, aspects.getAspects());
        properties.add("TestProp3", "Prop description", BaseTypes.STRING, aspects.getAspects());

        SteamSensorClient.connect("ws://pp-1804271345f2.portal.ptc.io:80/Thingworx/WS",
                "f76e9513-0bbc-4b33-af7f-09e5ea959504", "1", "TestThing", properties.getPropertyDefinition());
    }

}
