import java.util.List;

public interface IVideoRepository {
    List<Video> getUnprocessedVideos();

    void addVideo(Video video);
}
