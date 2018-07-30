package com.thingworx.sdk.simplething;

import com.thingworx.metadata.PropertyDefinition;
import com.thingworx.communications.client.ConnectedThingClient;
import com.thingworx.communications.client.things.VirtualThing;
import java.util.List;

public class Thing extends VirtualThing implements Runnable {

    public Thing(String name, String description, String identifier, ConnectedThingClient client, List<PropertyDefinition> property) throws Exception {
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

    public void scanDevice() {
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            this.getClient().shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
