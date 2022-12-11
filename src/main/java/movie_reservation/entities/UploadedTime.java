package movie_reservation.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class UploadedTime {
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;

    public void create(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void modify(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }
}
