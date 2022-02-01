public class Video {
    private final Integer id;
    private final String title;
    private final boolean isProcessed;

    public Video(int id, String title, boolean isProcessed) {
        this.id = id;
        this.title = title;
        this.isProcessed = isProcessed;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public String getTitle() {
        return title;
    }

    public Integer getId() {
        return id;
    }

}
