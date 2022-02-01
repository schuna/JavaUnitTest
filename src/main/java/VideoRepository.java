import java.util.List;
import java.util.stream.Collectors;

public class VideoRepository implements IVideoRepository {

    public VideoContext videoContext;

    public VideoRepository() {
        videoContext = new VideoContext();
    }

    @Override
    public List<Video> getUnprocessedVideos() {

        return videoContext.videoHashSet.stream()
                .filter(video -> !video.isProcessed())
                .collect(Collectors.toList());
    }

    @Override
    public void addVideo(Video video) {
        this.videoContext.videoHashSet.add(video);
    }
}
