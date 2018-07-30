
package com.thingworx.sdk.simplething;

import com.thingworx.communications.client.ClientConfigurator;
import com.thingworx.communications.client.ConnectedThingClient;
import com.thingworx.communications.client.things.VirtualThingPropertyChangeListener;
import com.thingworx.communications.common.SecurityClaims;
import com.thingworx.metadata.PropertyDefinition;

import java.util.List;

public class ThingClient extends ConnectedThingClient {
    private ClientConfigurator config = new ClientConfigurator();
    private ThingClient client;
    private Thing thing = null;

    public ThingClient(ClientConfigurator config) throws Exception {
        super(config);
    }

    public static void main(String[] args) { }

    public ThingClient config(String uri, String appKey, String id, String name, List<PropertyDefinition> property, VirtualThingPropertyChangeListener onChangeListener) throws Exception {
        config.setUri(uri);
        config.setReconnectInterval(15);
        SecurityClaims claims = SecurityClaims.fromAppKey(appKey);
        config.setSecurityClaims(claims);
        config.setName(name);
        config.setAsSDKType();
        config.ignoreSSLErrors(true);
        client = new ThingClient(config);
        thing = new Thing(name + id, "Identifier: " + name + id, name + id, client, property);
        client.bindThing(thing);
        thing.addPropertyChangeListener(onChangeListener);
        return this;
    }

    public ThingClient connect(int scanRate) throws Exception {
        client.start();
        while (!client.isShutdown()) {
            if (client.isConnected()) {
                thing.processScanRequest();
            }
            Thread.sleep(scanRate);
        }
        return this;
    }
}
