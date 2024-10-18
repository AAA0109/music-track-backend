package java_advance.spring_boot_spotify.model.album;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.List;

@Entity
@Data
public class Album {
    @Id
    private String id;
    private String name;
    @Lob
    private List<CoverImage> coverImage;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static class CoverImage {
        private String url;
        private Integer height;
        private Integer width;

        public void setUrl(String url) {
            this.url = url;
        }
        public void setHeight(Integer height) {
            this.height = height;
        }
        public void setWidth(Integer width) {
            this.width = width;
        }
    }
    public void setCoverImage(List<CoverImage> coverImage) {
        this.coverImage = coverImage;
    }
    public List<CoverImage> getCoverImage() {
        return coverImage;
    }
}