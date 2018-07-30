package com.thingworx.sdk.simplething;

import com.thingworx.communications.client.ClientConfigurator;
import com.thingworx.communications.client.things.VirtualThingPropertyChangeEvent;
import com.thingworx.communications.client.things.VirtualThingPropertyChangeListener;
import com.thingworx.types.BaseTypes;
import com.thingworx.types.constants.DataChangeType;

public class Main {
    public static void main(String[] args) throws Exception {
        BindProperties properties = new BindProperties();
        String webSocketUrl = "ws://server-url.portal.ptc.io:80/Thingworx/WS";
        String appKey = "appkey";

        BindAspects string = new BindAspects()
                .setDataChangeType(DataChangeType.ALWAYS)
                .setDefaultValue("hello world!")
                .setPushType(DataChangeType.ALWAYS);

        BindAspects bool = new BindAspects()
                .setDataChangeType(DataChangeType.ALWAYS)
                .setDefaultValue(false)
                .setPushType(DataChangeType.ALWAYS);

        BindAspects integer = new BindAspects()
                .setDataChangeType(DataChangeType.ALWAYS)
                .setDefaultValue(777)
                .setPushType(DataChangeType.ALWAYS);

        properties.add("StringProp", "String property", BaseTypes.STRING, string.getAspects());
        properties.add("BooleanProp", "Boolean property", BaseTypes.BOOLEAN, bool.getAspects());
        properties.add("IntegerProp", "Integer property", BaseTypes.INTEGER, integer.getAspects());

        VirtualThingPropertyChangeListener onChangeListener = new VirtualThingPropertyChangeListener() {
            @Override
            public void propertyChangeEventReceived(VirtualThingPropertyChangeEvent evt) {
                String changedProperty = evt.getPropertyDefinition().getName();
                String newValue = evt.getPrimitiveValue().getValue().toString();

                System.out.println();
                System.out.println(String.format("%s:%s", changedProperty, newValue));
                System.out.println();
            }
        };
        ClientConfigurator config = ThingClient.config(webSocketUrl, appKey);
        new ThingClient("1", "TestThing", properties.getPropertyDefinition(), onChangeListener, config)
        .connect(250);
    }
}
