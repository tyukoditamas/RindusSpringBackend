package project.rindus.springBackend.data;

import lombok.Data;

@Data
public class TodoData {

    private String userId;
    private String title;
    private String completed;
}
