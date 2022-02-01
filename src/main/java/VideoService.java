import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class VideoService {
    private final IFileReader fileReader;
    private final IVideoRepository repository;

    public VideoService(IFileReader fileReader, IVideoRepository repository) {
        this.fileReader = fileReader;
        this.repository = repository;
    }

    public String readVideoTitle() throws IOException {
        String readData = fileReader.read("video.txt");
        Video video = JsonConvert.DeserializeObject(readData);
        if (video != null) {
            return video.getTitle();
        }
        return "Error parsing the video.";

    }

    // Black Box Test
    // [] => ""
    // [{}, {}, {}] => "1,2,3"
    public String getUnprocessedVideosAsCsv() {

        List<Integer> videoIds = new ArrayList<>();
        List<Video> videos = repository.getUnprocessedVideos();
        for (Video video : videos) {
            videoIds.add(video.getId());
        }
        return videoIds.stream()
                .sorted(Comparator.naturalOrder())
                .map(id -> Integer.toString(id))
                .collect(Collectors.joining(","));
    }

}
