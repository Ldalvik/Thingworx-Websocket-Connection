package com.thingworx.sdk.simplething;

import com.thingworx.metadata.PropertyDefinition;
import com.thingworx.types.collections.AspectCollection;
import com.thingworx.types.constants.Aspects;
import com.thingworx.types.constants.DataChangeType;
import com.thingworx.types.primitives.BooleanPrimitive;
import com.thingworx.types.primitives.IntegerPrimitive;
import com.thingworx.types.primitives.NumberPrimitive;
import org.joda.time.DateTime;

import com.thingworx.communications.client.ConnectedThingClient;
import com.thingworx.communications.client.things.VirtualThing;
import com.thingworx.metadata.FieldDefinition;
import com.thingworx.metadata.annotations.ThingworxEventDefinition;
import com.thingworx.metadata.annotations.ThingworxEventDefinitions;
import com.thingworx.metadata.annotations.ThingworxPropertyDefinition;
import com.thingworx.metadata.annotations.ThingworxPropertyDefinitions;
import com.thingworx.metadata.annotations.ThingworxServiceDefinition;
import com.thingworx.metadata.annotations.ThingworxServiceParameter;
import com.thingworx.metadata.annotations.ThingworxServiceResult;
import com.thingworx.metadata.collections.FieldDefinitionCollection;
import com.thingworx.types.BaseTypes;
import com.thingworx.types.InfoTable;
import com.thingworx.types.collections.ValueCollection;
import com.thingworx.types.constants.CommonPropertyNames;
import com.thingworx.types.primitives.StringPrimitive;

import java.util.List;


public class SteamThing extends VirtualThing implements Runnable {

    public SteamThing(String name, String description, String identifier, ConnectedThingClient client, List<PropertyDefinition> property) throws Exception {
        super(name, description, identifier, client);
        for(int i = 0; i < property.size(); i++) {
            super.defineProperty(property.get(i));
        }
    }

    public void synchronizeState() {
        super.synchronizeState();
        super.syncProperties();
    }

    @Override
    public void processScanRequest() throws Exception {
        super.processScanRequest();
        this.scanDevice();
    }

    public void scanDevice() throws Exception {
        //super.updateSubscribedProperties(15000);
        //super.updateSubscribedEvents(60000);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            this.getClient().shutdown();
        } catch (Exception x) {
        }
    }
}
