import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VideoServiceTest {

    private VideoService videoServiceUsingMock;
    private IFileReader fileReaderMock;
    private IVideoRepository repositoryMock;

    private VideoService videoServiceReal;
    private List<Video> videos;
    private IVideoRepository repository;

    @BeforeEach
    public void beforeEachTest() {
        fileReaderMock = mock(IFileReader.class);
        repositoryMock = mock(IVideoRepository.class);
        videoServiceUsingMock = new VideoService(fileReaderMock, repositoryMock);

        IFileReader fileReader = new FileReader();
        repository = new VideoRepository();
        videoServiceReal = new VideoService(fileReader, repository);
        videos = getVideoList();
    }

    @Test
    public void readVideoTitle_MockFileFilledWithEntry_ReturnTitle() throws IOException {
        // Arrange
        when(fileReaderMock.read("video.txt")).thenReturn("{\"id\": 1,\"title\": \"abc\",\"isProcessed\": true}");

        // Act
        String result = videoServiceUsingMock.readVideoTitle();

        // Assert
        assertEquals("abc", result);
    }

    @Test
    public void getUnprocessedVideosAsCsv_MockAllVideosAreProcessed_ReturnEmptyString() {
        // Arrange
        when(repositoryMock.getUnprocessedVideos()).thenReturn(new ArrayList<>());

        // Act
        String result = videoServiceUsingMock.getUnprocessedVideosAsCsv();

        // Assert
        assertEquals("", result);

    }

    @Test
    public void getUnprocessedVideosAsCsv_MockHasUnprocessedVideos_ReturnIdsString() {
        // Arrange
        when(repositoryMock.getUnprocessedVideos()).thenReturn(videos);

        // Act
        String result = videoServiceUsingMock.getUnprocessedVideosAsCsv();

        // Assert
        assertEquals("1,2,3", result);

    }


    @Test
    public void getUnprocessedVideosAsCsv_AllVideosAreProcessed_ReturnEmptyString() {
        // Arrange

        // Act
        String result = videoServiceReal.getUnprocessedVideosAsCsv();

        // Assert
        assertEquals("", result);

    }

    @Test
    public void getUnprocessedVideosAsCsv_HasUnprocessedVideos_ReturnIdsString() {
        // Arrange
        videos.forEach(repository::addVideo);

        // Act
        String result = videoServiceReal.getUnprocessedVideosAsCsv();

        // Assert
        assertEquals("1,2,3", result);

    }


    private List<Video> getVideoList() {
        List<Video> videos = new ArrayList<>();
        videos.add(new Video(1, "Iron man", false));
        videos.add(new Video(2, "Hulk", false));
        videos.add(new Video(3, "Captain america", false));
        return videos;
    }
}