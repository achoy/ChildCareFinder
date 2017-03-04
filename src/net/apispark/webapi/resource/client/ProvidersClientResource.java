package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class ProvidersClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     */
    public ProvidersClientResource(net.apispark.webapi.Config config) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.absolutePath = config.getBasePath() + "/providers";
    }

    /**
     * Get all providers for distance, location.
     * 
     * @param distance
     *            distance from me in miles
     *            Required parameter.
     * @param lat
     *            latitude
     *            Required parameter.
     * @param lon
     *            longtitude in degrees
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void getProviders(java.lang.Double distance, java.lang.Double lat, java.lang.Double lon) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        QueryParameterHelper.addQueryParameter(client, "distance", distance);
        QueryParameterHelper.addQueryParameter(client, "lat", lat);
        QueryParameterHelper.addQueryParameter(client, "lon", lon);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.ProvidersResource.class).getProviders();
    }

}
