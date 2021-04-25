package project.rindus.springBackend.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;


@Data
@NoArgsConstructor
public class JsonPlaceHolderBean {

    @Value("${jsonPlaceHolder.baseUrl}")
    private String jsonPlaceHolderBaseUrl;

    @Value("${jsonPlaceHolder.users}")
    private String usersEndpoint;

    @Value("${jsonPlaceHolder.albums}")
    private String albumsEndpoint;

    @Value("${jsonPlaceHolder.todos}")
    private String todosEndpoint;
}
