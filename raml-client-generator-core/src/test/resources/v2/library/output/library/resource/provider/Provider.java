
package library.resource.provider;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;
import library.exceptions.CoreServicesAPIReferenceException;
import library.responses.CoreServicesAPIReferenceResponse;


/**
 * External auth* provider information for the organization
 * 
 */
public class Provider {

    private String _baseUrl;
    private Client _client;

    public Provider() {
        _baseUrl = null;
        _client = null;
    }

    public Provider(String baseUrl, Client _client) {
        _baseUrl = (baseUrl +"/provider");
        this._client = _client;
    }

    protected Client getClient() {
        return this._client;
    }

    private String getBaseUri() {
        return _baseUrl;
    }

    /**
     * Returns combined provider details
     * 
     */
    public CoreServicesAPIReferenceResponse<library.resource.provider.model.ProviderGETResponseBody> get() {
        WebTarget target = this._client.target(getBaseUri());
        final javax.ws.rs.client.Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON_TYPE);
        Response response = invocationBuilder.get();
        if (response.getStatusInfo().getFamily()!= Family.SUCCESSFUL) {
            Response.StatusType statusInfo = response.getStatusInfo();
            throw new CoreServicesAPIReferenceException(statusInfo.getStatusCode(), statusInfo.getReasonPhrase(), response.getStringHeaders(), response);
        }
        CoreServicesAPIReferenceResponse<library.resource.provider.model.ProviderGETResponseBody> apiResponse = new CoreServicesAPIReferenceResponse<library.resource.provider.model.ProviderGETResponseBody>(response.readEntity(library.resource.provider.model.ProviderGETResponseBody.class), response.getStringHeaders(), response);
        return apiResponse;
    }

}
