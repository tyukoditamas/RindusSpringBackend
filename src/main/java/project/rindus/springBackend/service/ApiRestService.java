package project.rindus.springBackend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.rindus.springBackend.data.AlbumData;
import project.rindus.springBackend.data.TodoData;
import project.rindus.springBackend.exception.CallFailedException;

import java.io.File;
import java.io.IOException;

/**
 * Class service for REST APIs & operations.
 */
@Service
public class ApiRestService {
    /*
    Using slf4j logging.
     */
    private static final Logger log = LoggerFactory.getLogger(ApiRestService.class);

    private final String EXPORT_LOCATION = System.getProperty("user.dir") + "/src/main/resources/ExportedFiles/";

    @Autowired
    private JsonPlaceHolderService jsonPlaceHolderService;

    public Object getUserById(String userId) throws CallFailedException, IOException {
        /*
        Using objects in order to simplify the implementation logic because of the different objects structures
        returned from consuming 3rd party REST service APIs.
         */
        Object userResponseData = jsonPlaceHolderService.sendGetUserByIdRequest(userId);

        /*
        Exporting the Response Body to JSON file.
         */
        exportToJsonFile(userResponseData);
        /*
        Exporting the Response Body to XML file.
         */
        exportToXmlFile(userResponseData);

        return userResponseData;
    }

    public Object getAllAlbums() throws IOException, CallFailedException {
        Object userResponseData = jsonPlaceHolderService.sendGetAllAlbums();
         /*
        Exporting the Response Body to JSON file.
         */
        exportToJsonFile(userResponseData);
        /*
        Exporting the Response Body to XML file.
         */
        exportToXmlFile(userResponseData);

        return userResponseData;
    }

    public Object postTodo(TodoData todoData) throws IOException, CallFailedException {
        todoData.setCompleted("false");
        Object userResponseData = jsonPlaceHolderService.sendPostTodo(todoData);
         /*
        Exporting the Response Body to JSON file.
         */
        exportToJsonFile(userResponseData);
        /*
        Exporting the Response Body to XML file.
         */
        exportToXmlFile(userResponseData);

        return userResponseData;
    }

    public Object updateAlbum(AlbumData albumData) throws IOException, CallFailedException {
        Object userResponseData = jsonPlaceHolderService.sendUpdateAlbum(albumData);
         /*
        Exporting the Response Body to JSON file.
         */
        exportToJsonFile(userResponseData);
        /*
        Exporting the Response Body to XML file.
         */
        exportToXmlFile(userResponseData);

        return userResponseData;
    }


    public Object deleteUserById(String userId) throws CallFailedException, IOException {
        /*
        Using objects in order to simplify the implementation logic because of the different objects structures
        returned from consuming 3rd party REST service APIs.
         */
        Object userResponseData = jsonPlaceHolderService.sendDeleteUserByIdRequest(userId);

        /*
        Exporting the Response Body to JSON file.
         */
        exportToJsonFile(userResponseData);
        /*
        Exporting the Response Body to XML file.
         */
        exportToXmlFile(userResponseData);

        return userResponseData;
    }

    /**
     * Method which exports response data to JSON file using JACKSON.
     * The file will be located: /src/main/resources/ExportedFiles/
     *
     * @param userResponseData - data to be exported
     * @throws IOException
     */
    private void exportToJsonFile(Object userResponseData) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(EXPORT_LOCATION + "JsonResponseData.json"), userResponseData);
        } catch (IOException e) {
            log.error("Error while processing the JSON file! Error message: {}", e.getMessage());
            throw e;
        }
        log.info("Export successfully to {}JsonResponseData.json", EXPORT_LOCATION);
    }

    /**
     * Method which exports response data to XML file using JACKSON.
     * The file will be located: /src/main/resources/ExportedFiles/
     *
     * @param userResponseData - data to be exported
     * @throws IOException
     */
    private void exportToXmlFile(Object userResponseData) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writeValue(new File(EXPORT_LOCATION + "XmlResponseData.xml"), userResponseData);
        } catch (IOException e) {
            log.error("Error while processing the XML file! Error message: {}", e.getMessage());
            throw e;
        }
        log.info("Export successfully to {}XmlResponseData.xml", EXPORT_LOCATION);
    }
}
