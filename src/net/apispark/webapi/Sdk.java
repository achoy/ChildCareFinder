package net.apispark.webapi;

/**
 * Entry-point for API calls.
 */
public class Sdk {

    private final net.apispark.webapi.Config config = new net.apispark.webapi.Config();

    /**
     * Returns the SDK configuration.
     */
    public net.apispark.webapi.Config getConfig() {
        return config;
    }

    public net.apispark.webapi.resource.client.ProvidersClientResource providers() {
        return new net.apispark.webapi.resource.client.ProvidersClientResource(config);
    }

}
