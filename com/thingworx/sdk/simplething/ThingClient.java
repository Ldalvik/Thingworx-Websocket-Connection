
package com.thingworx.sdk.simplething;

import com.thingworx.communications.client.ClientConfigurator;
import com.thingworx.communications.client.ConnectedThingClient;
import com.thingworx.communications.client.things.VirtualThingPropertyChangeListener;
import com.thingworx.communications.common.SecurityClaims;
import com.thingworx.metadata.PropertyDefinition;

import java.util.List;

public class ThingClient extends ConnectedThingClient {
    private static ClientConfigurator config = new ClientConfigurator();
    private static Thing thing;

    public ThingClient(String id, String name, List<PropertyDefinition> property, VirtualThingPropertyChangeListener onChangeListener, ClientConfigurator config) throws Exception {
        super(config);
        thing = new Thing(name + id, "Identifier: " + name + id, name + id, this, property);
        thing.addPropertyChangeListener(onChangeListener);
        config.setName(name);
        this.bindThing(thing);
    }


    public static void main(String[] args) { }

    public static ClientConfigurator config(String uri, String appKey) {
        config.setUri(uri);
        config.setReconnectInterval(15);
        SecurityClaims claims = SecurityClaims.fromAppKey(appKey);
        config.setSecurityClaims(claims);
        config.setAsSDKType();
        config.ignoreSSLErrors(true);
        return config;
    }

    public ThingClient connect(int scanRate) throws Exception {
        this.start();
        while (!this.isShutdown()) {
            if (this.isConnected()) {
                thing.processScanRequest();
            }
            Thread.sleep(scanRate);
        }
        return this;
    }
}
