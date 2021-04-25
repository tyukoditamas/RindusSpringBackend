package project.rindus.springBackend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import project.rindus.springBackend.configuration.JsonPlaceHolderBean;
import project.rindus.springBackend.data.AlbumData;
import project.rindus.springBackend.data.TodoData;
import project.rindus.springBackend.exception.CallFailedException;

/**
 * Service class for preparing the REST calls to 3rd party REST service.
 */
@Service
public class JsonPlaceHolderService {
    /*
    Using slf4j logging.
     */
    private static final Logger log = LoggerFactory.getLogger(JsonPlaceHolderService.class);

    @Autowired
    private JsonPlaceHolderBean jsonPlaceHolderConfiguration;

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    /**
     * Send a GET call to retrieve a single user by ID.
     * @param userId - The ID of the user.
     * @return The response body of the call.
     * @throws CallFailedException - exception thrown because of various reasons happened during the REST call
     */
    public Object sendGetUserByIdRequest(String userId) throws CallFailedException {
        HttpEntity<String> httpEntity = new HttpEntity<>(getHttpHeader());

        String url = jsonPlaceHolderConfiguration.getJsonPlaceHolderBaseUrl()
                + jsonPlaceHolderConfiguration.getUsersEndpoint()
                + userId;

        return sendGetRestCall(httpEntity, url, HttpMethod.GET);
    }

    /**
     * Send a GET call to retrieve all albums.
     * @return The response body of the call.
     */
    public Object sendGetAllAlbums() throws CallFailedException {
        HttpEntity<String> httpEntity = new HttpEntity<>(getHttpHeader());

        String url = jsonPlaceHolderConfiguration.getJsonPlaceHolderBaseUrl()
                + jsonPlaceHolderConfiguration.getAlbumsEndpoint();
        return sendGetRestCall(httpEntity, url, HttpMethod.GET);
    }

    /**
     * Send a POST call to register a new ThingsTodo
     * @param todoData- The request body of the call, containing: userId, title, completed
     * @return - The response body of the call
     * @throws CallFailedException
     */
    public Object sendPostTodo(TodoData todoData) throws CallFailedException {
        HttpEntity<TodoData> httpEntity = new HttpEntity<>(todoData, getHttpHeader());

        String url = jsonPlaceHolderConfiguration.getJsonPlaceHolderBaseUrl()
                + jsonPlaceHolderConfiguration.getTodosEndpoint();

        return sendGetRestCall(httpEntity, url, HttpMethod.POST);
    }

    /**
     * Send a PUT call to update an album.
     * @param albumData The request body of the call, containing: userId, title, albumId
     * @return - The response body of the call
     * @throws CallFailedException
     */
    public Object sendUpdateAlbum(AlbumData albumData) throws CallFailedException {
        HttpEntity<AlbumData> httpEntity = new HttpEntity<>(albumData, getHttpHeader());

        String url = jsonPlaceHolderConfiguration.getJsonPlaceHolderBaseUrl()
                + jsonPlaceHolderConfiguration.getAlbumsEndpoint()
                + albumData.getId();

        return sendGetRestCall(httpEntity, url, HttpMethod.PUT);
    }

    /**
     * Send a Delete call to remove a single user by ID.
     * @param userId - The ID of the user.
     * @return The response body of the call.
     * @throws CallFailedException - exception thrown because of various reasons happened during the REST call
     */
    public Object sendDeleteUserByIdRequest(String userId) throws CallFailedException {
        HttpEntity<String> httpEntity = new HttpEntity<>(getHttpHeader());

        String url = jsonPlaceHolderConfiguration.getJsonPlaceHolderBaseUrl()
                + jsonPlaceHolderConfiguration.getUsersEndpoint()
                + userId;

        return sendGetRestCall(httpEntity, url, HttpMethod.DELETE);
    }

    /**
     * Generalized GET REST call.
     * @param httpEntity The entity of the REST call.
     * @param url The URL path where the call will be sent.
     * @return The response body.
     * @throws CallFailedException
     */
    private Object sendGetRestCall(HttpEntity<?> httpEntity, String url, HttpMethod httpMethod) throws CallFailedException {
        ResponseEntity<Object> response;
        log.info("Sending GET request to: {}", url);
        try {
            response = restTemplate.exchange(
                    url,
                    httpMethod,
                    httpEntity,
                    Object.class
            );
            Object responseBody = response.getBody();
            log.info("Sending GET request to {} was successful! ResponseBody: {}", url, responseBody);
            return responseBody;
        } catch (RestClientException exception) {
            log.error("Error sending GET request to {}! Error message: {}", url, exception.getMessage());
            throw new CallFailedException(exception);
        }
    }

    private HttpHeaders getHttpHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        return headers;
    }
}
