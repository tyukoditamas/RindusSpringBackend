package project.rindus.springBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.rindus.springBackend.data.AlbumData;
import project.rindus.springBackend.data.TodoData;
import project.rindus.springBackend.exception.CallFailedException;
import project.rindus.springBackend.service.ApiRestService;

import java.io.IOException;

/**
 * Controller class used to receive REST calls from frontend.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ApiRestController {

    @Autowired
    private ApiRestService apiRestService;

    @GetMapping("/getTest")
    public ResponseEntity<String> getTestEndpoint() {
        System.out.println("Accepted!");
        return new ResponseEntity<>("Request GET received!", HttpStatus.ACCEPTED);
    }

    /**
     * Endpoint used to get a single user by id
     */
    @GetMapping("/getUserById/{userId}")
    public ResponseEntity getUserById(@PathVariable(name = "userId") String userId) {
        Object responseBody;
        try {
            responseBody = apiRestService.getUserById(userId);
        } catch (CallFailedException | IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseBody, HttpStatus.ACCEPTED);
    }

    /**
     * Endpoint used to delete a single user by id
     */
    @DeleteMapping("/getUserById/{userId}")
    public ResponseEntity deleteUserById(@PathVariable(name = "userId") String userId) {
        Object responseBody;
        try {
            responseBody = apiRestService.deleteUserById(userId);
        } catch (CallFailedException | IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseBody, HttpStatus.ACCEPTED);
    }

    /**
     * Endpoint used to get a single user by id
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAllAlbums")
    public ResponseEntity getAllAlbums() {
        Object responseBody;
        try {
            responseBody = apiRestService.getAllAlbums();
        } catch (CallFailedException | IOException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseBody, HttpStatus.ACCEPTED);
    }

    /**
     * Endpoint used to post a ThingsTodo for a userId
     */
    @PostMapping("/postTodo")
    public ResponseEntity postTodo(@RequestBody TodoData todoData) {
        Object responseBody;
        try {
            responseBody = apiRestService.postTodo(todoData);
        } catch (CallFailedException | IOException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseBody, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateAlbums")
    public ResponseEntity updateAlbum(@RequestBody AlbumData albumData) {
        Object responseBody;
        try {
            responseBody = apiRestService.updateAlbum(albumData);
        } catch (CallFailedException | IOException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseBody, HttpStatus.ACCEPTED);
    }

}
