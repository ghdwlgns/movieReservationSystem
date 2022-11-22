package movie_reservation.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class UploadedTime {
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;

    public void create() {
        this.dateCreated = LocalDateTime.now();
    }

    public void modify() {
        dateModified = LocalDateTime.now();
    }
}
