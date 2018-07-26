
package com.thingworx.sdk.simplething;

import com.thingworx.communications.client.ClientConfigurator;
import com.thingworx.communications.client.ConnectedThingClient;
import com.thingworx.communications.client.things.VirtualThingPropertyChangeListener;
import com.thingworx.communications.common.SecurityClaims;
import com.thingworx.metadata.PropertyDefinition;
import java.util.List;

public class ThingClient extends ConnectedThingClient {

    public ThingClient(ClientConfigurator config) throws Exception {
        super(config);
    }

    public static void main(String[] args) { }

    public static void connect(String uri, String appKey, String id, String name, int scanRate, List<PropertyDefinition> property, VirtualThingPropertyChangeListener onChangeListener) throws Exception {
        ClientConfigurator config = new ClientConfigurator();
        config.setUri(uri);
        config.setReconnectInterval(15);
        SecurityClaims claims = SecurityClaims.fromAppKey(appKey);
        config.setSecurityClaims(claims);
        config.setName(name);
        config.setAsSDKType();
        config.ignoreSSLErrors(true);
        ThingClient client = new ThingClient(config);
        final Thing thing = new Thing(name + id, "Identifier: " + name + id, name + id, client, property);
        client.bindThing(thing);
        thing.addPropertyChangeListener(onChangeListener);

        client.start();

        while (!client.isShutdown()) {
            if (client.isConnected()) {
                thing.processScanRequest();
            }
            Thread.sleep(scanRate);
        }
    }
}
