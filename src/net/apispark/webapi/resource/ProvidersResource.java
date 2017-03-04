package net.apispark.webapi.resource;

public interface ProvidersResource {

    /**
     * Get all providers for distance, location.
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    void getProviders();

}